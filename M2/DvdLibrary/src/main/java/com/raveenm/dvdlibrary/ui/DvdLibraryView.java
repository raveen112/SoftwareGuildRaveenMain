/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.dvdlibrary.ui;

import com.raveenm.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author ravee
 */
public class DvdLibraryView {
    
    private UserIO io ;
            
        public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit existing DVD");
        io.print("4. Search for DVD");
        io.print("5. List DVD Collection");
        io.print("6. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 6);
        
      }
        
        public DVD getNewDvdInfo(){
            String title = io.readString("Please enter title: ");
            String releaseDate = io.readString("Please enter the release date: ");
            String mpaaRating = io.readString("Pleas enter the MPAA rating: ");
            String directorsName = io.readString("Please enter the directors name: ");
            String studio  = io.readString("Please enter the studio: ");
            String userRating = io.readString("Please enter your rating along with comments (Rating - comments): ");
            
            DVD currentDvd = new DVD(title);
            
            
            currentDvd.setReleaseDate(releaseDate);
            currentDvd.setMpaaRating(mpaaRating);
            currentDvd.setDirectorName(directorsName);
            currentDvd.setStudio(studio);
            currentDvd.setUserRating(userRating);
            
            return currentDvd;
        }
        
        public void displayAddDvdBanner(){
            io.print("=== ADD DVD ===");
    }   
        
        public void displaySuccessfullyCreatedBanner(){
            io.readString("DVD successfully added. Please hit enter to continue.");
        }
        
        public void displayAllBanner(){
            io.print("=== All DVDs ===");
        }
        
        public void displayAllDvds(List<DVD> dvdList){
            for (DVD currentDVD : dvdList ){
                String dvdInfo = String.format("%s (%s) \nMPAA Rating:%s \nDirector: %s  \nStudio:%s \nUser Rating: %s\n ", 
                        currentDVD.getTitle(),
                        currentDVD.getReleaseDate(),
                        currentDVD.getMpaaRating(),
                        currentDVD.getDirectorName(),
                        currentDVD.getStudio(),
                        currentDVD.getUserRating());
                
                io.print(dvdInfo);
            }io.readString("Please hit enter to continue.");
        }
        
        public void displayGetSingleDvdBanner(){
            io.print("=== DISPLAY DVD ====");
            
        }
        
        public String getDvdChoice(){
            return io.readString("Please enter the title you wish to find:  ");
        }
        
         public String getDvdChoiceToEdit(){
            return io.readString("Please enter the title you wish to edit:  ");
        }
        
        public void displayDvd(DVD library){
            if(library != null){
                io.print("Title: " + library.getTitle());
                io.print("Release Date: "+library.getReleaseDate() + "\nDirector: "+ library.getDirectorName()+ "\nStudio: " + library.getStudio());
                io.print("MPAA Rating: "+ library.getMpaaRating()+ "\nUser Rating/Comments: " + library.getUserRating());
               
            }else{
                io.print("DVD not found.");
            }
            io.readString("Please hit enter to continue. ");                   
        }
        
        public void displayRemoveDvdBanner(){
            io.print("=== Remove DVD ===");
        }
        
        public void displayRemoveResult (DVD dvdRecord){
            if (dvdRecord != null){
                io.print("DVD removed successfully");
            }else{
                io.print("DVD not found.");
            }io.readString("Please hit enter to continue.");
        }
        public void displayExitBanner(){
            io.print("Good Bye!!");
        }
        
        public void displayUnkownCommandBanner(){
            io.print("Unknown Command!");
        }
        
        public DvdLibraryView(UserIO io){
            this.io=io;
        }
        
        public void displayErrorMessage(String errorMsg){
            io.print("=== ERROR ===");
            io.print(errorMsg);
        }
        
       public DVD getEditedDvdInfo(DVD dvdToEdit){
           io.print("Previous Director: "+ dvdToEdit.getDirectorName());
           dvdToEdit.setDirectorName(io.readString("Enter new Director's name: "));
           io.print("Previous MPAA: "+ dvdToEdit.getMpaaRating());
           dvdToEdit.setMpaaRating(io.readString("Enter new MPPA rating: "));
           io.print("Previous Release Date: "+ dvdToEdit.getReleaseDate());
           dvdToEdit.setReleaseDate(io.readString("Enter new release date: "));
           io.print("Previous Studio: "+ dvdToEdit.getStudio());
           dvdToEdit.setStudio(io.readString("Enter the new studio: "));
           io.print("Previous User Rating: "+ dvdToEdit.getUserRating());
           dvdToEdit.setUserRating(io.readString("Enter the new user rating: "));
           return dvdToEdit;
       }
       
       public void displayEditDvdErrorBanner(){
            displayErrorMessage("DVD not found. ");
            io.readString("Please hit enter to continue.");
       }
       
       public void displayEditDvdBanner(){
           io.print("=== EDIT DVD ===");
       }
}

