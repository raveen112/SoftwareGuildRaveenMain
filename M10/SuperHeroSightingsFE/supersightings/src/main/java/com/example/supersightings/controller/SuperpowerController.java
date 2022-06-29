/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.controller;

import com.example.supersightings.dao.HeroDao;
import com.example.supersightings.dao.LocationDao;
import com.example.supersightings.dao.OrganizationDao;
import com.example.supersightings.dao.SightingDao;
import com.example.supersightings.dao.SuperpowerDao;
import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Organization;
import com.example.supersightings.model.Superpower;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ravee
 */
@Controller
public class SuperpowerController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @GetMapping("superpowers")
    public String displaySuperpower(Model model) {
        List<Superpower> powers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", powers);
        return "superpowers";
    }
    
    @PostMapping("addSuperpower")
    public String addSuperpower(Superpower superpower, HttpServletRequest request){
        superpowerDao.addSuperpower(superpower);
        return "redirect:/superpowers";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer id) {
        superpowerDao.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }
    
    @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model){
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        model.addAttribute("superpowers", superpower);
        return "editSuperpower";
        
    }
    
    @PostMapping("editSuperpower")
    public String performEditSuperpower(Integer id, HttpServletRequest request){
        
        Superpower superpower = superpowerDao.getSuperpowerById(id);

        superpower.setName(request.getParameter("name"));
        superpowerDao.updateSuperpower(superpower);

        return "redirect:/superpowers";

    }

}
