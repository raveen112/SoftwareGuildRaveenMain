/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao {

    private HashMap<String, Product> products = new HashMap<>();
    private final String PRODUCT_FILE;
    private String DELIMITER = ",";

    public FlooringMasteryProductDaoFileImpl() {
        this.PRODUCT_FILE = "Data/Products.txt";
    }

    public FlooringMasteryProductDaoFileImpl(String productFilePath) {
        this.PRODUCT_FILE = productFilePath;
    }

    // unmarshall materials model 
    private Product unmarshallProduct(String materialString) {
        String[] fieldArray = materialString.split(DELIMITER);
        String productType = fieldArray[0];
        BigDecimal costPerSquareFoot = new BigDecimal(fieldArray[1]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(fieldArray[2]).setScale(2, RoundingMode.HALF_UP);

        return new Product(productType, costPerSquareFoot, laborCostPerSquareFoot);
    }

    // load materials model
    private void loadProducts() throws OrderPersistenceException {

        products.clear();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                Product nextProduct = unmarshallProduct(scanner.nextLine());
                products.put(nextProduct.getProductType(), nextProduct);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException("Could not load product file :/ ");
        }

    }

    @Override
    public Product getProduct(String productType)throws OrderPersistenceException {
        loadProducts();
        return products.get(productType);

    }

    @Override
    public List<Product> getallProductTypes()throws OrderPersistenceException {
        loadProducts();
        return products.values().stream().collect(Collectors.toList());
    }

//    @Override
//    public Material addProduct(Material product) {
//        return products.put(product.getProductType(), product);
//    }
}
