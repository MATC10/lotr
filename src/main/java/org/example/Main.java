package org.example;

import org.example.books.Istari;
import org.example.books.IstariBookProduct;
import org.example.potions.Orc;
import org.example.potions.OrcPotionProduct;

import java.util.concurrent.Semaphore;

import static org.example.Warriors.*;

public class Main {
    public static void main(String[] args) {
        Semaphore access = new Semaphore(3);
        Semaphore shieldSauron = new Semaphore(3);
        Semaphore swordSauron = new Semaphore(3);
        Semaphore daggerSauron = new Semaphore(3);
        Semaphore shieldIluvatar = new Semaphore(3);
        Semaphore swordIluvatar = new Semaphore(3);
        Semaphore daggerIluvatar = new Semaphore(3);
        Semaphore sauronBattle = new Semaphore(1);
        Semaphore iluvatarBattle = new Semaphore(1);

        //creamos las arrays de hilos
        Thread[] orcs = new Thread[3];
        Thread[] istari = new Thread[3];
        Thread[] sauronWarrior = new Thread[10];
        Thread[] iluvatarWarrior = new Thread[10];

        //inicializamos el libro y la poción de energía
        OrcPotionProduct orcPotionProduct = new OrcPotionProduct(0);
        IstariBookProduct istariBookProduct = new IstariBookProduct(access);

        //metemos en array a los orcos
        for(int i = 0; i < orcs.length; i++){
            orcs[i] = new Thread (new Orc("Orco" + (i+1), orcPotionProduct));
        }

        //metemos en array a los istari
        for(int i = 0; i < istari.length; i++){
            istari[i] = new Thread (new Istari("Istari" + (i+1), istariBookProduct,access));
        }

        //metemos en array a los sauronWarriors
        for(int i = 0; i < sauronWarrior.length; i++){
            sauronWarrior[i] = new Thread (new SauronWarrior("SauronWarrior" + (i+1), 0,
                    shieldSauron, swordSauron, daggerSauron, orcPotionProduct));
        }

        //metemos en array a los iluvatarWarriors
        for(int i = 0; i < iluvatarWarrior.length; i++){
            iluvatarWarrior[i] = new Thread (new IluvatarWarrior("IluvatarWarrior" + (i+1), 0,
                    shieldIluvatar, swordIluvatar, daggerIluvatar, istariBookProduct));
        }

        //inicializamos orcos
        for(Thread o : orcs){
            o.start();
        }

        //inicializamos istari
        for(Thread i : istari){
            i.start();
        }

        //inicializamos sauronWarriors
        for(Thread sw : sauronWarrior){
            sw.start();
        }

        //inicializamos iluvatarWarrior
        for(Thread il : iluvatarWarrior){
            il.start();
        }




    }
}