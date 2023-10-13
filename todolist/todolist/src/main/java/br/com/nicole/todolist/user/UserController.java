package br.com.nicole.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


/**
 * Modificador 
 * public 
 * private
 * protected
 */

 @RestController
 @RequestMapping("/users")
public class UserController {
    
    

    @Autowired
    private IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

   


    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        UserModel existingUser = userRepository.findByUsername(userModel.getUsername());

        if (existingUser != null) {
            String responseMessage = "Usuário já existe";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        var passwordHashred = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        userRepository.save(userModel);
        String responseMessage = "Usuário adicionado com sucesso";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}











 /**
     * string
     * int
     * double
     * float
     * char
     * date
     * void
     */
 //logica para criar um novo user com base no userModel
        /*
         * @RequestBody: Esta é uma anotação do Spring Framework usada 
         * em métodos de controlador para indicar que o parâmetro do método 
         * (nesse caso, userModel) deve ser vinculado ao corpo da solicitação HTTP. 
         * Isso é comumente usado em aplicativos da web para extrair dados JSON ou 
         * XML do corpo da solicitação HTTP e convertê-los em objetos Java.
         * 
         * o corpo da solicitação contém os dados que estão sendo 
         * enviados para o servidor quando você faz uma requisição HTTP
         */

