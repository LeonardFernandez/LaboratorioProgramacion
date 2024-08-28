/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorioprogramacion;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Leo
 */
public class Domotica implements Callable<Integer>{
    int identificador;
    ControlUniversalTarget control;
    private Semaphore semaforo;
   
    public Integer call() throws InterruptedException{
        Random random = new Random();
        int randomInt=random.nextInt(2);
        //Tomar permiso
        semaforo.acquire();
        switch(randomInt){
            case 0: control.subirVolumen(); break;
            case 1: control.bajarVolumen(); break;
            case 2: control.cambiarCanal(); break;
        }
        //Largar permiso
        semaforo.release();
        return identificador;
    }
    
    public Domotica(int id, Semaphore sem){
        identificador=id;
        semaforo=sem;
    }
    
}
