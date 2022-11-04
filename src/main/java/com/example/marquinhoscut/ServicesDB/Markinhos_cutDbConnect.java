package com.example.marquinhoscut.ServicesDB;

import java.sql.Connection;

public class Markinhos_cutDbConnect {
    public static Connection getConnection(){
        return new ConnectDbMySQL().getConnection();
    }
}
