package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Services;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDao {
    public ArrayList<Services> getListServices() throws SQLException {

        ArrayList<Services> services = new ArrayList<>();
        Connection con = null;
        ResultSet result = null;
        Statement statement = null;

        try {
            con = DatabaseConnection.getConnection();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM servico");

            Services service;
            while (result.next()) {
                service = new Services(result.getString("nome"),
                        result.getDouble("preco"),
                        result.getBoolean("ativo"));
                services.add(service);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
        }
        return services;
    }
}
