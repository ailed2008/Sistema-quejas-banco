package cnbv.service;

/*
     *Contrato para cualquier componente capaz de notificar resoluciones o cambios de estado en el sistema
     */
    public interface  Notificable{

        void enviarNotificacion(String destinatario, String mensaje);
    }

