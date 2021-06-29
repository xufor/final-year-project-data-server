package com.akash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.akash.dao.ReadingDao;
import com.akash.entity.Reading;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReadingService {
	
	@Autowired
	ReadingDao readingDao;
	
	public void storeReading(Reading reading) {
	    log.info("Saved data -> " + reading);
		readingDao.saveReading(reading);
	}
	
	public List<Reading> getFewReadings(@PathVariable long targetTimestamp) {
	    log.info("Serving at least 15 rows greater than timestamp -> " + targetTimestamp);
		return readingDao.find15GreaterReadings(targetTimestamp);
	}
	
	public List<Reading> queryReadingsBetween(long mintargetTimestamp, long maxtargetTimestamp) {
	    log.info("Serving all rows between timestamp -> " + mintargetTimestamp + " and " + maxtargetTimestamp);
		return readingDao.findReadingsBetween(mintargetTimestamp, maxtargetTimestamp);
	}
	
	public List<Object[]> queryReadingMetrices(long mintargetTimestamp, long maxtargetTimestamp) {
	    log.info("Serving basic metrics between timestamp -> " + mintargetTimestamp + " and " + maxtargetTimestamp);
		return readingDao.findReadingMetrices(mintargetTimestamp, maxtargetTimestamp);
	}
}
