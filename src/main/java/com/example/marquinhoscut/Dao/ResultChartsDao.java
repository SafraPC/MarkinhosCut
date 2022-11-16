package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Dialog;

import java.sql.*;
import java.util.ArrayList;

public class ResultChartsDao {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public ArrayList<ResultCharts> getListTotalDay(String dateInitial, String dateFinal, String professsional, String paymentMethod) throws SQLException {
        ArrayList<ResultCharts> listResultCharts = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        try {
            professsional = professsional != null ? professsional : "";
            paymentMethod = paymentMethod != null ? paymentMethod : "";
            String query = "CALL getResultCharts('"+dateInitial+"','"+dateFinal+"','%"+professsional+"%','%"+paymentMethod+"%');";
            result = statement.executeQuery(query);
            ResultCharts resultCharts;
            while (result.next()) {
                resultCharts = new ResultCharts(result.getDouble("totalDate"),
                        result.getDate("sellingDate"));
                listResultCharts.add(resultCharts);
            }
            return listResultCharts;
        }catch (Exception error){
            DialogMessage.errorMessage("Erro!","Não foi possível listar os resultados!");
        }
        return listResultCharts;
    }
}
