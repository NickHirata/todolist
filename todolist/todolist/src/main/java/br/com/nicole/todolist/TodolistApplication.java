package br.com.nicole.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Em resumo, UserController.java controla as 
 * operações relacionadas ao usuário 
 * (receber requisições HTTP, processá-las e
 * enviar respostas) e UserModel.java representa a 
 * estrutura de dados do usuário no banco de dados. 
 * Ambas são classes essenciais em uma aplicação 
 * Spring Boot típica.
 * 
 * 
 * 
 * application properties é onde eu escrevo para pode fazer a integracao com o banco de dados 
 * no caso aqui foi usado em memoria, ou seja depois ele destroi e o h2
 */
@SpringBootApplication
public class TodolistApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
