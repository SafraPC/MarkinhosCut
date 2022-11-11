package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentMethodDao {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public ArrayList<PaymentMethod> getListPaymentMethod() throws SQLException {
        ArrayList<PaymentMethod> listpaymentMethod = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM PaymentMethod;");
            PaymentMethod paymentMethod;
            while (result.next()) {
                paymentMethod = new PaymentMethod(result.getString("paymentName"));
                listpaymentMethod.add(paymentMethod);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaymentMethodDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
        return listpaymentMethod;
    }
}
