package com.example.apirest;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}
}

@RestController
class HelloWorldRest {

	@GetMapping ("/hello")
	public String hello() {
		return "Hello REST World!";
	}
}

@Data
class Person{
	private UUID id;
	private String name;
	private int age;
}

@RestController
class PersonBusiness {

	@PostMapping ("/person")
	public Person create (@RequestBody Person person) {
		// Do something important
		// Some business rules

		person.setId(UUID.randomUUID());

		return person;
	}

	@PutMapping ("/person")
	public Person update (@RequestBody Person person) {
		// Do some update ...

		person.setId(UUID.randomUUID());
		person.setName(person.getName() + " - updated!");

		return person;
	}

}
