package com.example;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;

import lombok.Data;

import com.microsoft.azure.functions.*;

public class Function {

    @FunctionName ("helloserverless")
    public String hello(
        @HttpTrigger (
            name = "hellorest",
            methods = {HttpMethod.GET},
            route = "hello"
        )
        String x) {

		return "Hello REST World!";
	}

    @FunctionName ("createperson")
    public Person create (
        @HttpTrigger (
            name = "createpersonrest",
            methods = {HttpMethod.POST},
            route = "person"
        ) 
        Person person) {
		// Do something important
		// Some business rules

		person.setId(UUID.randomUUID());

		return person;
	}

    @FunctionName ("updateperson")
	public Person update (
        @HttpTrigger (
            name = "updatepersonrest",
            methods = {HttpMethod.PUT},
            route = "person"
        ) 
        Person person) {
		// Do some update ...

		person.setId(UUID.randomUUID());
		person.setName(person.getName() + " - updated!");

		return person;
    }
}

@Data
class Person{
	private UUID id;
	private String name;
	private int age;
}