package com.example.noteTaker.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AppUtil {


    public static String createNote(){
        return "NOTE "+ UUID.randomUUID().toString();
    }
}

//mapping
//note details
