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
public class VieDTO {

    private int id;
    private String wordVie;
    private int idtype;
    private String idtypeDescription;
    private String desVie;

    public VieDTO() {
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
        this.idtypeDescription = 
                idtype == 1 ? "Danh từ" :
                idtype == 2 ? "Động từ" :
                idtype == 3 ? "Tính từ" :
                idtype == 4 ? "Từ hạn định" :
                idtype == 5 ? "Trạng từ" :
                idtype == 6 ? "Đại từ" :
                idtype == 7 ? "Giới từ" :
                idtype == 8 ? "Liên từ" : 
                idtype == 9 ? "Thán từ" : "";
    }
    
    public String getIdtypeDescription() {
        return idtypeDescription = 
                idtype == 1 ? "Danh từ" :
                idtype == 2 ? "Động từ" :
                idtype == 3 ? "Tính từ" :
                idtype == 4 ? "Từ hạn định" :
                idtype == 5 ? "Trạng từ" :
                idtype == 6 ? "Đại từ" :
                idtype == 7 ? "Giới từ" :
                idtype == 8 ? "Liên từ" : 
                idtype == 9 ? "Thán từ" : "";
    }

    public String getDesVie() {
        return desVie;
    }

    public void setDesVie(String desVie) {
        this.desVie = desVie;
    }

    public VieDTO(int id, String wordVie, int idtype, String desVie) {
        this.id = id;
        this.wordVie = wordVie;
        this.idtype = idtype;
        this.idtypeDescription = 
                idtype == 1 ? "Danh từ" :
                idtype == 2 ? "Động từ" :
                idtype == 3 ? "Tính từ" :
                idtype == 4 ? "Từ hạn định" :
                idtype == 5 ? "Trạng từ" :
                idtype == 6 ? "Đại từ" :
                idtype == 7 ? "Giới từ" :
                idtype == 8 ? "Liên từ" : 
                idtype == 9 ? "Thán từ" : "trống";
        this.desVie = desVie;
    }

    @Override
    public String toString() {
        return "VieDTO{" + "id=" + id + ", wordVie=" + wordVie + ", idtypeDescription=" + idtypeDescription + ", desVie=" + desVie + '}';
    }
    
}
