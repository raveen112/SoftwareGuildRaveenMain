/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.dao;

import com.example.VoiceOfTOM.model.Associate;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface AssociateDao {

    Associate getAssociateById(int id);

    List<Associate> getAllAssociates();

    Associate addAssociate(Associate Associate);

    void updateAssociate(Associate Associate);

    void deleteAssociateById(int id);


}
