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
public class Vie{
    private int id;
    private String wordVie;
    private int idtype;
    private String desVie;
    
    public Vie() {
    }

    public Vie(int id, String wordVie, int idtype, String desVie) {
        this.id = id;
        this.wordVie = wordVie;
        this.idtype = idtype;
        this.desVie = desVie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordVie() {
        return wordVie;
    }

    public void setWordVie(String wordVie) {
        this.wordVie = wordVie;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public String getDesVie() {
        return desVie;
    }

    public void setDesVie(String desVie) {
        this.desVie = desVie;
    }

    @Override
    public String toString() {
        return "Vie{" + "id=" + id + ", wordVie=" + wordVie + ", idtype=" + idtype + ", desVie=" + desVie + '}';
    }
    
}
