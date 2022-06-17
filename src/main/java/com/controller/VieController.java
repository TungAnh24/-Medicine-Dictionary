/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.dto.VieDTO;
import com.model.Vie;
import com.model.dao.VieDAO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Nguyen Huu Tung
 */
public class VieController {

    static VieDAO vieDAO = new VieDAO();
    
        public static VieDTO VieModelToDTO(Vie v){
        if (v == null) {
            return null;
        }
        VieDTO vdto = new VieDTO();
        vdto.setId(v.getId());
        vdto.setWordVie(v.getWordVie());
        vdto.setIdtype(v.getIdtype());
        vdto.setDesVie(v.getDesVie());
        return vdto;
    }
    
    public static List<VieDTO> displayVies(){
        return vieDAO.getAll().stream()
                .map(v -> VieModelToDTO(v))
                .collect(Collectors.toList());
    }
    
    public boolean addVie(String wordVie, int idtype, String desVie){
        Vie v = new Vie();
        v.setWordVie(wordVie);
        v.setIdtype(idtype);
        v.setDesVie(desVie);
        return vieDAO.insertVie(v);
    }
    
    public boolean update(String id, String wordVie, int idtype, String desVie){
        Vie v = new Vie();
        v.setId(Integer.parseInt(id));
        v.setWordVie(wordVie);
        v.setIdtype(idtype);
        v.setDesVie(desVie);
        return vieDAO.updateVie(v);
    }
    
    public boolean deleteVie(String id){
        Vie v = new Vie();
        v.setId(Integer.parseInt(id));
        return vieDAO.deleteVie(id);
    }
    
    public List<VieDTO> searchVie(String keywordVie){
        Vie vie = new Vie();
        vie.setWordVie(keywordVie);
        return vieDAO.searchVie(keywordVie).stream()
                .map(v -> VieModelToDTO(v))
                .collect(Collectors.toList());
    }
    
    public List<VieDTO> getVieBywordEng(String wordEng){
        return vieDAO.getVieBywordEng(wordEng).stream()
                .map(v -> VieModelToDTO(v))
                .collect(Collectors.toList());
    }
    
    public Vie getViebyId(String id){
        return vieDAO.getViebyId(id);
    }
    
    public static void main(String[] args) {
        VieController vctl = new VieController();
        
        Vie v1 = new Vie(0, "testcontrler", 7, "testCLT");
        vctl.addVie("Huy", 3, "ngu vkl");
//        vctl.deleteVie("2");
//        List<VieDTO> vies  = vctl.getVieBywordEng("s");
//        vies.stream().forEach(v -> System.out.println(v));
        List<VieDTO> list = vctl.displayVies();
        list.stream().forEach(v -> System.out.println(v));
    }
}
