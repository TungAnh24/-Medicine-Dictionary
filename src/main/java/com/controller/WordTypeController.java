/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.dto.WordTypeDTO;
import com.model.WordType;
import com.model.dao.WordTypeDAO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Nguyen Huu Tung
 */
public class WordTypeController {

    static WordTypeDAO wtdao = new WordTypeDAO();

    public static WordTypeDTO WTModelToDTO(WordType wt) {
        if (wt == null) {
            return null;
        }
        WordTypeDTO wtdto = new WordTypeDTO();
        wtdto.setId(wt.getId());
        wtdto.setTypenameVie(wt.getTypenameVie());
        wtdto.setTypenameEng(wt.getTypenameEng());
        return wtdto;
    }

    public List<WordTypeDTO> displayWT() {
        return wtdao.getAll().stream()
                .map(wt -> WTModelToDTO(wt))
                .collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        WordTypeController wtctl = new WordTypeController();
        List<WordTypeDTO> list;
        list = wtctl.displayWT();
        list.stream().forEach(wt -> System.out.println(wt));
    }
}
