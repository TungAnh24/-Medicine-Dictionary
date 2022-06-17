/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.WordType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Huu Tung
 */
public class WordTypeDAO extends DbConnect {

    public List<WordType> getAll() {
        List<WordType> wtList = null;
        try {
            conn = this.conn();
            String query = "select * from wordtype";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            wtList = new ArrayList<>();
            while (rs.next()) {
                WordType wt = new WordType(
                        rs.getInt("id"),
                        rs.getString("typenameVie"),
                        rs.getString("typenameEng")
                );
                wtList.add(wt);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.closeAll(conn, pstm, rs);
        }
        return wtList;
    }

    public static void main(String[] args) {
        WordTypeDAO wtdao = new WordTypeDAO();
        List<WordType> list;
        list = wtdao.getAll();
        list.stream().forEach(wt -> System.out.println(wt));
    }
}
