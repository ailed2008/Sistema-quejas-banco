package cnbv.service.notification;

import cnbv.service.Notificable;

public class NotificacionEmailService implements Notificable {
    @Override
    public void enviarNotificacion(String destinatario, String mensaaje){
        //Logica especifica para enviar correos SMTP
        System.out.println("Enviando correo a "+ destinatario+" : "+mensaaje );
    }
}
