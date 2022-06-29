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
import com.example.supersightings.model.Organization;
import com.example.supersightings.model.Superpower;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String displayHero(Model model) {
        List<Hero> supers = heroDao.getAllHero();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganization();

        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("supers", supers);

        return "supers";
    }

    @PostMapping("addHero")
    public String addHero(Hero hero, HttpServletRequest request) {
        String superPowerId = request.getParameter("id");
        String[] organizationIds = request.getParameterValues("orgId");
        String heroDescription = request.getParameter("description");
        hero.setSuperPower(superpowerDao.getSuperpowerById(Integer.parseInt(superPowerId)));

        List<Organization> organizations = new ArrayList<>();

        for (String organizationId : organizationIds) {
            organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
        }

        hero.setDescription(heroDescription);
        hero.setOrganization(organizations);

        heroDao.addHero(hero);
        return "redirect:/supers";
    }

    @GetMapping("deleteHero")
    public String deleteHero(int id) {
        heroDao.deleteHeroById(id);
        return "redirect:/supers";
    }

    @GetMapping("editSuper")
    public String editHero(Integer id, Model model) {
        Hero hero = heroDao.getHeroById(id);
        List<Organization> organizations = organizationDao.getAllOrganization();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("hero", hero);

        return "editSuper";
    }

    @PostMapping("editSuper")
    public String performEditHero(HttpServletRequest request, Model model) {
        Hero hero = new Hero();
        String superPowerId = request.getParameter("superpowerId");
        String[] organizationIds = request.getParameterValues("orgId");
        String heroDescription = request.getParameter("description");

        hero.setSuperPower(superpowerDao.getSuperpowerById(Integer.parseInt(superPowerId)));

        List<Organization> organizations = new ArrayList<>();

        for (String organizationId : organizationIds) {
            organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
        }

        hero.setDescription(heroDescription);
        hero.setOrganization(organizations);
        heroDao.updateHero(hero);

        return "redirect:/supers";
    }

}
