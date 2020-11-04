package edu.bsu.cs222.pos;

import java.util.*;

public class BarcodeGenerator {
    private Set<String> usedCodes;
    String newCodeInProgress = "";
    String newBarcode;

    public BarcodeGenerator(HashMap<String, Item> availableInventoryList) {
        this.usedCodes = availableInventoryList.keySet();


    }



    public String makeNewCode() {

        do {
            for (int i = 0; i < 12; i++) {
                int digit = (int) ((Math.random() * (10)) + 0);
                newCodeInProgress = newCodeInProgress.concat(String.valueOf(digit));
            }


        } while (usedCodes.contains(newBarcode));
        newBarcode = newCodeInProgress;
        return newBarcode;
    }


    public Set<String> getUsedCodes() {
        return usedCodes;
    }
}
