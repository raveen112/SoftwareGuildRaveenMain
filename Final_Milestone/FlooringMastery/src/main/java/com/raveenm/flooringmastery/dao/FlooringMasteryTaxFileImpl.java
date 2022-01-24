package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Material;
import com.raveenm.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author ravee
 */
public class FlooringMasteryTaxFileImpl implements FlooringMasteryTaxDao {

    private HashMap<String, Tax> stateTaxes = new HashMap<>();
    private String STATE_TAX_FILE;
    private String DELIMITER = ",";

    public FlooringMasteryTaxFileImpl() {
        this.STATE_TAX_FILE = "src/main/resources/Data/Taxes.txt";
    }

    public FlooringMasteryTaxFileImpl(String stateTaxFilePath) {
        this.STATE_TAX_FILE = stateTaxFilePath;
    }

    public List<Tax> getAllStateTaxes() {
        return stateTaxes.values().stream().sorted(Comparator.comparing(Tax.getStateAbbreviation)).collect(Collectors.toList());
    }
    
    public Tax getStateTax(String stateAbbreviation){
        
        
    }

    private void loadTaxes() throws OrderPersistenceException {

        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(STATE_TAX_FILE)));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                Tax nextStateTax = unmarshallTaxes(scanner.nextLine());

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException("Could not load tax file");
        }
    }

    private Tax unmarshallTaxes(String taxString) {
        String[] fieldArray = taxString.split(DELIMITER);
        String stateAbbreviation = fieldArray[0];
        String stateName = fieldArray[2];
        BigDecimal taxRate = new BigDecimal(fieldArray[3]).setScale(2, RoundingMode.HALF_UP);

        return new Tax(stateAbbreviation, stateName, taxRate);
    }
}
