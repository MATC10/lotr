package org.example.battle;

import org.example.factions.Warrior;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class TomBombadil implements Runnable{

    private final String NAME = "Tom Bombadil";
    private static int iluvatarVictories;
    private static int sauronVictories;
    private static int draw;
    private static int totalFights;
    private static final Warrior[] BATTLE_ARRAY = new Warrior[2];





    public TomBombadil() {

        iluvatarVictories = 0;
        sauronVictories = 0;
        totalFights = 0;
        draw = 0;
    }

    public String getNAME() {
        return NAME;
    }

    public static int getIluvatarVictories() {
        return iluvatarVictories;
    }

    public static void setIluvatarVictories(int iluvatarVictories) {
        TomBombadil.iluvatarVictories = iluvatarVictories;
    }

    public static int getSauronVictories() {
        return sauronVictories;
    }

    public static void setSauronVictories(int sauronVictories) {
        TomBombadil.sauronVictories = sauronVictories;
    }

    public static int getDraw() {
        return draw;
    }

    public static void setDraw(int draw) {
        TomBombadil.draw = draw;
    }

    public static int getTotalFights() {
        return totalFights;
    }

    public static void setTotalFights(int totalFights) {
        TomBombadil.totalFights = totalFights;
    }

    public static Warrior[] getBattleArray() {
        return BATTLE_ARRAY;
    }


    @Override
    public void run() {
    }

    public static void compareEnergy(){
        //sumamos 1 a las peleas totales
        setTotalFights(getTotalFights() + 1);

        if(getBattleArray()[0].getEnergy() > getBattleArray()[1].getEnergy()) {
            //victoria para los de sauron

            System.out.printf("Gana %s con %f de energía, pierde %s con %f de energía\n", getBattleArray()[0].getName(),
                    getBattleArray()[0].getEnergy(), getBattleArray()[1].getName(), getBattleArray()[1].getEnergy());

            setSauronVictories(getSauronVictories() + 1);

            System.out.printf("Victorias totales de los de Sauron: %d\n", getSauronVictories());
        }
        else if(getBattleArray()[0].getEnergy() < getBattleArray()[1].getEnergy()){
            //victoria para los de iluvatar

            System.out.printf("Gana %s con %f de energía, pierde %s con %f de energía\n", getBattleArray()[1].getName(),
                    getBattleArray()[1].getEnergy(), getBattleArray()[0].getName(), getBattleArray()[0].getEnergy());

            setIluvatarVictories(getIluvatarVictories() + 1);

            System.out.printf("Victorias totales de los de Ilúvatar: %d\n", getIluvatarVictories());
        }
        else{
            //empate
            setDraw(getDraw() + 1);

            System.out.printf("Empate entre %s con %f de energía y %s con %f de energía\n", getBattleArray()[0].getName(),
                    getBattleArray()[0].getEnergy(), getBattleArray()[1].getName(), getBattleArray()[1].getEnergy());

        }
    }
}


