package com.sun.task_manager;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class TaskManagerApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	@Test
	void contextLoads() {
	}
	@Test
	void testGetTask(){
		ResponseEntity<Task> getResponse = restTemplate.getForEntity("/task/0",Task.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Task task = getResponse.getBody();
		assertThat(task.id()).isEqualTo(0);
		assertThat(task.title()).isEqualTo("hello world");
	}
	@Test
	void testPostTask(){
		Task newTask = new Task(null,"hi world");
		ResponseEntity<Void> postResponse = restTemplate.postForEntity("/task",newTask,Void.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		URI uri = postResponse.getHeaders().getLocation();
		ResponseEntity<Task> getResponse = restTemplate.getForEntity(uri,Task.class);
		Task task = getResponse.getBody();
		assertThat(task.id()).isNotNull();
		assertThat(task.title()).isEqualTo("hi world");
	}

}
