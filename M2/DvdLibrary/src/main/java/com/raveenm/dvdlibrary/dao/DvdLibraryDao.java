/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.dvdlibrary.dao;

import com.raveenm.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface DvdLibraryDao {
    
    DVD addDvd (String title, DVD headLine) throws DvdLibraryDaoException ;
    DVD removeDvd (String title) throws DvdLibraryDaoException ;
    DVD getTitle(String title) throws DvdLibraryDaoException ;
    List<DVD> getAllDVD() throws DvdLibraryDaoException ;
    DVD editDVD(DVD dvd) throws DvdLibraryDaoException;
    
    // Edit specific;
    // Load file from TXT (Bufferred reader)
    
    
}
