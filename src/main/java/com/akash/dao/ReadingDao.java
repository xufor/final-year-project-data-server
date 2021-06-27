package com.akash.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akash.entity.Reading;
import com.akash.repository.ReadingRepository;

@Repository
public class ReadingDao {
	
	@Autowired
	ReadingRepository readingRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void saveReading(Reading reading) {
		readingRepository.save(reading);
	}

    public List<Reading> find15GreaterReadings(long targetTimestamp) {
        return entityManager.createQuery("SELECT r FROM Reading r WHERE r.timestamp > " + targetTimestamp,
          Reading.class).setMaxResults(15).getResultList();
    }
    
    public List<Reading> findReadingsBetween(long mintargetTimestamp, long maxtargetTimestamp) {
        return entityManager.createQuery("SELECT r FROM Reading r WHERE r.timestamp > :mints and r.timestamp < :maxts",
        		Reading.class).setParameter("mints", mintargetTimestamp)
        		.setParameter("maxts", maxtargetTimestamp)
        		.getResultList();
    }
    
    public List<Object[]> findReadingMetrices(long mintargetTimestamp, long maxtargetTimestamp) {
    	return entityManager.createQuery("SELECT MIN(r.reading), MAX(r.reading), AVG(r.reading) FROM Reading r WHERE r.timestamp > :mints and r.timestamp < :maxts",
    			Object[].class).setParameter("mints", mintargetTimestamp)
        		.setParameter("maxts", maxtargetTimestamp)
        		.getResultList();
    }
	
}
