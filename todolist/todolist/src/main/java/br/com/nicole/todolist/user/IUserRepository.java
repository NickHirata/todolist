package br.com.nicole.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Tem os metodos, mas nao tem a implementaçao 
 * representacao dos metodos apenas 
 */


public interface IUserRepository extends JpaRepository <UserModel, UUID> {
    UserModel findByUsername(String username);
    
}
