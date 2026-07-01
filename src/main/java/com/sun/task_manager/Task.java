package com.sun.task_manager;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TASKS")
public record Task(@Id Integer id,String title){
    
}
