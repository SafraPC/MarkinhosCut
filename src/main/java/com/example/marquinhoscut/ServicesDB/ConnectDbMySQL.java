package com.example.marquinhoscut.ServicesDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDbMySQL extends ConnectDB{
    public ConnectDbMySQL(){
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.porta = 3306;
        this.servidor = "localhost";
        this.bd = "Markinhos_cut";
        this.usuario = "root";
        this.senha = "Marcelo1213";
    }

    @Override
    public Connection getConnection() {

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(getURL(), usuario, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDbMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDbMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    @Override
    public String getURL() {

        return "jdbc:mysql://" + this.servidor + ":" + this.porta + "/" + this.bd
                + "?useTimezone=true&serverTimezone=UTC";

    }
}
