package com.sun.task_manager;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;
@JsonTest
public class TaskJsonTest {
    
    @Autowired
    JacksonTester<Task> taskJson;

    @Test
    void serializeTask() throws IOException{
        Task task = new Task(0,"hello world");
        assertThat(taskJson.write(task)).isStrictlyEqualToJson("expected.json");
        assertThat(taskJson.write(task)).hasJsonPathStringValue("@.title");
        assertThat(taskJson.write(task)).hasJsonPathNumberValue("@.id");
    }

    @Test
    void deserializetask() throws IOException{
        Task task = taskJson.readObject("expected.json");
        assertThat(task.title()).isEqualTo("hello world");
        assertThat(task.id()).isEqualTo(0);
    }
}
