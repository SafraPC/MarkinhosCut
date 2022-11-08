package com.example.marquinhoscut.ServicesDB;


import com.example.marquinhoscut.App;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectMySQL extends DatabaseLinks {
    public ConnectMySQL(){
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.port = 3306;
        this.server = "localhost";
        this.db = "Markinhos_cut";
        this.user = "root";
        this.password = App.getDbPassword();
    }

    @Override
    public java.sql.Connection getConnection() {

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(getURL(), user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ConnectMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ConnectMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    @Override
    public String getURL() {
        return "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.db
                + "?useTimezone=true&serverTimezone=UTC";
    }
}
