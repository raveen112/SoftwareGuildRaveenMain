/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.dao;

import com.example.VoiceOfTOM.model.Associate;
import com.example.VoiceOfTOM.model.Issue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ravee
 */
@Repository
public class IssueDaoImpl implements IssueDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Issue getIssueById(int id) {
         try {
            final String GET_ISSUE_BY_ID = "SELECT * FROM issues WHERE issue_id= ?";
            Issue issue = jdbc.queryForObject(GET_ISSUE_BY_ID, new IssueMapper(), id);
            issue.setAssociate(getAssociateForIssue(id));
            return issue;
        } catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
   // Helper methods
    @Override
    public Associate getAssociateForIssue(int id){
        final String GET_ASSOCIATE_FOR_ISSUE = "SELECT * FROM associate "
                + "WHERE id = (SELECT i.id FROM issues i WHERE i.issue_id = ?)";
         return jdbc.queryForObject(GET_ASSOCIATE_FOR_ISSUE, new AssociateDaoImpl.AssociateMapper(), id);
    }
    
        
    @Override
    public List<Issue> getAllIssues() {
        final String GET_ALL_ISSUES = "SELECT * FROM issues";
        List<Issue> issues = jdbc.query(GET_ALL_ISSUES, new IssueMapper());
        
        for(Issue issue : issues){
            issue.setAssociate(getAssociateForIssue(issue.getIssue_id()));
            
        }
        return issues;
    }

    @Override
    @Transactional
    public Issue addIssue(Issue issue) {
        final String INSERT_ISSUE = "INSERT INTO issues(complaint, date, status, id) VALUES(?, ?, ?, ?);";
        
        jdbc.update(INSERT_ISSUE, 
                issue.getComplaint(),
                issue.getDate(),
                issue.getStatus(),
                issue.getAssociate().getAssociate_id()
                );
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        issue.setIssue_id(newId);
        return issue;
        
    }

    @Override
    public void updateIssue(Issue issue) {
        final String UPDATE_ISSUE = "UPDATE issues SET complaint=?, date=?, status=? WHERE issue_id =?";
        jdbc.update(UPDATE_ISSUE, 
                issue.getComplaint(),
                issue.getDate(),
                issue.getStatus(),
                issue.getIssue_id());
    }

    @Override
    public void deleteIssueById(int id) {
      final String DELETE_ISSUE = "DELETE FROM issues WHERE issue_id=?" ; 
      jdbc.update(DELETE_ISSUE, id);
    }

    @Override
    public List<Issue> getIssuesByAssociate(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public static final class IssueMapper implements RowMapper<Issue> {

        @Override
        public Issue mapRow(ResultSet rs, int index) throws SQLException {
            Issue issue = new Issue();
            issue.setIssue_id(rs.getInt("issue_id"));
            issue.setComplaint(rs.getString("complaint"));
            issue.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            issue.setStatus(rs.getBoolean("status"));
           
            return issue;
        }
    }

}
