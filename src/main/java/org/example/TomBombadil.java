package org.example;

import org.example.books.Istari;

public class TomBombadil implements Runnable{

    private final String NAME = "Tom Bombadil";
    Warriors sauronWarrior;
    Warriors iluvatarWarrior;
    Battle battle;


    public TomBombadil(Warriors sauronWarrior, Warriors iluvatarWarrior){
        this.sauronWarrior = sauronWarrior;
        this.iluvatarWarrior = iluvatarWarrior;
    }

    @Override
    public void run() {

    }

    public void compareEnergy(Warriors sauronWarrior, Warriors iluvatarWarrior){
        //sumamos 1 a las peleas totales
        battle.setTotalFights(battle.getTotalFights() + 1);

        if(sauronWarrior.getEnergy() > iluvatarWarrior.getEnergy()) {
            //victoria para los de sauron

            System.out.printf("Gana %s con %f de energía, pierde %s con %f de energía\n", sauronWarrior.getName(),
                    sauronWarrior.getEnergy(), iluvatarWarrior.getName(), iluvatarWarrior.getEnergy());

            battle.setSauronVictories(battle.getSauronVictories() + 1);

            System.out.printf("Victorias totales de los de Sauron: %d\n", battle.getSauronVictories());
        }
        else if(sauronWarrior.getEnergy() < iluvatarWarrior.getEnergy()){
            //victoria para los de iluvatar

            System.out.printf("Gana %s con %f de energía, pierde %s con %f de energía\n", iluvatarWarrior.getName(),
                    iluvatarWarrior.getEnergy(), sauronWarrior.getName(), sauronWarrior.getEnergy());

            battle.setIluvatarVictories(battle.getIluvatarVictories() + 1);

            System.out.printf("Victorias totales de los de Ilúvatar: %d\n", battle.getIluvatarVictories());
        }
        else{
            //empate
            battle.setDraw(battle.getDraw() + 1);

            System.out.printf("Empate entre %s con %f de energía y %s con %f de energía\n", sauronWarrior.getName(),
                    sauronWarrior.getEnergy(), iluvatarWarrior.getName(), iluvatarWarrior.getEnergy());

        }
    }
}


