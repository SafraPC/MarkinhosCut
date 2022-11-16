package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;

public class ResultChartsDao {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public ArrayList<ResultCharts> getListTotalDay(String dateInitial, String dateEnd, String professsional, String paymentMethod) throws SQLException {
        ArrayList<ResultCharts> listResultCharts = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        try {
            if(professsional == null) {
                result = statement.executeQuery("SELECT sellingDate, sum(total) as totalDate FROM selling INNER JOIN Professional using(cpf) WHERE" +
                        " sellingDate >= '"+ dateInitial +"'  AND paymentName = '"+paymentMethod+"'  group by sellingDate ;");
            } else if (dateInitial == null) {
                result = statement.executeQuery("SELECT sellingDate, sum(total) as totalDate FROM selling INNER JOIN Professional using(cpf) WHERE" +
                        " sellingDate <= '"+ dateEnd +"' AND  professionalName <= '"+ professsional +"' AND paymentName = '"+paymentMethod+"'  group by sellingDate ;");
            } else if (dateInitial == null) {
                
            } else {
                result = statement.executeQuery("SELECT sellingDate, sum(total) as totalDate FROM selling INNER JOIN Professional using(cpf) WHERE" +
                        " sellingDate >= '"+ dateInitial +"'  AND sellingDate <= '"+ dateEnd +"' AND  professionalName <= '"+ professsional +"' AND paymentName = '"+paymentMethod+"'  group by sellingDate ;");
            }
            ResultCharts resultCharts;
            while (result.next()) {
                resultCharts = new ResultCharts(result.getDouble("totalDate"),
                        result.getDate("sellingDate"));
                listResultCharts.add(resultCharts);
            }
            return listResultCharts;
        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        return listResultCharts;
    }
}
