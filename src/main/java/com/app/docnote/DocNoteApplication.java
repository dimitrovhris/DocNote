package com.app.docnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class DocNoteApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DocNoteApplication.class, args);
    }

}
