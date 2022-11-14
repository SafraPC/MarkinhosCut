package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Services;
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

public class ServiceDao extends CallDatabase {



    public boolean handleChangeServiceStatus(boolean changeTo, int serviceId) throws SQLException {
        String query = "CALL changeServiceStatus("+changeTo+", "+serviceId+")";
        return callDatabase(query,"Ocorreu um erro ao alterar o status do serviço!");
    }

    public boolean handleEditService(String name, double price, int serviceId) throws SQLException{
        String query = "CALL editService('"+name+"', "+price+","+serviceId+")";
        return callDatabase(query,"Ocorreu um erro ao alterar um serviço!");
    }

    public boolean handleCreateService(String name, double price) throws SQLException{
        String query = "CALL createService('"+name+"', "+price+")";
        return callDatabase(query,"Houve um erro ao criar um novo serviço!");
    }

    public ArrayList<Services> getListServices() throws SQLException {
        Connection connection = null;
        ResultSet result;
        Statement statement;
        ArrayList<Services> services = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM Service");
            Services service;
            while (result.next()) {
                service = new Services(result.getString("serviceName"),
                        result.getDouble("price"),
                        result.getBoolean("isActive"),
                        result.getInt("serviceId")
                        );
                services.add(service);
            }

        } catch (SQLException ex) {
            DialogMessage.errorMessage("Erro!","Houve um erro ao buscar os serviços!");
        } finally {
            connection.close();
        }
        return services;
    }
}
