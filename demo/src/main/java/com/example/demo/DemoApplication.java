package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@RestController
@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/wordladder")
    @ResponseBody

    public String wordladder(String w1,String w2) throws FileNotFoundException {
        wordladder englishwords = new wordladder();
        return  englishwords.main(w1,w2);
        }

    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class,args);
    }

}
