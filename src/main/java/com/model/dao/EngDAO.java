/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.Eng;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Huu Tung
 */
public class EngDAO extends DbConnect {

    public List<Eng> getAll() {
        List<Eng> engList = null;
        try {
            conn = this.conn();
            String query = "select * from eng";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            engList = new ArrayList<>();
            while (rs.next()) {
                Eng e = new Eng(
                        rs.getInt("id"),
                        rs.getString("wordEng"),
                        rs.getInt("idtype"),
                        rs.getString("desEng"));
                engList.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EngDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return engList;
    }
    
    public Eng getEngbyId(String idtype){
        Eng e = null;
        try {
            conn = this.conn();
            String query = "select * from eng where id =?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, idtype);
            rs = pstm.executeQuery();
            if (rs.next()) {                
                e = new Eng(
                        rs.getInt("id"),
                        rs.getString("wordEng"),
                        rs.getInt("idtype"),
                        rs.getString("desEng")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return e;
    }

    public boolean insertEng(Eng e) {
        if (isDuplicateVie(e.getWordEng())) {
            JOptionPane.showMessageDialog(null, "" + e.getWordEng() + " is in the dictionary.", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean check = false;
        try {
            conn = this.conn();
            String query = "insert into eng(wordEng, idtype, desEng) values (?, ?, ?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, e.getWordEng());
            pstm.setInt(2, e.getIdtype());
            pstm.setString(3, e.getDesEng());
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EngDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean updateEng(Eng e) {
        if (isDuplicateVie(e.getWordEng())) {
            JOptionPane.showMessageDialog(null, "" + e.getWordEng() + " is in the dictionary.", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean check = false;
        try {
            conn = this.conn();
            String query = "update eng set wordEng = ?, idtype = ?, desEng = ? where id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, e.getWordEng());
            pstm.setInt(2, e.getIdtype());
            pstm.setString(3, e.getDesEng());
            pstm.setInt(4, e.getId());
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EngDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean deleteEng(String id) {
        boolean check = true;
        try {
            conn = this.conn();
            String query = "delete from eng where id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, Integer.parseInt(id));
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "This term has been defined in English.", "Deletion failed", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public List<Eng> searchEng(String keywordEng) {
        List<Eng> engList = null;
        try {
            conn = this.conn();
            engList = new ArrayList<>();
            String query = "select * from eng where wordEng like?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, "%" + keywordEng + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                Eng e = new Eng(
                        rs.getInt("id"),
                        rs.getString("wordEng"),
                        rs.getInt("idtype"),
                        rs.getString("desEng")
                );
                engList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return engList;
    }

    public boolean isDuplicateVie(String wordEng) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "select wordEng from eng where wordEng = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, wordEng);
            rs = pstm.executeQuery();
            check = rs.isBeforeFirst();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public List<Eng> getEngBywordVie(String wordEng) {
        List<Eng> engList = null;

        try {
            conn = this.conn();
            String query = "SELECT e.id, e.wordEng, e.idtype, e.desEng\n"
                    + "FROM eng e\n"
                    + "INNER JOIN trans ON trans.idEng = e.id\n"
                    + "INNER JOIN vie v ON trans.idVie = v.id\n"
                    + "WHERE v.wordVie like concat('%', ? , '%')";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, wordEng);
            rs = pstm.executeQuery();
            engList = new ArrayList<>();
            while (rs.next()) {
                Eng v = new Eng(
                        rs.getInt("id"),
                        rs.getString("wordEng"),
                        rs.getInt("idtype"),
                        rs.getString("desEng")
                );
                engList.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return engList;
    }

    public static void main(String[] args) {
        EngDAO edao = new EngDAO();
        List<Eng> engs;
        edao.deleteEng("2");
        engs = edao.getAll();
        engs.stream().forEach(e -> System.out.println(e));
    }
}
