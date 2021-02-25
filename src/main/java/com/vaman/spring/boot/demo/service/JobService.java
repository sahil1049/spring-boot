package com.vaman.spring.boot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaman.spring.boot.demo.model.Job;
import com.vaman.spring.boot.demo.repository.JobRepository;

@Service
public class JobService {
	private final Logger log = LoggerFactory.getLogger(JobService.class);

	@Autowired
	JobRepository jobRepository;

	public List<Job> getAllJobs() {
		log.info("getAllJobs service");
		List<Job> jobsList = new ArrayList<Job>();
		jobRepository.findAll().forEach(job -> jobsList.add(job));
		return jobsList;
	}

	public Job getJobById(int id) {
		log.info("getJobById service");
		return jobRepository.findById(id).get();
	}

	public Job createJob(Job job) {
		System.out.println("createJob service");
		return jobRepository.save(job);
	}

	public Optional<Job> update(Job job, int id) {
		System.out.println("update service");
		return jobRepository.findById(id).map( j -> {
			j.setId(j.getId()); // use getters-setters here 
			j.setName(job.getName());
			j.setDesignation(job.getDesignation());
			return jobRepository.save(j);
		});
	}

	public void delete(int id) {
		System.out.println("delete service");
		jobRepository.deleteById(id);
	}

}
