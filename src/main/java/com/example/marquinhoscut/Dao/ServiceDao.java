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

    Connection connection = null;
    ResultSet result = null;
    Statement statement = null;

    public boolean handleChangeServiceStatus(boolean changeTo, int serviceId) throws SQLException {
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL changeServiceStatus("+changeTo+", "+serviceId+")");
            return true;
        }catch(SQLException ex){
            return false;
        }finally {
            connection.close();
        }
    }

    public boolean handleEditService(String name, double price, int serviceId) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            String query = "CALL editService('"+name+"', "+price+","+serviceId+")";
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

    public boolean handleCreateService(String name, double price) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL createService('"+name+"', "+price+")");
            return true;
        }catch(SQLException ex){
            return false;
        }finally {
            connection.close();
        }
    }

    public ArrayList<Services> getListServices() throws SQLException {

        ArrayList<Services> services = new ArrayList<>();


        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM Service");

            Services service;
            while (result.next()) {
                service = new Services(result.getString("serviceName"),
                        result.getDouble("price"),
                        result.getBoolean("isActive"));
                services.add(service);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return services;
    }
}
