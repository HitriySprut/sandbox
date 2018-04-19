package ksenia.BlocksPuzzle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by admin on 12.11.2017.
 */
class Solution {

    public static void main(String args[]) throws Exception {


//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)) ;
//        System.out.println("enter height");
//        int height = Integer.parseInt(reader.readLine());
//        System.out.println("enter width");
//        int width = Integer.parseInt(reader.readLine());
//
//        Random rand = new Random();
//        ArrayList<Integer> blocks = new ArrayList<>();
//
//        for (int i = 0; i < width;i++){
//
//            blocks.add(rand.nextInt(height));
//        }
//        for (int block_num_in_a_row : blocks)
//        {
//            if(block_num_in_a_row==0)System.out.print("_");
//            else
//            System.out.print(block_num_in_a_row);
//        }

        Integer[] array = {4,2,2,0,4,3};
        ArrayList<Integer> blocks = new ArrayList<>(Arrays.asList(array));
        int water_amount = 0;

        for (int i=0;i< blocks.size()-1;i++){
            if(blocks.get(i)>0&&(i==blocks.size()-1))break;
            if((blocks.get(i)>0)&&(blocks.get(i+1)<blocks.get(i))){
                for(int k =i+1;k<=blocks.size()-1;k++){
                    if(k==blocks.size()-1)break;
                    if(k+1==blocks.size()-1){
                        if(blocks.get(k+1)>blocks.get(k)){
                            int range = k - i;
                            water_amount += Math.min(blocks.get(i),blocks.get(k+1))*range;

                            for(int n = i+1;n<k+1;n++) {
                                water_amount -= blocks.get(n);
                            }
                            i=k;
                            break;
                        }
                    }
                    if((blocks.get(k)<blocks.get(k+1))&&(blocks.get(k+1)>blocks.get(k+2))||(k+2==blocks.size()-1)){
                        int range = k - i;
                        water_amount += Math.min(blocks.get(i),blocks.get(k+1))*range;

                            for(int n = i+1;n<k+1;n++) {
                                water_amount -= blocks.get(n);
                            }
                            i=k;
                            break;

                    }
                }
            }
            else continue;
        }
        System.out.println("water_amount: "+water_amount);
        System.in.read();
/*
        while(true)
        {

            int max_block_num = Collections.max(blocks);
            //Идем вправо

            int index = blocks.indexOf(max_block_num);

            while(index < blocks.size()-1){

                if(blocks.get(index+1)>blocks.get(index)){

                    int range = index+1-blocks.indexOf(max_block_num);
                    water_amount += blocks.get(index+1)*range;
                    for (int i =1;i<range-1;i++){
                        water_amount -= blocks.get(blocks.indexOf(max_block_num)+i);
                        blocks.set(blocks.get(blocks.indexOf(max_block_num)+i),0);
                    }

                   break;
                }
                index++;

            }
            //Идем влево

            while(index > 0){

                if(blocks.get(index-1)>blocks.get(index)){

                    int range = blocks.indexOf(max_block_num)-(index-1);
                    water_amount += blocks.get(index-1)*range;
                    for (int i =1;i<range-1;i++){
                        water_amount -= blocks.get(blocks.indexOf(max_block_num)-i);
                        blocks.set(blocks.get(blocks.indexOf(max_block_num)-i),0);
                    }

                    break;
                }
                index--;

            }
            blocks.set(blocks.get(blocks.indexOf(max_block_num)),0);

            if(blocks.stream().allMatch(blocks.get(0)::equals))break;

        }
        */



    }
}
