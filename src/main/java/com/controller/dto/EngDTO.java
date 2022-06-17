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
public class EngDTO {
    private int id;
    private String wordEng;
    private int idtype;
    private String idtypeDescription;
    private String desEng;

    public EngDTO() {
    }

    public EngDTO(int id, String wordEng, int idtype, String desEng) {
        this.id = id;
        this.wordEng = wordEng;
        this.idtype = idtype;
        this.idtypeDescription = 
                idtype == 1 ? "Noun" :
                idtype == 2 ? "Verd" :
                idtype == 3 ? "Adjactive" :
                idtype == 4 ? "Determiner" :
                idtype == 5 ? "Adverb" :
                idtype == 6 ? "Pronoun" :
                idtype == 7 ? "Preposition" :
                idtype == 8 ? "Conjunction" : 
                idtype == 9 ? "Interjection" : "";
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
        this.idtypeDescription = 
                idtype == 1 ? "Noun" :
                idtype == 2 ? "Verd" :
                idtype == 3 ? "Adjactive" :
                idtype == 4 ? "Determiner" :
                idtype == 5 ? "Adverb" :
                idtype == 6 ? "Pronoun" :
                idtype == 7 ? "Preposition" :
                idtype == 8 ? "Conjunction" : 
                idtype == 9 ? "Interjection" : "";
    }

    public String getIdtypeDescription() {
        return this.idtypeDescription = 
                idtype == 1 ? "Noun" :
                idtype == 2 ? "Verd" :
                idtype == 3 ? "Adjactive" :
                idtype == 4 ? "Determiner" :
                idtype == 5 ? "Adverb" :
                idtype == 6 ? "Pronoun" :
                idtype == 7 ? "Preposition" :
                idtype == 8 ? "Conjunction" : 
                idtype == 9 ? "Interjection" : "";
    }

    public String getDesEng() {
        return desEng;
    }

    public void setDesEng(String desEng) {
        this.desEng = desEng;
    }

    @Override
    public String toString() {
        return "EngDTO{" + "id=" + id + ", wordEng=" + wordEng + ", idtypeDescription=" + idtypeDescription + ", desEng=" + desEng + '}';
    }
    
}
