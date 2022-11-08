package com.example.marquinhoscut.ServicesDB;

import java.sql.Connection;

public class DatabaseConnection {
    public static Connection getConnection(){
        return new ConnectMySQL().getConnection();
    }
}
