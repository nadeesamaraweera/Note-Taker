package com.example.noteTaker.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {


    public static String createNote(){
        return "NOTE "+ UUID.randomUUID().toString();
    }

    public static String createUserId(){
        return "USER-"+UUID.randomUUID();
    }

    public static String toBase64ProfilePic(String ProfilePic){
        return Base64.getEncoder().encodeToString(ProfilePic.getBytes());
    }
}



//mapping
//note details