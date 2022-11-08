package com.example.marquinhoscut.ServicesDB;

public abstract class DatabaseLinks {
    protected java.sql.Connection con;
    protected String server,db,user,password,driver;
    protected int port;

    public abstract java.sql.Connection getConnection();

    public abstract String getURL();
}
