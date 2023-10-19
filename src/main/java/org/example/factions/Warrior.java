package org.example.factions;

import java.util.concurrent.Semaphore;

public abstract class Warrior {
    private String name;
    private float energy;

    public Warrior(String name, float energy) {
        this.name = name;
        this.energy = energy;
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


}
