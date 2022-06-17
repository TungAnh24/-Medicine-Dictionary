/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.dto;

/**
 *
 * @author Nguyen Huu Tung
 */
public class WordTypeDTO {
    private int id;
    private String typenameVie;
    private String typenameEng;

    public WordTypeDTO() {
    }

    public WordTypeDTO(int id, String typenameVie, String typenameEng) {
        this.id = id;
        this.typenameVie = typenameVie;
        this.typenameEng = typenameEng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypenameVie() {
        return typenameVie;
    }

    public void setTypenameVie(String typenameVie) {
        this.typenameVie = typenameVie;
    }

    public String getTypenameEng() {
        return typenameEng;
    }

    public void setTypenameEng(String typenameEng) {
        this.typenameEng = typenameEng;
    }

    @Override
    public String toString() {
        return "WordTypeDTO{" + "id=" + id + ", typenameVie=" + typenameVie + ", typenameEng=" + typenameEng + '}';
    }
    
}
