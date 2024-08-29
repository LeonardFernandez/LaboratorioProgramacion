package TP1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void apagarTV(ScheduledExecutorService scheduler, Callable<String> apagarTele, Callable<String> desenchufarTele) {
        List<Callable<String>> taskList = Arrays.asList(apagarTele, desenchufarTele);
        try {
            List<Future<String>> results = scheduler.invokeAll(taskList);
            for (Future<String> result : results) {
                try {
                    System.out.println(result.get());
                } catch (ExecutionException e) {
                    System.err.println("ERROR");
                }
            }
        } catch (InterruptedException e) {
            System.err.println("ERROR");
        }
        scheduler.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        // Crear el adaptee y el adapter
        Televisor miTV = new Televisor();
        ControlRemoto control = new TelevisorAdapter(miTV);

        // Crear un ScheduledExecutorService con dos hilos
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService schedulerApagado = Executors.newScheduledThreadPool(2);

        // Crear y programar las tareas de presión de botones
        Callable<String> encender = () -> {
            control.encender();
            return "Terminó tarea de encender el televisor";
        };
        Callable<String> cambiarCanal = () -> {
            control.upChannel();
            return "Terminó tarea de subir de canal";
        };
        Callable<String> subirVol = () -> {
            control.subirVolumen();
            return "Terminó tarea de subir el volumen";
        };

        Callable<String> bajarCanal = () -> {
            control.downChannel();
            return "Terminó tarea de bajar de canal";
        };
        Callable<String> bajarVol = () -> {
            control.bajarVolumen();
            return "Terminó tarea de bajar el volumen";
        };

        Callable<String> turnOffTV = () -> {
            control.apagar();
            return "Tarea de apagar el televisor terminada";
        };

        Callable<String> unplugTV = () -> {
            control.desenchufar();
            return "Tarea de desenchufar el televisor terminada";
        };

        scheduler.schedule(encender, 0, TimeUnit.SECONDS);
        scheduler.schedule(cambiarCanal, 5, TimeUnit.SECONDS);
        scheduler.schedule(subirVol, 8, TimeUnit.SECONDS);
        scheduler.schedule(cambiarCanal, 10, TimeUnit.SECONDS);
        scheduler.schedule(subirVol, 12, TimeUnit.SECONDS);
        scheduler.schedule(bajarCanal, 14, TimeUnit.SECONDS);
        scheduler.schedule(bajarVol, 16, TimeUnit.SECONDS);

        // Método de apagado que utiliza invokeAll
        try {
            Thread.sleep(20000); // Simula el tiempo antes de ejecutar la función apagar
            apagarTV(schedulerApagado, turnOffTV, unplugTV);
        } catch (InterruptedException e) {
            System.err.println("ERROR");
        }
        scheduler.shutdown();
    }

}

