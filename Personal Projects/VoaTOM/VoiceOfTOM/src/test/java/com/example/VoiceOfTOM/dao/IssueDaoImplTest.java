/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.dao;

import com.example.VoiceOfTOM.model.Issue;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ravee
 */
@SpringBootTest
public class IssueDaoImplTest {

    public IssueDaoImplTest() {
    }

    @Autowired
    IssueDao issueDao;

    @BeforeEach
    public void setUp() {
        List<Issue> issues = issueDao.getAllIssues();
        for (Issue issue : issues) {
            issueDao.deleteIssueById(issue.getIssue_id());
        }
    }

    /**
     * Test of getIssueById method, of class IssueDaoImpl.
     */
    @Test
    public void testAddAndGetIssue() {
        Issue issue = new Issue();
        issue.setComplaint("Test Complaint");
        issue.setDate(LocalDate.now());
        issue.setStatus(Boolean.TRUE);
        issueDao.addIssue(issue);

        Issue fromDao = issueDao.getIssueById(issue.getIssue_id());

        assertEquals(issue, fromDao);
    }

    /**
     * Test of getAllIssues method, of class IssueDaoImpl.
     */
    @Test
    public void testGetAllIssues() {

        Issue issue = new Issue();
        issue.setComplaint("Test Complaint");
        issue.setDate(LocalDate.now());
        issue.setStatus(Boolean.TRUE);
        issueDao.addIssue(issue);

        Issue issue2 = new Issue();
        issue2.setComplaint("Test Complaint2");
        issue2.setDate(LocalDate.now());
        issue2.setStatus(Boolean.FALSE);
        issueDao.addIssue(issue2);

        List<Issue> issues = issueDao.getAllIssues();

        assertEquals(2, issues.size());
        assertTrue(issues.contains(issue));
        assertTrue(issues.contains(issue2));

    }

    /**
     * Test of updateIssue method, of class IssueDaoImpl.
     */
    @Test
    public void testUpdateIssue() {
        Issue issue = new Issue();
        issue.setComplaint("Test Complaint");
        issue.setDate(LocalDate.now());
        issue.setStatus(Boolean.TRUE);
        issueDao.addIssue(issue);

        Issue fromDao = issueDao.getIssueById(issue.getIssue_id());
        assertEquals(issue, fromDao);

        issue.setComplaint("New Complaint");
        issueDao.updateIssue(issue);

        assertNotEquals(issue, fromDao);

        fromDao = issueDao.getIssueById(issue.getIssue_id());

        assertEquals(issue, fromDao);

    }

}
