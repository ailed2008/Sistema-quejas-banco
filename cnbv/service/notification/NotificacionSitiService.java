package cnbv.service.notification;

import cnbv.service.Notificable;

public class NotificacionSitiService  implements Notificable {
    @Override
    public void enviarNotificacion(String destinatario, String mensaje){
        // Lógica para enviar vía API web del portal oficial SITI de la CNBV
        System.out.println("Evento enviado al portal SITI para "+destinatario);
    }
}
