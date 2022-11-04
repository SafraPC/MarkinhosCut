package com.example.marquinhoscut.ServicesDB;

import java.sql.Connection;
public abstract class ConnectDB {
    protected Connection con;
    protected String server,db,user,password,driver;
    protected int port;

    public abstract Connection getConnection();

    public abstract String getURL();
}
