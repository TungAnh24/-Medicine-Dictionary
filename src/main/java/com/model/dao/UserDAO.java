/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model.dao;

import com.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author hadt2
 */
public class UserDAO extends DbConnect {

    //login:
    public User authenticate(String username, String hashedPassword) {
        User user = null;
        try {
            conn = this.conn();
            String query = "SELECT * FROM user WHERE userName = ? AND password = ? AND status = 1";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, hashedPassword);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("userName"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(hashedPassword);
                user.setAuthority(rs.getInt("authority"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return user;
    }

    //Check userName
    public User checkUsername(String username) {
        User user = null;
        try {
            conn = this.conn();
            String query = "SELECT * FROM user WHERE userName = ? AND status = 1";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return user;
    }

    //Check passWord
    public User checkpassWord(String passWord) {
        User user = null;
        try {
            conn = this.conn();
            String query = "SELECT * FROM user WHERE password = ? AND status = 1";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, passWord);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("userName"));
                user.setPassword(passWord);
                user.setAuthority(rs.getInt("authority"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return user;
    }

    //Get user list:
    public List<User> getAllUser() {
        List<User> list = null;
        try {
            conn = this.conn();
            String query = "SELECT * FROM user";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("userName"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setAuthority(rs.getInt("authority"));
                list.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return list;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            conn = this.conn();
            String query = "SELECT * FROM user WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            user = new User();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("userName"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setAuthority(rs.getInt("authority"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return user;
    }

    //Check duplicated username:
    public boolean isDuplicatedUser(String userName) {
        boolean result = false;
        try {
            conn = this.conn();
            String query = "SELECT userName FROM user WHERE userName = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, userName);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return result;
    }

    //update password:
    public boolean changePassword(int id, String newPass) {
        boolean result = false;
        try {
            conn = this.conn();
            String query = "UPDATE user SET password = ? WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, newPass);
            pstm.setInt(2, id);
            result = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, null);
        }
        return result;
    }

    //Check duplicated phone:
    public boolean isDuplicatePhone(int id, String phone) {
        boolean result = false;
        try {
            conn = this.conn();
            String query = "SELECT phone FROM user WHERE phone = ? AND id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, phone);
            pstm.setInt(2, id);
            rs = pstm.executeQuery();
            if (!rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return result;
    }

    //Change phone:
    public boolean changePhone(int id, String newPhone) {
        boolean result = false;
        try {
            conn = this.conn();
            String query = "UPDATE user SET phone = ? WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, newPhone);
            pstm.setInt(2, id);
            result = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, null);
        }
        return result;
    }

    public boolean addNewUser(User user) {
        boolean result = false;
        try {
            conn = this.conn();
            String query = "INSERT INTO user SET "
                    + "userName = ?, "
                    + "password = ?, "
                    + "phone = ?, "
                    + "authority = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getPhone());
            pstm.setInt(4, user.getAuthority());
            result = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database access error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, null);
        }
        return result;
    }

    public boolean updateUserStatus(int id, int status) {
        boolean check = false;
        try {
            conn = this.conn();
            pstm = conn.prepareStatement("UPDATE user SET status = ? WHERE id = ?");
            pstm.setInt(1, status);
            pstm.setInt(2, id);
            check = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

}
