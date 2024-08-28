/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorioprogramacion;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Leo
 */
public class Main {
    private static Semaphore semOnOff;
            
    public static void main(String[] args) {
        semOnOff = new Semaphore(0);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
        //Se crea controlDomotica con semaforo
        Callable<Integer> onOff = new ControlDomotica(semOnOff);
        //Creamos las diferentes tareas(callable) de domotica
        Callable<Integer> tarea1 = new Domotica(0,semOnOff);
        Callable<Integer> tarea2 = new Domotica(1,semOnOff);
        Callable<Integer> tarea3 = new Domotica(2,semOnOff);
        
        //Primer instante se enciende sistema de domotica
        scheduler.schedule(onOff, 0, TimeUnit.SECONDS);
        //Se ejecuta una funcion de domotica a los dos segundos
        scheduler.schedule(tarea2, 4, TimeUnit.SECONDS);
        scheduler.schedule(tarea1, 2, TimeUnit.SECONDS);
        scheduler.schedule(tarea3, 6, TimeUnit.SECONDS);
        //Se apaga TV
        scheduler.schedule(onOff, 20, TimeUnit.SECONDS);

    }
}
