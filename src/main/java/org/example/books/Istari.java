package org.example.books;


public class Istari  implements Runnable{
    private String name;
    private IstariBookProduct istariBookProduct;

    public Istari(String name, IstariBookProduct istariBookProduct) {
        this.name = name;
        this.istariBookProduct = istariBookProduct;
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
