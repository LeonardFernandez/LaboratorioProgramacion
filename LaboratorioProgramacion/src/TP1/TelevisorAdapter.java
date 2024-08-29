package TP1;
 class TelevisorAdapter extends ControlRemoto {
    private Televisor televisor;

    public TelevisorAdapter (Televisor unTele) {
        this.televisor = unTele;
    }

    public void encender() {
        televisor.apretarBotonEncendido();
    }

    public void apagar() {
        televisor.apretarBotonApagado();
    }

    public void bajarVolumen() {
        televisor.apretarBotonBajarVolumen();
    }

    public void subirVolumen() {
        televisor.apretarBotonSubirVolumen();
    }

    public void upChannel() {
        televisor.subirCanal();
    }

    public void downChannel() {
        televisor.bajarCanal();
    }

    public void desenchufar() {
        televisor.desenchufarTele();
    }
}