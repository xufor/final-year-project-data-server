package com.akash.stomp;

import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class FypStompClient {

	private String stompEndpoint = "ws://localhost:8080/stomp-endpoint";
	
	public FypStompClient() {
		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new CustomStompSessionHandler();
		try {
			stompClient.connect(stompEndpoint, sessionHandler).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}
