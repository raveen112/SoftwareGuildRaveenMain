/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Material;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class FlooringMasteryMaterialsDaoFileImpl implements FlooringMasteryMaterialsDao {

    private HashMap<String, Material> products;
    private final String PRODUCT_FILE;
    private String DELIMITER = ",";

    public FlooringMasteryMaterialsDaoFileImpl() {
        this.PRODUCT_FILE = "src/main/resources/Data/Products.txt";
    }

    public FlooringMasteryMaterialsDaoFileImpl(String productFilePath) {
        this.PRODUCT_FILE = productFilePath;
    }

    // unmarshall materials model 
    private Material unmarshallProduct(String materialString) {
        String[] fieldArray = materialString.split(DELIMITER);
        String productType = fieldArray[0];
        BigDecimal costPerSquareFoot = new BigDecimal(fieldArray[1]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(fieldArray[2]).setScale(2, RoundingMode.HALF_UP);

        return new Material(productType, costPerSquareFoot, laborCostPerSquareFoot);
    }

    // load materials model
    private void loadProducts() throws OrderPersistenceException{
        
        try{
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
            
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            while(scanner.hasNextLine()){
                Material nextProduct = unmarshallProduct(scanner.nextLine());
                
            }
            
            scanner.close();
        }catch(FileNotFoundException e){
            throw new OrderPersistenceException("Could not load product file :/ ");
        }
        
        
    }

    @Override
    public Material getProductType(String productType) {
        return products.get(productType);

    }

    @Override
    public List<Material> getallProductTypes() {
       return null;
    }

//    @Override
//    public Material addProduct(Material product) {
//        return products.put(product.getProductType(), product);
//    }


}
