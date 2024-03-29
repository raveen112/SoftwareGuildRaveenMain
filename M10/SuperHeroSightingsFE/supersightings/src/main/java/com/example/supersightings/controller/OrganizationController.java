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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
public class OrganizationController {

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

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Hero> heroes = heroDao.getAllHero();
        List<Organization> organizations = organizationDao.getAllOrganization();

        model.addAttribute("heroes", heroes);

        model.addAttribute("organizations", organizations);
        
        model.addAttribute("errors", violations);

        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization organization, HttpServletRequest request) {

        List<Hero> members = heroDao.getMembersForOrganization(organization);
        for (Hero hero : members) {
            System.out.println(hero.getName());
        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {
            organizationDao.addOrganization(organization);
        }
        return "redirect:/organizations";

    }

    @GetMapping("organizationDetails")
    public String organizationDetail(Integer id, Model model) {
        Organization organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organizationDetails";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organization organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {
        Organization organization = new Organization();

        organization.setId(Integer.parseInt(request.getParameter("id")));
        organization.setName(request.getParameter("name"));
        organization.setAddress(request.getParameter("address"));
        organization.setDescription(request.getParameter("description"));
        organizationDao.updateOrganization(organization);

        return "redirect:/organizations";
    }

    // path variable reads the integer from the route
    @GetMapping("deleteOrganization/{id}")
    public String deleteOrganization(HttpServletRequest request, @PathVariable Integer id) {
        
        organizationDao.deleteOrganizationById(id);

        return "redirect:/organizations";
    }
}
