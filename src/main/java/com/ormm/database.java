/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ormm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class database {
    protected Connection con;
    protected Statement st;
    
    public database(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbo","root","");
            st = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NYALAKAN MYSQL DAHULU");
        }
    }
}
