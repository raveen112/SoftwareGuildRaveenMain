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
import com.example.supersightings.model.Location;
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
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ravee
 */
@Controller
public class LocationController {

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

    Set<ConstraintViolation<Location>> violations = new HashSet<>();

    @GetMapping("locations")
    public String displayLocations(Model model) {

        List<Location> locations = locationDao.getAllLocations();
        List<Organization> organizations = organizationDao.getAllOrganization();
        model.addAttribute("organziations", organizations);
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(Location location, HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");


        location.setName(name);
        location.setDescription(description);
        location.setAddress(address);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            locationDao.addLocation(location);
        }

        return "redirect:/locations";

    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        locationDao.deleteLocationById(id);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);

        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);

        location.setName(request.getParameter("name"));
        location.setAddress(request.getParameter("address"));
        location.setDescription(request.getParameter("description"));
        location.setLatitude(request.getParameter("latitude"));
        location.setLongitude(request.getParameter("longitude"));

        locationDao.updateLocation(location);

        return "redirect:/locations";

    }

}
