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
import com.example.supersightings.model.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
public class SightingController {

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

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Hero> heroes = heroDao.getAllHero();
        List<Location> locations = locationDao.getAllLocations();
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("heroes", heroes);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request){
        String superId = request.getParameter("superPeople");
        String locationId = request.getParameter("location");
        String date = request.getParameter("dateString");
        
        Sighting sighting = new Sighting();
        sighting.setHero(heroDao.getHeroById(Integer.parseInt(superId)));
        sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        sighting.setDate(LocalDate.parse(date, formatter));
        sightingDao.addSighting(sighting);
        
        return "redirect:/sightings";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting(int id){
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @GetMapping("sightingDetails")
    public String sightingDetails(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sightings", sighting);
        return "sightingDetails";
    }
    
    @GetMapping("/") //Go to index html page
    public String recentSightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        
        List<Sighting> recentSightings = sightings.stream()
                .sorted(Comparator.comparing(Sighting::getDate).reversed()) //order by date from most recent to oldest
                .limit(10) //get the 10 first sightings
                .collect(Collectors.toList());        

        model.addAttribute("sightings", recentSightings);
        
        return "index"; //returning "sightings" means we will need a sightings.html file to push our data to
    }
       
    @GetMapping("editSighting")
    public String editSighting (Integer id, Model model){
        List<Hero> heroes = heroDao.getAllHero();
        List<Location> locations = locationDao.getAllLocations();
        Sighting sighting = sightingDao.getSightingById(id);
        
        if(model.getAttribute("sightings")!=null){
            ((Sighting) model.getAttribute("sightings")).setHero(sighting.getHero());
            ((Sighting) model.getAttribute("sightings")).setLocation(sighting.getLocation());
        }
        
        model.addAttribute("sightings", model.getAttribute("sightings") != null ? model.getAttribute("sightings"): sighting);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        model.addAttribute("date", sighting.getDate());
        model.addAttribute("sightings", sighting); 
        return "editSighting";
    }
    
    // need to debug
    
    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request, Model model){
        Sighting sighting = new Sighting();
        String superId = request.getParameter("heroId");
        String locationId = request.getParameter("locationId");
        sighting.setHero(sightingDao.getHeroForSighting(Integer.parseInt(superId)));
        sighting.setLocation(sightingDao.getLocationForSighting(Integer.parseInt(locationId)));
        
        sightingDao.updateSighting(sighting);
        return "redirect:/sightings";
    }
}
