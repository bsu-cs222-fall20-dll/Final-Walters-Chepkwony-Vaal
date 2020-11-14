package edu.bsu.cs222.pos;

import java.sql.SQLException;
import java.util.*;

public class  BarcodeGenerator {
    private Set<String> usedCodes;
    private String newCodeInProgress = "";
    private String newBarcode;
    private Company company;

    public BarcodeGenerator(Company company) {
        this.usedCodes = Set.copyOf(company.getAvailableInventoryList().keySet());
        this.company = company;

    }



    public String makeNewCode() {

        do {
            for (int i = 0; i < 12; i++) {
                int digit = (int) ((Math.random() * (10)) + 0);
                newCodeInProgress = newCodeInProgress.concat(String.valueOf(digit));
            }
//TODO

//        } while (usedCodes.contains(newCodeInProgress));
        } while (company.getItem(newCodeInProgress) !=null);
//        newBarcode = newCodeInProgress;
//        usedCodes.add(newCodeInProgress);
        return newCodeInProgress;
    }


    public Set<String> getUsedCodes() {
        return usedCodes;
    }
}
