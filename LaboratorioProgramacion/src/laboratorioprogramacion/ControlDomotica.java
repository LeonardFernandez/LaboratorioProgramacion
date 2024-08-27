/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorioprogramacion;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Leo
 */
public class ControlDomotica implements Callable<Integer>{
    //Esta clase se encarga de "encender" y "apagar" el sistema de domotica
    boolean estaEncedido;
    //Si esta encedido libera el semaforo para poder ejecutar tareas, caso contrario retiene permiso
    private Semaphore semaforo; 
    ControlUniversalTarget control;
    
    
    public ControlDomotica(Semaphore sem){
        semaforo=sem;
        estaEncedido=false;
    }
    
    public Integer call() throws InterruptedException{
        if(estaEncedido){
            apagar();
        }else{
            encender();
        }
        return 0;
    }
    
    public void encender(){
        //Enciende TV y libera permiso para que puedan ejecutarse tareas domotica
        semaforo.release();
        control.encender();
        estaEncedido=true;
    }
    
    public void apagar() throws InterruptedException{
        //Apaga la TV tomando el semaforo para que no puedan ejecutarse tareas de domotica
        semaforo.acquire();
        control.apagar();
        estaEncedido=false;
    }
}
