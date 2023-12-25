package com.example.movie_example_01;
import java.sql.Connection;
import java.sql.DriverManager;

public class database {


    public static Connection connectDb(){

        try{
            Class.forName("org.postgresql.Driver");

            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "1234");

            return connect;

        }catch(Exception e){e.printStackTrace();}

        return null;
    }
}
