package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ResultChartsDao {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public ArrayList<ResultCharts> getListTotalDay() throws SQLException {
        ArrayList<ResultCharts> listResultCharts = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        result = statement.executeQuery("SELECT sellingDate, sum(total) as totalDate FROM selling group by sellingDate;");
        ResultCharts resultCharts;
        while (result.next()) {
            resultCharts = new ResultCharts(result.getDouble("totalDate"),
                    result.getDate("sellingDate"));
            listResultCharts.add(resultCharts);
        }
        return listResultCharts;
    }
}
