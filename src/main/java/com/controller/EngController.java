/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.dto.EngDTO;
import com.model.Eng;
import com.model.dao.EngDAO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Nguyen Huu Tung
 */
public class EngController {
    
    static EngDAO engDAO = new EngDAO();
    
    public static EngDTO EngModelToDTO(Eng e) {
        if (e == null) {
            return null;
        }
        EngDTO edto = new EngDTO();
        edto.setId(e.getId());
        edto.setWordEng(e.getWordEng());
        edto.setIdtype(e.getIdtype());
        edto.setDesEng(e.getDesEng());
        return edto;
    }
    
    public static List<EngDTO> displayEngs() {
        return engDAO.getAll().stream()
                .map(e -> EngModelToDTO(e))
                .collect(Collectors.toList());
    }
    
    public boolean addEng(String wordEng,int idtype, String desEng) {
        Eng e = new Eng();
        e.setWordEng(wordEng);
        e.setIdtype(idtype);
        e.setDesEng(desEng);
        return engDAO.insertEng(e);
    }
    
    public boolean updateEng(String id, String wordEng, int idtype, String desEng){
        Eng e = new Eng();
        e.setId(Integer.parseInt(id));
        e.setWordEng(wordEng);
        e.setIdtype(idtype);
        e.setDesEng(desEng);
        return engDAO.updateEng(e);
    }
    
    public boolean deleteEng(String id){
        Eng e = new Eng();
        e.setId(Integer.parseInt(id));
        return engDAO.deleteEng(id);
    }
    
    public List<EngDTO> searchEng(String keywordEng){
        return engDAO.searchEng(keywordEng).stream()
                .map(e -> EngModelToDTO(e))
                .collect(Collectors.toList());
    }
    
    public List<EngDTO> getEngBywordVie(String wordEng){
        return engDAO.getEngBywordVie(wordEng).stream()
                .map(edto -> EngModelToDTO(edto))
                .collect(Collectors.toList());
    }
    
    public Eng getEngbyId(String id){
        return engDAO.getEngbyId(id);
    }
    
    public static void main(String[] args) {
        
        EngController ectl = new EngController();
//        ectl.addEng("walk", 0, "test");
        List<EngDTO> vies  = ectl.getEngBywordVie("n");
        vies.stream().forEach(e -> System.out.println(e));
//        engs.stream().forEach(e -> System.out.println(e));
//        List<EngDTO> list = displayEngs();
//        list.forEach(e -> System.out.println(e));
    }
}
