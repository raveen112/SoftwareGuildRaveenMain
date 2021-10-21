/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.dvdlibrary.dao;

import com.raveenm.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, DVD> library = new HashMap<>();
    public static final String ROSTER_FILE = "library.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDvd(String title, DVD dvd) throws DvdLibraryDaoException {
        loadLibrary();
        DVD newDvd = library.put(title, dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public DVD removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        DVD removeDvd = library.remove(title);
        writeLibrary();
        return removeDvd;
    }

    @Override
    public DVD getTitle(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return library.get(title);
    }

    @Override
    public List<DVD> getAllDVD() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(library.values());
    }

    //FILE WRITE
    private DVD unmarshallDvd(String DvdAsText) {

        //separates the DVD to separate at the delimiter
        String[] dvdTokens = DvdAsText.split(DELIMITER);

        String title = dvdTokens[0];

        //create a new dvd object for the dvd constructor
        DVD dvdFromFile = new DVD(title);

        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);

        return dvdFromFile;

    }

    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));

        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("-_- Could not load library data into memory.", e);
        }
        String currentLine;
        DVD currentDvd;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);

            library.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();

    }

    private String marshallDvd(DVD aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating() ;

        return dvdAsText;

    }

    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));

        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save DVD data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVD();
        for (DVD currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public DVD editDVD(DVD editedDvd) throws DvdLibraryDaoException {
        
        loadLibrary();
        String title = editedDvd.getTitle();
        library.put(title, editedDvd);
        writeLibrary();
        return editedDvd;
    }

}
