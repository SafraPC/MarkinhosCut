package com.example.marquinhoscut.Dao;

import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.ServicesDB.CallDatabase;
import com.example.marquinhoscut.ServicesDB.DatabaseConnection;
import com.example.marquinhoscut.Utils.DbValidation.MySQLValidation;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SellingDao extends CallDatabase {
    Connection connection;
    ResultSet result;
    Statement statement ;

    public int handleSelling (String cpf, String payment, double total, String date) throws SQLException {
        try{
            int registeredItem = -1;
            String query = "CALL createSelling('"+cpf+"', '"+payment+"', "+total+", '"+date+"')";
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if(MySQLValidation.HAS_ERRORS(result.toString())){
                return registeredItem;
            }
            if(result != null){
                while(result.next()){
                    registeredItem = Integer.parseInt(result.getString("createdElementId"));
                }
                DialogMessage.successMessage("Sucesso!","Venda registrada com sucesso!");
                return registeredItem;
            }
            DialogMessage.errorMessage("Erro!","Houve um erro ao registrar a venda.");
            return registeredItem;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            if(MySQLValidation.HAS_ERRORS(ex.getMessage())){
                return -1;
            }
            DialogMessage.errorMessage("Erro!","Houve um erro ao registrar a venda.");
            return -1;
        }finally {
            connection.close();
        }
    }

    public boolean handleSellingService (int id, int serviceId, int qtd, double price) throws SQLException {
        String query = "CALL createQtdSellingService("+id+", "+serviceId+", "+qtd+", "+price+")";
        return callDatabase(query,"Erro ao registrar quantidade de serviços!","");
    }


    public ArrayList<Selling> getListSelling(String dateInitial, String dateFinal, String professsional, String paymentMethod) throws SQLException {
        ArrayList<Selling> listSelling = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        try {
            professsional = professsional != null ? professsional : "";
            paymentMethod = paymentMethod != null ? paymentMethod : "";
            String query = "CALL getRegisterSelling('"+dateInitial+"','"+dateFinal+"','%"+professsional+"%','%"+paymentMethod+"%');";
            System.out.println(query);
            result = statement.executeQuery(query);
            Selling selling;
            while (result.next()) {
                selling = new Selling(result.getInt("sellingId"),
                        result.getString("professionalName"),
                        result.getString("paymentName"),
                        result.getDouble("total"),
                        result.getDate("sellingDate"));
                listSelling.add(selling);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellingDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listSelling;
    }
    
    public ArrayList<Selling> getDetailedListSelling(String dateInitial, String dateFinal, String professsional, String paymentMethod) throws SQLException {
        ArrayList<Selling> listSelling = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        try {
            professsional = professsional != null ? professsional : "";
            paymentMethod = paymentMethod != null ? paymentMethod : "";
            String query = "CALL getRegisterSellingDetailed('"+dateInitial+"','"+dateFinal+"','%"+professsional+"%','%"+paymentMethod+"%');";
            System.out.println(query);
            result = statement.executeQuery(query);
            Selling selling;
            while (result.next()) {
                selling = new Selling(result.getInt("sellingId"),
                        result.getString("professionalName"),
                        result.getString("paymentName"),
                        result.getDate("sellingDate"),
                        result.getString("serviceName"),
                        result.getDouble("price"),
                        result.getInt("quantity"));
                listSelling.add(selling);
            }
            
        } catch (SQLException ex) {
            System.out.println("AQUI???");
            System.out.println(ex.getMessage());
            Logger.getLogger(SellingDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listSelling;
    }
    
}
