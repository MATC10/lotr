package org.example.battle;


import java.util.concurrent.CountDownLatch;

public  class Battle {

    private CountDownLatch arrayFull;
    TomBombadil tomBombadil;

    public Battle(CountDownLatch arrayFull, TomBombadil tomBombadil) {
        this.arrayFull = arrayFull;
        this.tomBombadil = tomBombadil;
    }



    public void battle(){

        tomBombadil.compareEnergy();

        try {

            arrayFull.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
