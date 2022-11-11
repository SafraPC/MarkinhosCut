package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;

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

    public boolean handleEditProfessional(String name, String cpf, int professionalId) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            String query = "CALL editProfessional('"+name+"', "+cpf+","+professionalId+")";
            result = statement.executeQuery(query);
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

    public boolean handleCreateProfessional(String name, String cpf) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL createProfessional('"+name+"', "+cpf+")");
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
