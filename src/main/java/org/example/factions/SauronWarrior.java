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
    private Semaphore sauronBattle;
    private CountDownLatch arrayFull;
    private final Object lock = new Object();


    public SauronWarrior(String name, int energy, Semaphore shieldSauron, Semaphore swordSauron,
                         Semaphore daggerSauron, OrcPotionProduct orcPotionProduct, Battle battle,
                         Semaphore sauronBattle, CountDownLatch arrayFull) {
        super(name, energy);
        this.orcPotionProduct = orcPotionProduct;
        this.shieldSauron = shieldSauron;
        this.swordSauron = swordSauron;
        this.daggerSauron = daggerSauron;
        this.battle = battle;
        this.sauronBattle = sauronBattle;
        this.arrayFull = arrayFull;

    }


    @Override
    public void run() {
        while (true) {
            //TODO SE PODRÍA PONER PARA CONSUMIR ENERGÍA SOLAMENTE CUANDO LE QUEDE 0

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
                TomBombadil.getBattleArray()[0] = this;

                if (TomBombadil.getBattleArray()[0] != null && TomBombadil.getBattleArray()[1] != null) {
                    synchronized (lock) {
                        lock.notify(); // Notificar que el array está lleno
                    }
                }


                try {
                    System.out.println("SAURON espada" + this.swordSauron.availablePermits() + "escudo" + this.shieldSauron.availablePermits() + "daga" + this.daggerSauron.availablePermits() +" " + this.getName());

                    if(TomBombadil.getBattleArray()[0] == null || TomBombadil.getBattleArray()[1] == null){
                        synchronized (lock){
                            lock.wait();
                        }
                    }


                    System.out.println("LLEGAAAA2");
                    sauronBattle.acquire();



                    //Esperamos a que la cuenta llegue a 0 para entrar
                    arrayFull.countDown();





                    this.battle.battle();
                    this.setEnergy(0);

                    sauronBattle.release();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            shieldSauron.release();
            swordSauron.release();
            daggerSauron.release();


        }
    }
}
