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
    private Semaphore iluvatarBattle;
    private CountDownLatch arrayFull;

    private final Object lock = new Object();

    public IluvatarWarrior(String name, int energy, Semaphore shieldIluvatar, Semaphore swordIluvatar,
                           Semaphore daggerIluvatar, IstariBookProduct istariBookProduct, Battle battle,
                           Semaphore iluvatarBattle, CountDownLatch arrayFull) {
        super(name, energy);
        this.istariBookProduct = istariBookProduct;
        this.battle = battle;
        this.shieldIluvatar = shieldIluvatar;
        this.swordIluvatar = swordIluvatar;
        this.daggerIluvatar = daggerIluvatar;
        this.iluvatarBattle = iluvatarBattle;
        this.arrayFull = arrayFull;
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
                TomBombadil.getBattleArray()[1] = this;

                try {
                    if(TomBombadil.getBattleArray()[0] == null || TomBombadil.getBattleArray()[1] == null){
                        synchronized (lock){
                            lock.wait();
                        }
                    }

                    //la array está llena
                    if (TomBombadil.getBattleArray()[0] != null && TomBombadil.getBattleArray()[1] != null) {
                        synchronized (lock) {
                            lock.notify();
                        }
                    }

                    arrayFull.countDown();


                    iluvatarBattle.acquire();





                    this.battle.battle();
                    this.setEnergy(0);


                    iluvatarBattle.release();

                }   catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                shieldIluvatar.release();
                swordIluvatar.release();
                daggerIluvatar.release();
            }



        }
    }
}
