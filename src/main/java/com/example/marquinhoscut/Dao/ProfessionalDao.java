package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.ServicesDB.CallDatabase;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessionalDao extends CallDatabase {
    public boolean handleChangeProfessionalStatus(boolean changeTo, int professionalId) throws SQLException {
        String query = "CALL changeProfessionalStatus("+changeTo+", "+professionalId+")";
        return callDatabase(query,"Houve um erro ao alterar o status do profissional");
    }

    public boolean handleEditProfessional(String name, String cpf, int professionalId) throws SQLException{
       String query = "CALL editProfessional('"+name+"', "+cpf+","+professionalId+")";
       return callDatabase(query,"Houve um erro ao editar o profissional");
    }

    public boolean handleCreateProfessional(String name, String cpf) throws SQLException{
        String query = "CALL createProfessional('"+name+"', "+cpf+")";
        return callDatabase(query,"Houve um erro ao criar o profissional");
    }

    public ArrayList<Professional> getListProfessional() throws SQLException {
        ArrayList<Professional> professionals = new ArrayList<>();

        Connection connection = null;
        ResultSet result;
        Statement statement;

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
            DialogMessage.errorMessage("Erro!","Houve um erro ao listar os profissionais");
        } finally {
            connection.close();
        }
        return professionals;
    }
}
