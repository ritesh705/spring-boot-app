package com.ritesh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication is equivalent to using
@ComponentScan, @Configuration and @EnableAutoConfiguration
*/
@SpringBootApplication
public class SpringBootApp
{
	public static void main(String[] args)
	{
		String test = System.getProperty("test");
		System.out.println(test);
		System.out.println("Memory: "+Runtime.getRuntime().maxMemory()/(1024*1024));
		SpringApplication.run(SpringBootApp.class, args);
	}
}
