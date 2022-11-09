package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessionalDao {
    public ArrayList<Professional> getListProfessional() throws SQLException {

        ArrayList<Professional> professionals = new ArrayList<>();
        Connection connection = null;
        ResultSet result;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM Professional;");

            Professional p;
            while (result.next()) {
                p = new Professional(result.getString("professionalName"),
                        result.getString("cpf"),
                        result.getBoolean("isActive"));
                professionals.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfessionalDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
        return professionals;
    }
}
