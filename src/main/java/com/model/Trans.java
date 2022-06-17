/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Nguyen Huu Tung
 */
public class Trans {
    private int idVie;
    private int idEng;

    public Trans() {
    }

    public Trans(int idVie, int idEng) {
        this.idVie = idVie;
        this.idEng = idEng;
    }

    public int getIdVie() {
        return idVie;
    }

    public void setIdVie(int idVie) {
        this.idVie = idVie;
    }

    public int getIdEng() {
        return idEng;
    }

    public void setIdEng(int idEng) {
        this.idEng = idEng;
    }
    
    @Override
    public String toString() {
        return "Trans{" + "idVie=" + idVie + ", idEng=" + idEng + '}';
    }
    
}
