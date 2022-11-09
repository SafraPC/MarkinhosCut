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

    Connection connection;
    ResultSet result;
    Statement statement ;


    public boolean handleChangeProfessionalStatus(boolean changeTo, int professionalId) throws SQLException {
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL changeProfessionalStatus("+changeTo+", "+professionalId+")");
            return true;
        }catch(SQLException ex){
            return false;
        }finally {
            connection.close();
        }
    }

    public boolean handleEditProfessional(String name, String cpf, int professionalId) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            String query = "CALL editProfessional('"+name+"', "+cpf+","+professionalId+")";
            System.out.println(query);
            result = statement.executeQuery(query);
            System.out.println(result.toString());
            return true;
        }catch(SQLException ex){
            return false;
        }finally {
            connection.close();
        }
    }

    public boolean handleCreateProfessional(String name, String cpf) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL createProfessional("+name+", "+cpf+")");
            return true;
        }catch(SQLException ex){
            return false;
        }finally {
            connection.close();
        }
    }


    public ArrayList<Professional> getListProfessional() throws SQLException {
        ArrayList<Professional> professionals = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM Professional;");
            Professional professional;
            while (result.next()) {
                professional = new Professional(result.getString("professionalName"),
                        result.getString("cpf"),
                        result.getBoolean("isActive"),
                        result.getInt("professionalId"));
                professionals.add(professional);
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
