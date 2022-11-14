package com.example.marquinhoscut.Utils.DbValidation;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import java.sql.ResultSet;

public class MySQLValidation {

    public static boolean NO_UPDATED_ROWS(ResultSet result){
        try{
            if(result.toString().contains("update count of 0") || result == null){
                DialogMessage.errorMessage("Erro!","Houve um erro e nada foi alterado.");
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean ALREADY_EXISTS(ResultSet result){
        try{

            return false;
        }catch (Exception err){
            return true;
        }
    }

    public static boolean HAS_ERRORS(ResultSet result){
        try{
            if(ALREADY_EXISTS(result)){
                return true;
            }
            if(NO_UPDATED_ROWS(result)){
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
