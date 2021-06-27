package com.akash.stomp;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import com.akash.entity.Reading;
import com.akash.service.ReadingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomStompSessionHandler implements StompSessionHandler {
	
	private ReadingService readingService;
	
	public CustomStompSessionHandler(ReadingService storageService) {
		this.readingService = storageService;
	}
	
	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
	    log.info("Received data -> " + payload);
	    this.readingService.storeReading((Reading) payload);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		session.subscribe("/topic/readings", this);
		log.info("Subscribed to topic -> /topic/readings");
	}
	
	@Override
	public Type getPayloadType(StompHeaders headers) {
		return Reading.class;
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
