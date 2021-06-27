package com.akash.stomp;

import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.akash.service.ReadingService;

public class FypStompClient {

	private String stompEndpoint = "ws://20.193.232.92:8080/stomp-endpoint";
	
	public FypStompClient(ReadingService readingService) {
		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new CustomStompSessionHandler(readingService);
		try {
			stompClient.connect(stompEndpoint, sessionHandler).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}
