package org.example;

public  class Battle {
    private Warriors sauronWarrior;
    private Warriors iluvatarWarrior;
    private TomBombadil tomBombadil;
    private int iluvatarVictories;
    private int sauronVictories;
    private int draw;
    private int totalFights;

    public Battle(Warriors sauronWarrior, Warriors iluvatarWarrior, TomBombadil tomBombadil,
                  int iluvatarVictories, int sauronVictories, int totalFights, int draw) {
        this.sauronWarrior = sauronWarrior;
        this.iluvatarWarrior = iluvatarWarrior;
        this.tomBombadil = tomBombadil;
        this.iluvatarVictories = iluvatarVictories;
        this.sauronVictories = sauronVictories;
        this.totalFights = totalFights;
        this.draw = draw;
    }


    public Warriors getSauronWarrior() {
        return sauronWarrior;
    }

    public void setSauronWarrior(Warriors sauronWarrior) {
        this.sauronWarrior = sauronWarrior;
    }

    public Warriors getIluvatarWarrior() {
        return iluvatarWarrior;
    }

    public void setIluvatarWarrior(Warriors iluvatarWarrior) {
        this.iluvatarWarrior = iluvatarWarrior;
    }

    public TomBombadil getTomBombadil() {
        return tomBombadil;
    }

    public void setTomBombadil(TomBombadil tomBombadil) {
        this.tomBombadil = tomBombadil;
    }

    public int getIluvatarVictories() {
        return iluvatarVictories;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
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

    public int getTotalFights() {
        return totalFights;
    }

    public void setTotalFights(int totalFights) {
        this.totalFights = totalFights;
    }


    //A LA HORA DE PELEAR SIMPLEMENTE COMPARO ENERGIAS Y DESPUÉS DE LA PELEA PIERDEN TODA SU ENERGIA LOS DOS?

    public void battle(Warriors sauronWarrior, Warriors iluvatarWarrior){
        tomBombadil.compareEnergy(sauronWarrior, iluvatarWarrior);
    }




}
