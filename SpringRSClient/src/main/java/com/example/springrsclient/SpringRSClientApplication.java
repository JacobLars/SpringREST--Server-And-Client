package com.example.springrsclient;

import com.example.springrsclient.management.Management;
import com.example.springrsclient.model.Person;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringRSClientApplication {

    static Management management = new Management();

    public static void main(String[] args) {
        management.runProgram();
    }

}
