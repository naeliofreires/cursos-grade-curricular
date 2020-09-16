package com.cursos.client.escola.grandecurricular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GrandeCurricularApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return  applicationBuilder.sources(GrandeCurricularApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GrandeCurricularApplication.class, args);
    }

}
