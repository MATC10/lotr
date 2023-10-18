package org.example.potions;

public class Orc implements Runnable{
    private String name;
    private  OrcPotionProduct orcPotionProduct;

    public Orc(String name, OrcPotionProduct orcPotionProduct) {
        this.name = name;
        this.orcPotionProduct = orcPotionProduct;
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
                this.orcPotionProduct.produceOrcPotion(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
