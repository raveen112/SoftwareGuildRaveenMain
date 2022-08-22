/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.dao;

import com.example.VoiceOfTOM.model.Associate;
import com.example.VoiceOfTOM.model.Issue;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface IssueDao {

    Issue getIssueById(int id);

    List<Issue> getAllIssues();

    Issue addIssue(Issue issue);

    void updateIssue(Issue issue);

    void deleteIssueById(int id);
    
    // helper
    
    List<Issue> getIssuesByAssociate(int id);
    Associate getAssociateForIssue(int id);
}
