/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravee
 */
//public class VendingMachineMoneyImpl implements VendingMachineMoney {
//
//    private BigDecimal owedMoney;
//
//   public VendingMachineMoneyImpl(BigDecimal owedMoney){
//       this.owedMoney = owedMoney;
//   }
//   
//    
//    @Override
//    public List<Funds> changeTally() {
//       List<Funds>returnedFunds= new ArrayList<>();
//       
//       for(Funds funds : Funds.values()){
//           while(Funds.getVALUE().compareTo(owedMoney)<=0){
//               setOwedMoney(owedMoney.subtract(funds.getVALUE()));
//               returnedFunds.add(funds);
//           }
//       }return returnedFunds;
//    }
//
//    @Override
//    public BigDecimal getOwedMoney() {
//        return owedMoney;
//    }
//
//    @Override
//    public void setOwedMoney(BigDecimal owedMoney) {
//        this.owedMoney = owedMoney;
//    }
//
//}
