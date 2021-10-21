/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.dvdlibrary.controller;

import com.raveenm.dvdlibrary.dao.DvdLibraryDao;
import com.raveenm.dvdlibrary.dao.DvdLibraryDaoException;
import com.raveenm.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.raveenm.dvdlibrary.dto.DVD;
import com.raveenm.dvdlibrary.ui.DvdLibraryView;
import com.raveenm.dvdlibrary.ui.UserIO;
import com.raveenm.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author ravee
 */
public class DvdLibraryController {
      private UserIO io = new UserIOConsoleImpl();
      private DvdLibraryView view;
      private DvdLibraryDao dao ;

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
        while (keepGoing) {
            
            menuSelection = getMenuSelection();
          

            switch (menuSelection) {
                case 1:
                    addDvd();
                    break;
                case 2:
                    removeDvd();
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    getSingleDvd();
                    break;
                
                case 5:
                    getAllDvd();
                    break;                  
                
                case 6:
                    keepGoing = false;
                    break;
                              
                default:
                    unknownCommand();
            }

        }
        exitBanner();
    }catch (DvdLibraryDaoException  e){
        view.displayErrorMessage(e.getMessage());
        
    }
    }
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displaySuccessfullyCreatedBanner();
        
    }
    
    private void getAllDvd() throws DvdLibraryDaoException {
        view.displayAllBanner();
        List<DVD> dvdList = dao.getAllDVD();
        view.displayAllDvds(dvdList);
    }
    
    private void getSingleDvd() throws DvdLibraryDaoException {
        view.displayGetSingleDvdBanner();
        String dvdTitle = view.getDvdChoice();
        DVD library = dao.getTitle(dvdTitle);
        view.displayDvd(library);
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdChoice();
        DVD removeDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removeDvd);
    }
    
    private void unknownCommand(){
        view.displayUnkownCommandBanner();
    }
    
    private void exitBanner(){
        view.displayExitBanner();
    }
    
    public DvdLibraryController (DvdLibraryDao dao, DvdLibraryView view){
        this.dao = dao;
        this.view = view;
    }
    
    public void editDvd() throws DvdLibraryDaoException{
        view.displayEditDvdBanner();
        DVD dvdToEdit;
        String titleToEdit = view.getDvdChoiceToEdit();
        
        dvdToEdit = dao.getTitle(titleToEdit);
        if(dvdToEdit != null){
            DVD editedDvd = view.getEditedDvdInfo(dvdToEdit);
        dao.editDVD(editedDvd);
        }else {
            view.displayEditDvdErrorBanner();
        }
            
    }
}
