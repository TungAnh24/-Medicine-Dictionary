/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Huu Tung
 */
public class DbConnect {

    private final String userName = "root";
    private final String password = "";
    private final String connString = "jdbc:mysql://localhost:3306/meddict";
    protected Connection conn = null;
    protected ResultSet rs = null;
    protected PreparedStatement pstm = null;

    //Tạo kết nối
    public Connection conn() throws SQLException {
        return DriverManager.getConnection(connString, userName, password);
    }

    //Đóng kết nối Statement và ResultSet
    public void closeAll(Connection conn, Statement stm, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
