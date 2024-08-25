/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorioprogramacion;

/**
 *
 * @author Leo
 */
public class Adapter implements ControlUniversalTarget{
    private ControlRemotoAdaptee controlRemotoViejo;
    
    public Adapter(ControlRemotoAdaptee control){
        controlRemotoViejo=control;
    }
    
    public void encender(){
        controlRemotoViejo.encender();
    }
    
    public void apagar(){
        controlRemotoViejo.apagar();
    }
    
    public void subirVolumen(){
        controlRemotoViejo.subirVolumen();
    }
    
    public void bajarVolumen(){
        controlRemotoViejo.bajarVolumen();
    }
    
    public void cambiarCanal(){
        controlRemotoViejo.cambiarCanal();
    }
    
    
}
