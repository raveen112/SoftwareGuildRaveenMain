/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.controller;

import com.example.VoiceOfTOM.dao.AssociateDao;
import com.example.VoiceOfTOM.dao.IssueDao;
import com.example.VoiceOfTOM.model.Associate;
import com.example.VoiceOfTOM.model.Issue;
import static java.lang.Boolean.getBoolean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class IssueController {

    @Autowired
    IssueDao issueDao;

    @Autowired
    AssociateDao associateDao;

    @GetMapping("issues")
    public String displayIssues(Model model) {
        List<Issue> issues = issueDao.getAllIssues();
        List<Associate> associates = associateDao.getAllAssociates();
        model.addAttribute("issues", issues);
        model.addAttribute("associate", associates);

        return "issues";
    }

    @GetMapping("createIssue")
    public String showCreateIssue(Integer id, Model model) {
        List<Issue> issues = issueDao.getAllIssues();
        List<Associate> associates = associateDao.getAllAssociates();
        model.addAttribute("issues", issues);
        model.addAttribute("associate", associates);

        return "createIssue";
    }

    @PostMapping("addRequest")
    public String addIssue(HttpServletRequest request) {

        String login = request.getParameter("login");
        String complaint = request.getParameter("complaint");
        String status = request.getParameter("status");
        String date = request.getParameter("dateString");
        
        Associate associate = associateDao.getAssociateByLogin(login);
        
        Issue issue = new Issue();
        issue.setComplaint(complaint);
        issue.setAssociate(associate);
        issue.setStatus(getBoolean(status));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        issue.setDate(LocalDate.parse(date, formatter));

        issueDao.addIssue(issue);

        return "redirect:/issues";
    }
    
    @GetMapping("deleteIssue/{id}")
    public String deleteIssue(HttpServletRequest request, @PathVariable Integer id) {
       
        issueDao.deleteIssueById(id);

        return "redirect:/issues";
    }
    
    @GetMapping("allIssues")
    public String showDeleteIssue(Integer id, Model model) {
        List<Issue> issues = issueDao.getAllIssues();
        List<Associate> associates = associateDao.getAllAssociates();
        model.addAttribute("issues", issues);
        model.addAttribute("associate", associates);

        return "allIssues";
    }

}
