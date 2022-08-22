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

    @PostMapping("addRequest")
    public String addIssue(HttpServletRequest request) {

        String associate = request.getParameter("login");
        String complaint = request.getParameter("complaint");
        String status = request.getParameter("status");
        String date = request.getParameter("date");

        Issue issue = new Issue();
        issue.setComplaint(complaint);
        issue.setAssociate(issueDao.getAssociateForIssue(Integer.parseInt(associate)));
        issue.setStatus(getBoolean(status));    

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        issue.setDate(LocalDate.parse(date, formatter));

        issueDao.addIssue(issue);

        return "redirect:/issues";
    }
}
