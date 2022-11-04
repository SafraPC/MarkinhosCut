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
    public ArrayList<Professional> getListProfessional() {

        ArrayList<Professional> professionals = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        try {
            con = Markinhos_cutDbConnect.getConnection();
            String sql = "SELECT * FROM funcionario;";
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);

            Professional p;
            while (rs.next()) {
                p = new Professional(rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getBoolean("ativo"));
                professionals.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfessionalDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (con != null) {
                    con.close();
                }

                if (st != null) {
                    st.close();
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfessionalDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return professionals;
    }
}
