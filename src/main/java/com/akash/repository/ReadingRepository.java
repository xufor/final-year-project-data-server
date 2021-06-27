package com.akash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.entity.Reading;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Integer> {
}
