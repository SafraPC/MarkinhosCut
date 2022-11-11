package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.Services;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;

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
            if(MySQLValidation.NO_UPDATED_ROWS(result)){
                return false;
            }
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
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

    public boolean handleCreateService(String name, double price) throws SQLException{
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("CALL createService('"+name+"', "+price+")");
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
                        result.getBoolean("isActive"),
                        result.getInt("serviceId")
                        );
                services.add(service);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
        return services;
    }
}
