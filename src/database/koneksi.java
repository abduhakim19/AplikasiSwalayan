package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author hakim
 */
public class koneksi {
    private Connection conn;
    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/swalayantest","root","");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +ex);
        }
        return conn;
    }
}
