package org.example.potions;

import org.example.SauronWarrior;

public class OrcPotionProduct {
    private float quantity;
    private static final int MAX_QUANTITY = 5;

    public OrcPotionProduct(float quantity) {
        this.quantity = quantity;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


    //método para producir y que los guerreros orcos consuman poción y generen energía
    //puede que lo tenga que hacer sin parámetro
    public synchronized void consumeOrcPotion(SauronWarrior sauronWarrior) throws InterruptedException {
        //si no queda poción se esperan
        while (this.quantity == 0) {
            wait();
        }

        //restamos 0.5 litros de poción
        this.quantity -= 0.5f;
        //sumamos 3 de energia al guerrero
        sauronWarrior.setEnergy(sauronWarrior.getEnergy() + 3);


        notify();

        //TODO PUEDE QUE TENGA QUE PONER EL SLEEP ANTES EN LUGAR DE DESPUÉS DE RESTAR Y SUMAR
        //Cuando se va a por la poción el guerrero tarda 2 segundos en cogerla
        Thread.sleep(2000);

        //lanzamos mensaje para anunciar la recarga de energía
        System.out.printf("El guerrero de sauron %s ha recargado energía y tiene ahora %d",
                sauronWarrior.getName(), sauronWarrior.getEnergy());

    }

    //TODO PUEDE QUE HAYA QUE QUITAR EL SYNCHHRONIZED
    public synchronized void produceOrcPotion(Orc orc) throws InterruptedException {
        while (this.quantity == MAX_QUANTITY) {
            wait();
        }

        //se suma un litro de poción generada por un orco
        ++this.quantity;

        //si por alguna circunstancia sobrepasamos la cantidad límite de la poción,
        // la cantidad será siempre 5 y el resto se desbordará y caerá al suelo
        if(this.quantity > MAX_QUANTITY) this.quantity = 5;

        notify();


        //TODO PUEDE QUE TENGA QUE PONER EL SLEEP ANTES EN LUGAR DE DESPUÉS DE RESTAR Y SUMAR
        //espera 1 segundo para volver a generar
        Thread.sleep(1000);

        //lanzamos mensaje para anunciar la recarga de poción
        System.out.printf("El Orco %s ha recargado la poción, tiene ahora %d",
                orc.getName(), this.getQuantity());

    }
}
