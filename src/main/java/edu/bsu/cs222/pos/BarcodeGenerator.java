package edu.bsu.cs222.pos;


public class  BarcodeGenerator {
    private final Company company;

    public BarcodeGenerator(Company company) {
        this.company = company;

    }



    public String makeNewCode() {
    String newCodeInProgress = "";
        do {
            for (int i = 0; i < 12; i++) {
                int digit = (int) ((Math.random() * (10)) + 0);
                newCodeInProgress = newCodeInProgress.concat(String.valueOf(digit));
            }
//TODO

//        } while (usedCodes.contains(newCodeInProgress));
        } while (company.getItemByID(newCodeInProgress) !=null);
//        newBarcode = newCodeInProgress;
//        usedCodes.add(newCodeInProgress);
        return newCodeInProgress;
    }


}
