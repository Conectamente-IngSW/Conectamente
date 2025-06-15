package com.ingsw.conectamente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ConectamenteApplication {

    public static void main(String[] args) {
        // 1) Instanciamos el encoder
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 2) Generamos el hash de "admin123"
        //String hash = encoder.encode("pass123");
        // 3) Lo imprimimos para copiarlo luego en data-test.sql
        //System.out.println("BCrypt hash de 'pass123': " + hash);

        SpringApplication.run(ConectamenteApplication.class, args);
    }

}
