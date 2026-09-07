package cnbv.service;

import cnbv.dto.QuejaDTO;
import cnbv.exception.ReglaNegocioException;
import cnbv.model.Prioridad;
import cnbv.model.QuejaBancaria;

import java.time.LocalDate;

public class MapeadorQuejaService {

    public QuejaBancaria convertirDTOaEntidad(QuejaDTO dto){
        //Declaramos la variable fuera del try para que tenga alcance en todo el método
        Prioridad prioridadEnum;
        // 1. Leemos los datos de la caja(Java bean) usando getters
        try {
           prioridadEnum = Prioridad.valueOf(dto.getPrioridad()); // valueOf se utiliza para convertir diferentes tipos de valores en una cadena de texto.

        }catch (IllegalArgumentException e){
            // Capturamos el error genérico y lo re-lanzamos como una excepción de negocio propia
            throw new ReglaNegocioException("La prioridad asignada no es válida: " + dto.getPrioridad());
        }

        //2.El tipo de sector que viene en el javaBean
        //instanciamos la subclase concreta correspondiente
        if("BANCARIO".equalsIgnoreCase(dto.getTipoSector())){
            return new QuejaBancaria(
                    dto.getFolio(),
                    LocalDate.now(),
                    dto.getNumCliente(),
                    dto.getMontoReclamado(),
                    prioridadEnum,
                    dto.getNumeroCuenta(),
                    dto.isContratoFirmadoAnexo()

            );
        }else{
               // throw new IllegalArgumentException("Tipo de sector no soportado: "+dto.getTipoSector() );
                throw new ReglaNegocioException("Sector no soportado por la CNBV: " + dto.getTipoSector());
        }
    }
}
