package br.com.nicole.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Lombok que coloca os get e set automaticamente 
 * insere o cod do proprio site no pom.xml
 */


@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id; //gerador de ids


    @Column(unique = true)
    //entende que essa sao as colunas do bd
    private String username;
    private String name;
    private String password;


    @CreationTimestamp
    private LocalDateTime createdAt;


    


}
