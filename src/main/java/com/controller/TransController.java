/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.dto.TransDTO;
import com.model.Trans;
import com.model.Vie;
import com.model.dao.TransDAO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Nguyen Huu Tung
 */
public class TransController {

    static TransDAO transDAO = new TransDAO();

    public static TransDTO TransModelToDTO(Trans t) {
        if (t == null) {
            return null;
        }
        TransDTO tdto = new TransDTO();
        tdto.setIdVie(t.getIdVie());
        tdto.setIdEng(t.getIdVie());
        return tdto;
    }

    public static List<TransDTO> displayTrans() {
        return transDAO.getAll().stream()
                .map(t -> TransModelToDTO(t))
                .collect(Collectors.toList());
    }
    
    public boolean insert(String idVie, String idEng){
        Trans t = new Trans();
        t.setIdVie(Integer.parseInt(idEng));
        t.setIdEng(Integer.parseInt(idEng));
        return transDAO.insertTrans(t);
    }
    
    public boolean addTrans(String idVie, String idEng){
        Trans t = new Trans();
        t.setIdVie(Integer.parseInt(idEng));
        t.setIdEng(Integer.parseInt(idEng));
        return transDAO.addTrans(idVie, idEng);
    }
    
    public List<Vie> getVieBywordEng(String wordEng){
        return transDAO.getVieBywordEng(wordEng);
    }
    public static void main(String[] args) {
        TransController tctl = new TransController();
        Trans t1 = new Trans(2, 10);
        tctl.addTrans("2", "10");
//        List<Vie> list = tctl.getVieBywordEng("walk");
//        list.stream().forEach(v -> System.out.println(v));
        List<TransDTO> listDTO = tctl.displayTrans();
        listDTO.stream().forEach(t -> System.out.println(t));
    }
}
