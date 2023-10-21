package org.example.battle;

import org.example.factions.Warrior;

public class TomBombadil extends Thread{

    private final String NAME = "Tom Bombadil";
    private int iluvatarVictories;
    private int sauronVictories;
    private int draw;
    private int totalFights;
    private final Warrior[] battleArray = new Warrior[2];





    public TomBombadil() {

        iluvatarVictories = 0;
        sauronVictories = 0;
        totalFights = 0;
        draw = 0;
    }

    public String getNAME() {
        return NAME;
    }

    public int getIluvatarVictories() {
        return iluvatarVictories;
    }

    public void setIluvatarVictories(int iluvatarVictories) {
        this.iluvatarVictories = iluvatarVictories;
    }

    public int getSauronVictories() {
        return sauronVictories;
    }

    public void setSauronVictories(int sauronVictories) {
        this.sauronVictories = sauronVictories;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getTotalFights() {
        return totalFights;
    }

    public void setTotalFights(int totalFights) {
        this.totalFights = totalFights;
    }

    public Warrior[] getBattleArray() {
        return battleArray;
    }

    public void run(){

    }


    public void compareEnergy(){
        //sumamos 1 a las peleas totales
        setTotalFights(getTotalFights() + 1);

        if(getBattleArray()[0].getEnergy() > getBattleArray()[1].getEnergy()) {
            //victoria para los de sauron

            System.out.printf("Gana %s con %.2f de energía, pierde %s con %.2f de energía\n", getBattleArray()[0].getName(),
                    getBattleArray()[0].getEnergy(), getBattleArray()[1].getName(), getBattleArray()[1].getEnergy());

            setSauronVictories(getSauronVictories() + 1);

            System.out.printf("Victorias totales de los de Sauron: %d\n", getSauronVictories());
        }
        else if(getBattleArray()[0].getEnergy() < getBattleArray()[1].getEnergy()){
            //victoria para los de iluvatar

            System.out.printf("Gana %s con %.2f de energía, pierde %s con %.2f de energía\n", getBattleArray()[1].getName(),
                    getBattleArray()[1].getEnergy(), getBattleArray()[0].getName(), getBattleArray()[0].getEnergy());

            setIluvatarVictories(getIluvatarVictories() + 1);

            System.out.printf("Victorias totales de los de Ilúvatar: %d\n", getIluvatarVictories());
        }
        else{
            //empate
            setDraw(getDraw() + 1);

            System.out.printf("Empate entre %s con %.2f de energía y %s con %.2f de energía\n", getBattleArray()[0].getName(),
                    getBattleArray()[0].getEnergy(), getBattleArray()[1].getName(), getBattleArray()[1].getEnergy());

        }
    }
}


