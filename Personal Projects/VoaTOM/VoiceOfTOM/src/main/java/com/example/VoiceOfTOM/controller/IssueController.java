/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.controller;

import com.example.VoiceOfTOM.dao.IssueDao;
import com.example.VoiceOfTOM.model.Issue;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ravee
 */
@Controller
public class IssueController {
    
    @Autowired
    IssueDao issueDao;
    
    @GetMapping("issues")
    public String displayIssues(Model model){
        List<Issue> issues = issueDao.getAllIssues();
        model.addAttribute("issue", issues);
        return "issues";
    }
}
