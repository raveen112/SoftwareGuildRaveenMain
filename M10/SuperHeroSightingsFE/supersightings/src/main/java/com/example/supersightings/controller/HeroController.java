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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    Set<ConstraintViolation<Hero>> violations = new HashSet<>();

    @GetMapping("supers")
    public String displayHero(Model model) {
        List<Hero> supers = heroDao.getAllHero();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganization();

        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("supers", supers);
        model.addAttribute("errors", violations);

        return "supers";
    }

    @PostMapping("addHero")
    public String addHero(Hero hero, BindingResult result, HttpServletRequest request, @RequestParam("superImageToSave") MultipartFile file, RedirectAttributes redirect) throws IOException {

        String superPowerId = request.getParameter("id");
        String[] organizationIds = request.getParameterValues("orgId");
        String heroDescription = request.getParameter("description");
        hero.setSuperPower(superpowerDao.getSuperpowerById(Integer.parseInt(superPowerId)));

        List<Organization> organizations = new ArrayList<>();

        if (organizationIds != null) {

            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
        }else {
            FieldError error = new FieldError("hero", "organizations", "Must Include one organization.");
            result.addError(error);
        }
        hero.setDescription(heroDescription);
        hero.setOrganization(organizations);
        hero.setSuperImage(file.getBytes());

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            heroDao.addHero(hero);
        }
        return "redirect:/supers";
    }

    @GetMapping("deleteSupe")
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
        String heroName = request.getParameter("name");

        hero.setSuperPower(superpowerDao.getSuperpowerById(Integer.parseInt(superPowerId)));

        List<Organization> organizations = new ArrayList<>();

        for (String organizationId : organizationIds) {
            organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
        }
        hero.setId(Integer.parseInt(request.getParameter("id")));
        hero.setName(heroName);
        hero.setDescription(heroDescription);
        hero.setOrganization(organizations);
        heroDao.updateHero(hero);

        return "redirect:/supers";
    }

    @GetMapping("heroDetails")
    public String heroDetail(Integer id, Model model) {

        Hero hero = heroDao.getHeroById(id);
        model.addAttribute("hero", hero);
        return "heroDetails";
    }

    @GetMapping("supers/{id}/image")
    public void renderSuperImage(@PathVariable String id, HttpServletResponse response, Model model) throws IOException {
        Hero hero = heroDao.getHeroById(Integer.parseInt(id));
        byte[] imageData = hero.getSuperImage();
        String getImageData = Base64.getMimeEncoder().encodeToString(imageData);
        model.addAttribute("imageData", getImageData);

        response.setContentType("image/jpg");
        InputStream is = new ByteArrayInputStream(hero.getSuperImage());
        IOUtils.copy(is, response.getOutputStream());
    }

}
