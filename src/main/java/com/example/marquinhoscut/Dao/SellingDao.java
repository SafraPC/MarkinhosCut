package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SellingDao {
    Connection connection;
    ResultSet result;
    Statement statement ;

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
