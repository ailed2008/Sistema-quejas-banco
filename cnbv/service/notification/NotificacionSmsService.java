package cnbv.service.notification;

import cnbv.service.Notificable;

public class NotificacionSmsService implements Notificable {
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("Enviando sms al numero "+destinatario+" : "+mensaje);
    }
}
