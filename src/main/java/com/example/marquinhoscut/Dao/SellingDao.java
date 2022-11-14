package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.ServicesDB.CallDatabase;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SellingDao extends CallDatabase {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public boolean handleSelling (String cpf, String payment, double total, String date) throws SQLException {
        String query = "CALL createSelling('"+cpf+"', '"+payment+"', "+total+", '"+date+"')";
        return callDatabase(query,"Houve um erro ao realizar a venda!","Sucesso ao cadastrar nova venda!");
    }

    public boolean handleSellingService (int id, int serviceId, int qtd, double price) throws SQLException {
        String query = "CALL createQtdSellingService("+id+", "+serviceId+", "+qtd+", "+price+")";
        return callDatabase(query,"","");
    }

    public ArrayList<ResultCharts> getListTotalDay() throws SQLException{
        ArrayList<ResultCharts> listResultCharts = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        result = statement.executeQuery("SELECT sellingDate, sum(total) as totalDate FROM Selling group by sellingDate;");
        ResultCharts resultCharts;
        while (result.next()) {
            resultCharts = new ResultCharts(result.getDouble("totalDate"),
                    result.getDate("sellingDate"));
            listResultCharts.add(resultCharts);
        }
        return listResultCharts;
    }

    public ArrayList<Selling> getListSelling() throws SQLException {
        ArrayList<Selling> listSelling = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM Selling;");
            Selling selling;
            while (result.next()) {
                selling = new Selling(result.getInt("sellingId"),
                        result.getString("cpf"),
                        result.getString("paymentName"),
                        result.getDouble("total"),
                        result.getDate("sellingDate"));
                listSelling.add(selling);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellingDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
        return listSelling;
    }
}
