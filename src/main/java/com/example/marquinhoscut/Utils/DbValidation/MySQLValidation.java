package com.example.marquinhoscut.Utils.DbValidation;

import java.sql.ResultSet;

public class MySQLValidation {

    public static boolean NO_UPDATED_ROWS(ResultSet result){
        try{
            if(result.toString().contains("update count of 0") || result == null){
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
