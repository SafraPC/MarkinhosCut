package com.example.marquinhoscut.ServicesDB;

import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CallDatabase {

    Connection connection;
    ResultSet result;
    Statement statement ;


    public boolean callDatabase(String query,String errorMessage) throws SQLException {
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if(MySQLValidation.HAS_ERRORS(result)){
                return false;
            }
            return true;
        }catch(SQLException ex){
            DialogMessage.errorMessage("Erro!",errorMessage);
            return false;
        }finally {
            connection.close();
        }
    }
}
