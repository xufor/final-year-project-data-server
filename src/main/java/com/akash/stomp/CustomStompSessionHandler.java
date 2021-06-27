package com.akash.stomp;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomStompSessionHandler implements StompSessionHandler {

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
	    System.out.println(payload);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		session.subscribe("/topic/readings", this);
		log.info("Subscribed to topic - /topic/readings");
	}
	
	@Override
	public Type getPayloadType(StompHeaders headers) {
		return ReadingMessage.class;
	}
	
	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		log.info(exception.getMessage());
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		log.info(exception.getMessage());	
	}
	
}
