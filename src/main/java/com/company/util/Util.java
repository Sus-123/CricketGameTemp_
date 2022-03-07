package com.company.util;
import constants.Constants;
import com.company.entity.*;

import java.util.concurrent.ThreadLocalRandom;


public class Util {

     static UtilHelper helper = new UtilHelper();

     public  int getIntegerInput(int lower, int upper) {
        int io = helper.getIntegerInput();
        while(true) {
            try {
                //io = Integer.parseInt(sc.nextLine());
                if (io < lower || io > upper) {
                    System.out.println(String.format("Values Should be in range % d and % d", lower, upper));
                } else {
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Enter only Integer");
            }

        }
        return io;
    }

    public String getValidStringType () {
        String io = "";
        io = helper.getStringInput();
        while(true){
           // io = sc.nextLine();
            if(io.isEmpty()){
                System.out.println("Enter Non-empty Value");
            }
            else
                break;
        }
        return io;
    }

    public static int playToss() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerTossBound,Constants.upperTossBound);
    }


    public static int getRandomRun() {
        int [] numbers= helper.getIntegerArray();
        int ix = ThreadLocalRandom.current().nextInt(0, 100);
        int num = numbers[ix];
        return num;
     }

    public static int getRandomBowler() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerBowlerIndex, Constants.upperBowlerIndex);
     }











}
