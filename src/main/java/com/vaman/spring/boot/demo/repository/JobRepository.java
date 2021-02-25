package com.vaman.spring.boot.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.vaman.spring.boot.demo.model.Job;

public interface JobRepository extends CrudRepository<Job, Integer> {

}
