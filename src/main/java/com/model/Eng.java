/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.List;

/**
 *
 * @author Nguyen Huu Tung
 */
public class Eng {
    private int id;
    private String wordEng;
    private int idtype;
    private String desEng;
    private List<Vie> wordVie;

    public Eng() {
    }

    public Eng(int id, String wordEng, int idtype, String desEng) {
        this.id = id;
        this.wordEng = wordEng;
        this.idtype = idtype;
        this.desEng = desEng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordEng() {
        return wordEng;
    }

    public void setWordEng(String wordEng) {
        this.wordEng = wordEng;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public String getDesEng() {
        return desEng;
    }

    public void setDesEng(String desEng) {
        this.desEng = desEng;
    }

    @Override
    public String toString() {
        return "Eng{" + "id=" + id + ", wordEng=" + wordEng + ", idtype=" + idtype + ", desEng=" + desEng + '}';
    }
    
}
