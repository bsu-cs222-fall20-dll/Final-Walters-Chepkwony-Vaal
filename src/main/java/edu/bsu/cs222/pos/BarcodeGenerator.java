package edu.bsu.cs222.pos;

import java.util.*;

public class BarcodeGenerator {
    private ArrayList<Integer> availableCodes = new ArrayList<>();
    private Set<Integer> usedCodes;

    public BarcodeGenerator(HashMap<Integer, Item> availableInventoryList) {
        this.usedCodes = availableInventoryList.keySet();
        fillAvailableCodes();

    }

    private void removeUsedCodes() {
        for(int usedCode: usedCodes){
            if(availableCodes.contains(usedCode)){
                availableCodes.remove(usedCode);
            }
        }
    }

    private void fillAvailableCodes(){
        for(int i = 1; i<10 ; i++){
            availableCodes.add(i);
        }
    }

    public int makeNewCode() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(availableCodes.size());
        Integer newCode =availableCodes.get(randomIndex);
        usedCodes.add(newCode);
        removeUsedCodes();
        return newCode;
        }

    public ArrayList<Integer> getAvailableCodes() {
        return availableCodes;
    }

    public Set<Integer> getUsedCodes() {
        return usedCodes;
    }
}
