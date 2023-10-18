package org.example;

import org.example.potions.Orc;
import org.example.potions.OrcPotionProduct;

public class Main {
    public static void main(String[] args) {

        Thread[] orcs = new Thread[3];


        OrcPotionProduct orcPotionProduct = new OrcPotionProduct(0);


        for(int i = 0; i < orcs.length; i++){
            orcs[i] = new Thread (new Orc("Orco" + (i+1), orcPotionProduct));
        }
        
        for(int i = 0; i < orcs.length; i++){
            orcs[i].start();
        }




    }
}