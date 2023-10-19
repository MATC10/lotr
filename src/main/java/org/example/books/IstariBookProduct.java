package org.example.books;

import org.example.factions.IluvatarWarrior;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class IstariBookProduct {

    //volatile es para que los cambios en access sean visibles para todos los hilos
    private volatile Semaphore access;
    private static List<Integer> istariBook = new LinkedList<>();

    public IstariBookProduct(Semaphore access){
        this.access = access;
    }




    public List<Integer> getIstariBook() {
        return istariBook;
    }



    public static void setIstariBook(List<Integer> istariBook) {
        IstariBookProduct.istariBook = istariBook;
    }

    public void consumeIstariBook(IluvatarWarrior iluvatarWarrior) {
        if (!getIstariBook().isEmpty()){
            try {
            access.acquire();

            iluvatarWarrior.setEnergy(istariBook.get(randomNumberList()));


            System.out.printf("El guerrero de Ilúvatar %s ha recargado energía y tiene ahora %f\n",
                    iluvatarWarrior.getName(), iluvatarWarrior.getEnergy());


            Thread.sleep(5000);
                access.release();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public synchronized void produceIstariBook(Istari istari) throws InterruptedException {
        try {

        //el Istari adquiere los 3 permisos de semáforo entrando solo al libro
        access.acquire(3);

        //agrega un número de energía aleatorio entre 1 y 5 a la lista
        istariBook.add(randomNumber());

            System.out.printf("%s ha escrito una nueva página en el libro, tiene ahora %d páginas\n",
                    istari.getName(), istariBook.size());


        //TODO PUEDO QUE ESTO TENGA QUE PONERLO LO PRIMERO
            access.release(3);

            //deja los permisos libres para otros
            Thread.sleep((10000));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //método para generar un número aleatorio entre 1 y 5
    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(5)+1;
    }

    //método para generar un número aleatorio para hacer get de un elemento aleatorio de la lista
    public int randomNumberList(){
        Random random = new Random();
        return random.nextInt(istariBook.size());
    }
}
