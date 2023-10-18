package org.example;


import org.example.potions.OrcPotionProduct;

import java.util.concurrent.Semaphore;

public class SauronWarrior extends Warriors implements Runnable{
    OrcPotionProduct orcPotionProduct;


    public SauronWarrior(String name, int energy, Semaphore shield, Semaphore sword,
                         Semaphore dagger, OrcPotionProduct orcPotionProduct){
       super(name, energy, shield, sword, dagger);
       this.orcPotionProduct = orcPotionProduct;
    }


    @Override
    public void run() {
        while (true){
            //TODO SE PODRÍA PONER PARA CONSUMIR ENERGÍA SOLAMENTE CUANDO LE QUEDE 0
                try {
                    orcPotionProduct.consumeOrcPotion(this);
                } catch (Exception e) {
                    System.out.println("Error al consumir poción");
                }






        }


    }
}
