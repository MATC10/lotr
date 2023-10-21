package org.example.factions;


import org.example.battle.Battle;
import org.example.battle.TomBombadil;
import org.example.potions.OrcPotionProduct;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SauronWarrior extends Warrior implements Runnable {
    OrcPotionProduct orcPotionProduct;
    private Battle battle;
    private volatile Semaphore shieldSauron;
    private volatile Semaphore swordSauron;
    private volatile Semaphore daggerSauron;
    private CountDownLatch arrayFull;
    private final Object lock = new Object();
    private volatile TomBombadil tomBombadil;


    public SauronWarrior(String name, int energy, Semaphore shieldSauron, Semaphore swordSauron,
                         Semaphore daggerSauron, OrcPotionProduct orcPotionProduct, Battle battle,
                         CountDownLatch arrayFull, TomBombadil tomBombadil) {
        super(name, energy);
        this.orcPotionProduct = orcPotionProduct;
        this.shieldSauron = shieldSauron;
        this.swordSauron = swordSauron;
        this.daggerSauron = daggerSauron;
        this.battle = battle;
        this.arrayFull = arrayFull;
        this.tomBombadil = tomBombadil;

    }


    @Override
    public void run() {

    while (true) {
        try {
            orcPotionProduct.consumeOrcPotion(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {

            shieldSauron.acquire();
            swordSauron.acquire();
            daggerSauron.acquire();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (this.getEnergy() > 0 && (shieldSauron.availablePermits() > 0) &&
                (swordSauron.availablePermits() > 0) && daggerSauron.availablePermits() > 0) {

            tomBombadil.getBattleArray()[0] = this;

            //espera a que la cuenta llegue a 0 para entrar
            arrayFull.countDown();

            try {
                while (tomBombadil.getBattleArray()[0] == null || tomBombadil.getBattleArray()[1] == null) {
                    synchronized (lock) {
                        lock.wait();
                    }
                }

                this.battle.battle();
                this.setEnergy(0);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //notifica que el array está lleno
            if (tomBombadil.getBattleArray()[0] != null && tomBombadil.getBattleArray()[1] != null) {
                synchronized (lock) {
                    lock.notify();
                }
            }

        }
        shieldSauron.release();
        swordSauron.release();
        daggerSauron.release();

    }

    }

}
