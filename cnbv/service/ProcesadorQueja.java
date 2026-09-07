package cnbv.service;

import cnbv.model.Queja;

public class ProcesadorQueja {
    private final Notificable servicionNotificacion;
    //Inyeccion de dependencias: se recibe ka interfaz, no la clase concreta
    public ProcesadorQueja(Notificable servicioNotificacion){

        this.servicionNotificacion =servicioNotificacion;

    }

    public void notificarDictamen(Queja queja){
        String mensaje = "Su queja con folio "+queja.getFolio() + "ha sido dictaminada. ";
        //Ejecuta el metodo sin importar la implementacion real
        servicionNotificacion.enviarNotificacion(queja.getNumCliente(),mensaje);
    }

}
