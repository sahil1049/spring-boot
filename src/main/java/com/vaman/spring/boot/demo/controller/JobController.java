package com.vaman.spring.boot.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaman.spring.boot.demo.model.Job;

import com.vaman.spring.boot.demo.service.JobService;

@RestController
public class JobController {
	private final Logger log = LoggerFactory.getLogger(JobController.class);

	@Autowired
	JobService service;

	@GetMapping("/jobs")
	private List<Job> getAllJobs() {
		log.info("getAlljobs controller");
		return service.getAllJobs();
		
	}

	@GetMapping("/job/{id}")
	private Job getJob(@PathVariable("id") int id) {
		log.info("getjob controller");
		return service.getJobById(id);
	}

	@PostMapping("/job")
	private int createJob(@RequestBody Job job) {
		log.info("createJob controller");
		service.createJob(job);
		return job.getId();
	}

	@PutMapping("/job/{id}")
	private Optional<Job> update(@PathVariable("id") int id, @RequestBody Job job) {
		log.info("update controller");
		return service.update(job, id);
	}

	@DeleteMapping("/job/{id}")
	private void deleteJob(@PathVariable("id") int id) {
		log.info("deleteJob controller");
		service.delete(id);
	}
}
