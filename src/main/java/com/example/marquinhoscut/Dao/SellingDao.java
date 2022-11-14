package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SellingDao {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public boolean handleSelling (String cpf, String payment, double total, String date) throws SQLException {
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL createSelling('"+cpf+"', '"+payment+"', "+total+", '"+date+"')");
            System.out.println(result.toString());
            if(MySQLValidation.NO_UPDATED_ROWS(result)){
                return false;
            }
            return true;
        }catch(SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
            return false;
        }finally {
            connection.close();
        }
    }

    public boolean handleSellingService (int id, int serviceId, int qtd, double price) throws SQLException {
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL createQtdSellingService("+id+", "+serviceId+", "+qtd+", "+price+")");
            if(MySQLValidation.NO_UPDATED_ROWS(result)){
                return false;
            }
            return true;
        }catch(SQLException ex){
            return false;
        }finally {
            connection.close();
        }
    }

    public ArrayList<ResultCharts> getListTotalDay() throws SQLException{
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
