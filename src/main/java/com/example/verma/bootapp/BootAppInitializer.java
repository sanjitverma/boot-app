package com.example.verma.bootapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by SANJIT on 13/11/17.
 */
public class BootAppInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        builder.sources(BootAppApplication.class);
        return builder;
    }
}
