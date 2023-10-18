package org.example;

import org.example.books.IstariBookProduct;
import org.example.potions.OrcPotionProduct;

import java.util.concurrent.Semaphore;

public class IluvatarWarrior extends Warriors implements Runnable {
    IstariBookProduct istariBookProduct;


    public IluvatarWarrior(String name, int energy, Semaphore shield, Semaphore sword,
                         Semaphore dagger, IstariBookProduct istariBookProduct) {
        super(name, energy, shield, sword, dagger);
        this.istariBookProduct = istariBookProduct;
    }

    @Override
    public void run() {
        while (true) {
            try {
                istariBookProduct.consumeIstariBook(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
