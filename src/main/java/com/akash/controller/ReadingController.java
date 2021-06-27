package com.akash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.akash.entity.Reading;
import com.akash.service.ReadingService;

@RestController
@CrossOrigin(originPatterns = "http://*")
public class ReadingController {
	
	@Autowired 
	ReadingService readingService;
	
	@GetMapping("/test")
	public String startClient() {
		return "Working!";
	}
	
	@GetMapping("/query-few/{targetTimestamp}")
	public List<Reading> queryFewReadings(@PathVariable long targetTimestamp) {
		return readingService.getFewReadings(targetTimestamp);
	}
	
	@GetMapping("/query-btw/{mintargetTimestamp}/{maxtargetTimestamp}")
	public List<Reading> queryReadingsBetween(@PathVariable long mintargetTimestamp, @PathVariable long maxtargetTimestamp) {
		return readingService.queryReadingsBetween(mintargetTimestamp, maxtargetTimestamp);
	}
	
	@GetMapping("/query-mtr/{mintargetTimestamp}/{maxtargetTimestamp}")
	public List<Object[]> queryReadingMetrices(@PathVariable long mintargetTimestamp, @PathVariable long maxtargetTimestamp) {
		return readingService.queryReadingMetrices(mintargetTimestamp, maxtargetTimestamp);
	}
	
	
}