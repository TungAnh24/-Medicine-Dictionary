/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

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
public class VieDAO extends DbConnect {

    public List<Vie> getAll() {
        List<Vie> vieList = null;
        try {
            conn = this.conn();
            String query = "select * from vie";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            vieList = new ArrayList<>();
            while (rs.next()) {
                Vie vie = new Vie(
                        rs.getInt("id"),
                        rs.getString("wordVie"),
                        rs.getInt("idtype"),
                        rs.getString("desVie")
                );
                vieList.add(vie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return vieList;
    }

    public Vie getViebyId(String id) {
        Vie v = null;
        try {
            conn = this.conn();
            String query = "select * from vie where id=?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                v = new Vie(
                        rs.getInt("id"),
                        rs.getString("wordVie"),
                        rs.getInt("idtype"),
                        rs.getString("desVie")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return v;
    }

    public boolean insertVie(Vie v) {
        if (isDuplicateVie(v.getWordVie())) {
            JOptionPane.showMessageDialog(null, "" + v.getWordVie() + " đã có trong từ điển.", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean check = false;
        try {
            conn = this.conn();
            String query = "insert into vie(wordVie, idtype, desVie) values (?, ?, ?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, v.getWordVie());
            pstm.setInt(2, v.getIdtype());
            pstm.setString(3, v.getDesVie());
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean updateVie(Vie v) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "update vie set "
                    + "wordVie = ?,"
                    + "idtype = ?,"
                    + "desVie = ?"
                    + "where id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, v.getWordVie());
            pstm.setInt(2, v.getIdtype());
            pstm.setString(3, v.getDesVie());
            pstm.setInt(4, v.getId());
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public boolean deleteVie(String id) {
        boolean check = true;
        try {
            conn = this.conn();
            String query = "delete from vie where id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, Integer.parseInt(id));
            check = pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Thuật ngữ này đã được định nghĩa Tiếng anh", "Xóa thất bại", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
    }

    public List<Vie> searchVie(String keywordVie) {
        List<Vie> vieList = null;
        try {
            conn = this.conn();
            vieList = new ArrayList<>();
            String query = "select * from vie where wordVie like?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, "%" + keywordVie + "%");
            rs = pstm.executeQuery();
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

    public boolean isDuplicateVie(String wordVie) {
        boolean check = false;
        try {
            conn = this.conn();
            String query = "select wordVie from vie where wordVie = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, wordVie);
            rs = pstm.executeQuery();
            check = rs.isBeforeFirst();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return check;
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

    public static void main(String[] args) {
        VieDAO vdao = new VieDAO();
        Vie v1 = new Vie(1, "Linh", 5, "abc");
//        vdao.updateVie(v1);
        vdao.insertVie(v1);
        List<Vie> list;
        list = vdao.getAll();
        list.stream().forEach(v -> System.out.println(v));
    }
}
