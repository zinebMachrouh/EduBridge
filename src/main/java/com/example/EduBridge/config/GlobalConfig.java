package com.example.EduBridge.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
