package com.akash.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "readings")
public class Reading {
	
	@Id
	private long timestamp;
	private float reading;
	
}
