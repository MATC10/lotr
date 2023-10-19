package org.example.battle;


import org.example.factions.Warrior;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public  class Battle {

    private CountDownLatch arrayFull;

    public Battle(CountDownLatch arrayFull) {
        this.arrayFull = arrayFull;
    }



    public void battle(){

        TomBombadil.compareEnergy();

        try {
            arrayFull.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
