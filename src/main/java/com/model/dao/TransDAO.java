/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.Eng;
import com.model.Trans;
import com.model.Vie;
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
public class TransDAO extends DbConnect {

    public List<Trans> getAll() {
        List<Trans> transList = null;
        try {
            conn = this.conn();
            transList = new ArrayList<>();
            String query = "select * from trans";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Trans t = new Trans(
                        rs.getInt("idVie"),
                        rs.getInt("idEng")
                );
                transList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return transList;
    }

    public boolean insertTrans(Trans t) {
        if (isDuplicateVieTrans(t.getIdVie()) == isDuplicateEngTrans(t.getIdEng())) {
            JOptionPane.showMessageDialog(null, "Cặp từ điển " + t.getIdVie() + "-" + t.getIdEng() + " đã tồn tại.", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean check = false;
        try {
            conn = this.conn();
            String query = "insert into trans(idVie, idEng) values (?, ?)";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, t.getIdVie());
            pstm.setInt(2, t.getIdEng());
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean addTrans(String idV, String idE){
        
            boolean check = true;
        try {
            conn = this.conn();
            String query = "insert into trans(idVie, idEng) values (?, ?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, idV);
            pstm.setString(2, idE);
            check = pstm.executeUpdate() > 0;
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(TransDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

//    public boolean testt(String idV, String idE) {
//        boolean ch = true;
//        try {
//            conn = this.conn();
//        } catch (SQLException ex) {
//            Logger.getLogger(TransDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public boolean updateTrans(Trans t) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "update trans set idVie = ?, idEng = ? where idVie = ?";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, t.getIdVie());
            pstm.setInt(2, t.getIdEng());
            pstm.setInt(3, t.getIdVie());
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TransDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public List<Trans> searchtoVie(String idVie) {
        List<Trans> transList = null;
        try {
            transList = new ArrayList<>();
            conn = this.conn();
            String query = "select * from trans where idVie like?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, idVie);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Trans t = new Trans(
                        rs.getInt("idVie"),
                        rs.getInt("idEng")
                );
                transList.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return transList;
    }

    public List<Trans> searchtoEng(String idEng) {
        List<Trans> transList = null;
        try {
            transList = new ArrayList<>();
            conn = this.conn();
            String query = "select * from trans where idEng like?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, idEng);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Trans t = new Trans(
                        rs.getInt("idVie"),
                        rs.getInt("idEng")
                );
                transList.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return transList;
    }

    public boolean deleteTransByVie(String idVie) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "delete from trans where idVie = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, idVie);
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean deleteTransByEng(String idEng) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "delete from trans where idEng = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, idEng);
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean isDuplicateVieTrans(int idVie) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "select idVie from trans where idVie = ?";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, idVie);
            rs = pstm.executeQuery();
            check = rs.isBeforeFirst();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean isDuplicateEngTrans(int idEng) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "select idEng from trans where idEng = ?";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, idEng);
            rs = pstm.executeQuery();
            check = rs.isBeforeFirst();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public List<Eng> getEngById(String id) {
        List<Eng> engList = null;
        try {
            conn = this.conn();
            String query = "SELECT e.id, e.wordEng, e.idtype, e.desEng\n"
                    + "FROM trans\n"
                    + "INNER JOIN eng e ON e.id = trans.idEng\n"
                    + "WHERE e.id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            engList = new ArrayList<>();
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

    public List<Vie> getVieBywordEng(String wordEng) {
        List<Vie> vieList = null;

        try {
            conn = this.conn();
            String query = "SELECT v.id, v.wordVie, v.idtype, v.desVie\n"
                    + "FROM eng e\n"
                    + "INNER JOIN trans ON trans.idEng = e.id\n"
                    + "INNER JOIN vie v ON trans.idVie = v.id\n"
                    + "WHERE e.wordEng like concat('%', ? , '%')";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, wordEng);
            rs = pstm.executeQuery();
            vieList = new ArrayList<>();
            while (rs.next()) {
                Vie v = new Vie(
                        rs.getInt("id"),
                        rs.getString("wordVie"),
                        rs.getInt("idtype"),
                        rs.getString("desVie")
                );
                vieList.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return vieList;
    }

    public static void main(String[] args) throws SQLException {
        TransDAO tdao = new TransDAO();

        List<Trans> list;
        List<Vie> list1;
        Trans t1 = new Trans(2, 9);
//        tdao.insertTrans(t1);
        tdao.addTrans("2", "9");
//        tdao.deleteTransByVie("2");
//        tdao.deleteTransByVie("2");
//        list1 = tdao.getVieBywordEng("s");
//        list1.stream().forEach(t -> System.out.println(t));

        list = tdao.getAll();
        list.stream().forEach(a -> System.out.println(a));
    }
}
