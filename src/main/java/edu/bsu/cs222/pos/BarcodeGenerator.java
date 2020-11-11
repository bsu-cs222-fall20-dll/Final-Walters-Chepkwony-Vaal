package edu.bsu.cs222.pos;

import java.util.*;

public class  BarcodeGenerator {
    private Set<String> usedCodes;
    String newCodeInProgress = "";
    String newBarcode;

    public BarcodeGenerator(Company company) {
        this.usedCodes = company.getAvailableInventoryList().keySet();


    }



    public String makeNewCode() {

        do {
            for (int i = 0; i < 12; i++) {
                int digit = (int) ((Math.random() * (10)) + 0);
                newCodeInProgress = newCodeInProgress.concat(String.valueOf(digit));
            }
//TODO

        } while (usedCodes.contains(newBarcode));
        newBarcode = newCodeInProgress;
        return newBarcode;
    }


    public Set<String> getUsedCodes() {
        return usedCodes;
    }
}
