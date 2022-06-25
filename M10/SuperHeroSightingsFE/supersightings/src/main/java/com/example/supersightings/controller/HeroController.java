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
import com.example.supersightings.model.Location;
import com.example.supersightings.model.Superpower;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ravee
 */
@Controller
public class HeroController {

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

    @GetMapping("supers")
    public String displayLocations(Model model) {
        List<Hero> supers = heroDao.getAllHero();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("supers", supers);
        return "supers";
    }

    @PostMapping("addHero")
    public String addLocation(Hero hero, HttpServletRequest request) {
        String superPowerId = request.getParameter("superpower.id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        hero.setSuperPower(superpowerDao.getSuperpowerById(Integer.parseInt(superPowerId)));
        hero.setDescription(description);
        hero.setName(name);
        heroDao.addHero(hero);
        return "redirect:/supers";
    }

}
