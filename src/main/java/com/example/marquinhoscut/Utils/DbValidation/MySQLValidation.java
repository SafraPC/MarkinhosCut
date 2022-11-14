package com.example.marquinhoscut.Utils.DbValidation;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import java.sql.ResultSet;

public class MySQLValidation {

    public static boolean NO_UPDATED_ROWS(String result){
        try{
            if(result == null || result.contains("update count of 0")){
                DialogMessage.errorMessage("Nada foi alterado!","Houve um erro e nada foi alterado.");
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean ALREADY_EXISTS(String result){
        try{
            if(result.toLowerCase().contains("duplicate entry")){
                DialogMessage.errorMessage("Não é permitido itens duplicados!","O registro que está tentando cadastrar já existe na base de dados!");
                return true;
            }
            return false;
        }catch (Exception err){
            return true;
        }
    }

    public static boolean HAS_ERRORS(String result){
        try{
            if(NO_UPDATED_ROWS(result)){
                return true;
            }
            if(ALREADY_EXISTS(result)){
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
