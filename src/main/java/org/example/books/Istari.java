package org.example.books;

import org.example.potions.OrcPotionProduct;

import java.util.concurrent.Semaphore;

public class Istari  implements Runnable{
    private String name;
    private IstariBookProduct istariBookProduct;
    private volatile Semaphore access;

    public Istari(String name, IstariBookProduct istariBookProduct, Semaphore access) {
        this.name = name;
        this.istariBookProduct = istariBookProduct;
        this.access = access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.istariBookProduct.produceIstariBook(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
