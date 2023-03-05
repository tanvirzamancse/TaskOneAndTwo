package com.example.taskoneandtwo.utils;

public class ConstrantValue {

    private static ConstrantValue repository;


    public  static ConstrantValue getRepository( ){
        if (repository == null) {
            repository=new ConstrantValue();
        }
        return repository;
    }

    public static String BASE_URL="https://gorest.co.in/public/v2/";

}
