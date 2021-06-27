package com.akash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.akash.stomp.FypStompClient;

@SpringBootApplication
public class FypDataServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FypDataServerApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void tasksAfterStartup() {
		new FypStompClient();
	}

}
