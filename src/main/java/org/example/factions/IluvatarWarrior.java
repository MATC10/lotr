package org.example.factions;

import org.example.battle.Battle;
import org.example.battle.TomBombadil;
import org.example.books.IstariBookProduct;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class IluvatarWarrior extends Warrior implements Runnable {
    private IstariBookProduct istariBookProduct;
    private Battle battle;
    private volatile Semaphore shieldIluvatar;
    private volatile Semaphore swordIluvatar;
    private volatile Semaphore daggerIluvatar;

    private CountDownLatch arrayFull;
    private volatile TomBombadil tomBombadil;

    private final Object lock = new Object();

    public IluvatarWarrior(String name, int energy, Semaphore shieldIluvatar, Semaphore swordIluvatar,
                           Semaphore daggerIluvatar, IstariBookProduct istariBookProduct, Battle battle,
                           CountDownLatch arrayFull, TomBombadil tomBombadil) {
        super(name, energy);
        this.istariBookProduct = istariBookProduct;
        this.battle = battle;
        this.shieldIluvatar = shieldIluvatar;
        this.swordIluvatar = swordIluvatar;
        this.daggerIluvatar = daggerIluvatar;
        this.arrayFull = arrayFull;
        this.tomBombadil = tomBombadil;
    }

    @Override
    public void run() {
        while (true) {
            try {

                istariBookProduct.consumeIstariBook(this);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {

                shieldIluvatar.acquire();
                swordIluvatar.acquire();
                daggerIluvatar.acquire();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            if(this.getEnergy() > 0 && (shieldIluvatar.availablePermits () > 0) &&
                    (swordIluvatar.availablePermits() > 0) && daggerIluvatar.availablePermits() > 0){

                tomBombadil.getBattleArray()[1] = this;

                arrayFull.countDown();

                try {

                    while (tomBombadil.getBattleArray()[0] == null || tomBombadil.getBattleArray()[1] == null){
                        synchronized (lock){
                            lock.wait();
                        }
                    }

                    this.battle.battle();
                    this.setEnergy(0);

                }   catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //la array está llena
                if (tomBombadil.getBattleArray()[0] != null && tomBombadil.getBattleArray()[1] != null) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }
            shieldIluvatar.release();
            swordIluvatar.release();
            daggerIluvatar.release();

        }
    }

}
