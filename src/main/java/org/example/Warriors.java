package org.example;

import java.util.concurrent.Semaphore;

public abstract class Warriors {
    private String name;
    private float energy;
    private volatile Semaphore shield;
    private volatile Semaphore sword;
    private volatile Semaphore dagger;

    public Warriors(String name, float energy, Semaphore shield, Semaphore sword, Semaphore dagger) {
        this.name = name;
        this.energy = energy;
        this.shield = shield;
        this.sword = sword;
        this.dagger = dagger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public Semaphore getShield() {
        return shield;
    }

    public void setShield(Semaphore shield) {
        this.shield = shield;
    }

    public Semaphore getSword() {
        return sword;
    }

    public void setSword(Semaphore sword) {
        this.sword = sword;
    }

    public Semaphore getDagger() {
        return dagger;
    }

    public void setDagger(Semaphore dagger) {
        this.dagger = dagger;
    }


}
