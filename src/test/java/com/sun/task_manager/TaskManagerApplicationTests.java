package com.sun.task_manager;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.Transient;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskManagerApplicationTests {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Test
	void contextLoads() {
	}
	@Test
	void postTask(@RequestBody Task newTask){
		ResponseEntity<Void> postResponse = restTemplate.postForEntity("/task",newTask,Void.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		URI uri = postResponse.getHeaders().getLocation();
		ResponseEntity<Task> getResponse = restTemplate.getForEntity(uri,Task.class);
		Task task = getResponse.getBody();
		assertThat(task).isEqualTo(newTask);
	}

}
