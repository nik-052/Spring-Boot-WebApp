package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class studentConfig{

    @Bean
    CommandLineRunner commandLineRunner (StudentDaos studentDaos){
        return args -> {
            Student Nikhil = new Student( "Nikhil", 24, "M", "nikhilsaji200@gmail.com");
            Student Harley = new Student( "Harley", 8, "M", "harley@gmail.com");
            studentDaos.saveAll(
                    List.of(Nikhil, Harley)
            );
        };
    }
}
