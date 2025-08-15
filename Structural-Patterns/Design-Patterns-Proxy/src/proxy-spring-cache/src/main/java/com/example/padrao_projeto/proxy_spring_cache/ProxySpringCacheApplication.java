package com.example.padrao_projeto.proxy_spring_cache;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ProxySpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxySpringCacheApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(BookRepository bookRepository){
		return args -> {
			System.out.println("First call: " + bookRepository.findBookByIsbn("1"));
			System.out.println("Second call: " + bookRepository.findBookByIsbn("2"));
			System.out.println("Third call: " + bookRepository.findBookByIsbn("1"));
			System.out.println("Fourth call: " + bookRepository.findBookByIsbn("3"));
			System.out.println("Fifth call: " + bookRepository.findBookByIsbn("3"));
			System.out.println("Sixth call: " + bookRepository.findBookByIsbn("2"));
		};
	}

}
