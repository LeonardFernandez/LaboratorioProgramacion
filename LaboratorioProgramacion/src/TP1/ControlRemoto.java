package TP1;

public abstract class ControlRemoto {

    public ControlRemoto(){
    }
    abstract public void encender();
    abstract public void apagar();
    abstract public void subirVolumen();
    abstract public void bajarVolumen();
    abstract public void upChannel();
    abstract public void downChannel();
    abstract public void desenchufar();
}