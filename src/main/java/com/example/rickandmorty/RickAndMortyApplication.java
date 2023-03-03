package com.example.rickandmorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RickAndMortyApplication {
  public static void main(String[] args) {
    SpringApplication.run(RickAndMortyApplication.class, args);
  }
}
