package com.sun.task_manager;

import org.springframework.data.annotation.Id;

public record Task(@Id long id,String description){
    
}
