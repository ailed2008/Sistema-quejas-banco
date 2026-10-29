package cnbv.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class QuejaTarjetaCredito extends Queja{

    private String lugarSuceso;
    private LocalDateTime fechaSuceso;

    public QuejaTarjetaCredito(String folio, LocalDate fechaRecepcion, String numCliente, BigDecimal montoReclamado, Prioridad prioridad, String lugarEvento, LocalDateTime fechaEvento){
        super(folio, fechaRecepcion, numCliente, montoReclamado, prioridad);
        this.lugarSuceso = lugarEvento;
        this.fechaSuceso = fechaEvento;

        validarRequisitosFormales();
    }

    @Override
    public void validarRequisitosFormales() {
        //Validaciones
        //Que la fecha del suceso no sea del futuro.
        if(this.lugarSuceso == null || this.lugarSuceso.isBlank()){
            throw new IllegalArgumentException("El lugar del suceso es obligatorio");
        }
        if(this.fechaSuceso != null && this.fechaSuceso.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("La fecha del suceso no puede ser futura");
        }
        // Que el lugar del suceso no esté vacío o en blanco.
        System.out.println("Test");

    }
}
