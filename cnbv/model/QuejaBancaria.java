package cnbv.model;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Objects;
public class QuejaBancaria extends Queja {
    private final String numeroCuenta;
    private boolean contratoFirmadoAnexo;
    public QuejaBancaria(String folio, LocalDate fechaRecepcion, String numCliente,
                         BigDecimal montoReclamado, Prioridad prioridad,
                         String numeroCuenta, boolean contratoFirmadoAnexo) {

        super(folio, fechaRecepcion, numCliente, montoReclamado, prioridad);

        this.numeroCuenta = Objects.requireNonNull(numeroCuenta, "El número de cuenta es obligatorio");
        this.contratoFirmadoAnexo = contratoFirmadoAnexo;
    }

    @Override
    public void validarRequisitosFormales(){
        if(!contratoFirmadoAnexo){
            throw new IllegalStateException("Falta la copia del contrato firmado");
        }
    }
    public String getNumeroCuenta() { return numeroCuenta; }
    public boolean isContratoFirmadoAnexo() { return contratoFirmadoAnexo; }
}

