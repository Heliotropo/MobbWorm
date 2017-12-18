package model;

import java.util.Random;

public class Effects {
    private static int max = 5, min = 1;
    
    
    public void showEffects() {
        switch (getRandomNumber()) {
        case 1: break;
        case 2: break;
        case 3: break;
        case 4: break;
        case 5: break;

        default:
            break;
        }
    }
    public void doEffects() {
        switch (getRandomNumber()) {
        case 1: break;
        case 2: break;
        case 3: break;
        case 4: break;
        case 5: break;

        default:
            break;
        }
    }
    public void goFast() {}
    public void goSlow() {}
    public void square() {}
    public void inverse() {}
    public void extraPoint() {}
    public int getRandomNumber() {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
