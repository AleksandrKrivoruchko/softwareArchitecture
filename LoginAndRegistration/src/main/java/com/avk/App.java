package com.avk;

import com.avk.connection.ConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
//        ConnectionManager cm = new ConnectionManager();
//        System.out.println(cm.getConnection());
    }
}
