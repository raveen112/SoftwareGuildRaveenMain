package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author ravee
 */
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao {

    private HashMap<String, Tax> stateTaxes = new HashMap<>();

    private String STATE_TAX_FILE;
    private String DELIMITER = ",";

    public FlooringMasteryTaxDaoFileImpl() {
        this.STATE_TAX_FILE = "Data/Taxes.txt";
    }

    public FlooringMasteryTaxDaoFileImpl(String stateTaxFilePath) {
        this.STATE_TAX_FILE = stateTaxFilePath;
    }

    public List<Tax> getAllStateTaxes() throws OrderPersistenceException {
        loadTaxes();
        return stateTaxes.values().stream().collect(Collectors.toList());
    }

    public Tax getStateTax(String stateAbbreviation) throws OrderPersistenceException {
        loadTaxes();
        return stateTaxes.get(stateAbbreviation);

    }

    private void loadTaxes() throws OrderPersistenceException {

        stateTaxes.clear();

        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(STATE_TAX_FILE)));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                Tax nextStateTax = unmarshallTaxes(scanner.nextLine());
                stateTaxes.put(nextStateTax.getStateAbbreviation(), nextStateTax);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException("Could not load tax file");
        }
    }

    private Tax unmarshallTaxes(String taxString) {
        String[] fieldArray = taxString.split(DELIMITER);
        String stateAbbreviation = fieldArray[0];
        String stateName = fieldArray[1];
        BigDecimal taxRate = new BigDecimal(fieldArray[2]).setScale(2, RoundingMode.HALF_UP);

        return new Tax(stateAbbreviation, stateName, taxRate);
    }
}
