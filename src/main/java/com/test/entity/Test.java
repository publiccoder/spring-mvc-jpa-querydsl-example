package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.sql.Timestamp;

/**
 * The persistent class for the test database table.
 * 
 */
@Entity
@Table(name = "test")
@NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = true, updatable = false)
	private Date createdAt;

	private String test;

	@Column(name = "updated_at", insertable = true, updatable = true)
	private Timestamp updatedAt;

	public Test() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTest() {
		return this.test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}