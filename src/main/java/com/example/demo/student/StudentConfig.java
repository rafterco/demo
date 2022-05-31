package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository) {
        return args -> {
            Student frank = new Student("Frank Barrett",
                    "frictionfrank@hotmail.com",
                    LocalDate.of(1981, 4, 4));
            Student colin = new Student(
                    "Colin Raftrer",
                    "colinrafter82@gmail.com",
                    LocalDate.of(1982, 4, 3));
            Student heather = new Student(
                    "Heather Rafter",
                    "heatherRafter@gmail.com",
                    LocalDate.of(1991, 4, 3));

            studentRepository.saveAll(List.of(frank, colin, heather));
        };

    }

}
