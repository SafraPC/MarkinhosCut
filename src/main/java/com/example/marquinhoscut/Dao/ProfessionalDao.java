package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.ServicesDB.Markinhos_cutDbConnect;

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
        Connection con = null;
        ResultSet result = null;
        Statement statement = null;

        try {
            con = Markinhos_cutDbConnect.getConnection();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM funcionario;");

            Professional p;
            while (result.next()) {
                p = new Professional(result.getString("nome"),
                        result.getString("cpf"),
                        result.getBoolean("ativo"));
                professionals.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfessionalDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           con.close();
        }
        return professionals;
    }
}
