package ksenia;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        //Scanner in = new Scanner(System.in);
        //String MESSAGE = in.nextLine();



        /*byte[] bytes = MESSAGE.getBytes();

        MESSAGE=null;

        for (byte b : bytes)
  {
    if (b==0)
  }*/

        String MESSAGE = "%";

        byte[] bytes = MESSAGE.getBytes();
        StringBuilder binary = new StringBuilder();
        for (int j = 0; j < bytes.length; j++) {
            int val = bytes[j];
            for (int i = 0; i < 7; i++) {
                val <<= 1;
                binary.append((val & 128) == 0 ? 0 : 1);
            }
        }

        String binary_string = binary.toString();

        //System.out.println("'" + MESSAGE + "' to binary: " + binary);

        char[] binary_symbols = binary_string.toCharArray();
        String chuck_norris_string = "";

        System.out.println("binary_string: " + binary_string);




        boolean zeroAlreadyDetected =false;
        boolean isStart = true;
        System.out.println();
        for (char symbol : binary_symbols )
        {
            if(symbol == '0' && (!zeroAlreadyDetected || isStart)) {

                if(!isStart) chuck_norris_string += " ";
                chuck_norris_string += "00 0";
                zeroAlreadyDetected=true;
                isStart=false;
                continue;
            }
            if(symbol == '0'&& zeroAlreadyDetected ){

                chuck_norris_string += "0";
                continue;

            }
            if (symbol == '1' &&  (zeroAlreadyDetected|| isStart)){

                if(isStart) chuck_norris_string += " ";
                chuck_norris_string += "0 0";
                isStart=false;
                zeroAlreadyDetected = false;
                continue;

            }
            if(symbol == '1' && !zeroAlreadyDetected){

                chuck_norris_string += "0";
                continue;

            }

        }

        System.out.println(chuck_norris_string);
    }
}












        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        //System.out.println(chuck_norris_string);


