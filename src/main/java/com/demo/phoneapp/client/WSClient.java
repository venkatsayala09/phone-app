package com.demo.phoneapp.client;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import com.demo.phoneapp.model.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WSClient {
	private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

	public ListenableFuture<StompSession> connect() {
		Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
		List<Transport> transports = Collections.singletonList(webSocketTransport);
		SockJsClient sockJsClient = new SockJsClient(transports);
		sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		String url = "ws://{host}:{port}/gs-guide-websocket";
		return stompClient.connect(url, headers, new MyHandler(), "localhost", 8081);
	}

	public Type getPayloadType(StompHeaders stompHeaders) {
		return byte[].class;
	}

	public void handleFrame(StompHeaders stompHeaders, Object o) {
		log.info("Received message " + new String((byte[]) o));
	}

	public void send(StompSession stompSession, Notification notification) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(notification);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		stompSession.send("/app/notify", json.getBytes());
	}

	private class MyHandler extends StompSessionHandlerAdapter {
		public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
			log.info("Now connected : Session ID -> " + stompSession.getSessionId());
		}
	}
}
