package cnbv.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuejaDTO implements Serializable {
    //1.Atributos privados (lo que guardamos en la caja)
    private String folio;
    private String numCliente;
    private BigDecimal montoReclamado;
    private String tipoSector;
    private String prioridad;
    private String numeroCuenta;
    private String clave;
    private boolean contratoFirmadoAnexo;
    //2. Constructor publico vacio (Es obligatorio por el estandar JavaBean)
    public QuejaDTO(){

    }
    //3. Metodos Getter y Setter (para guardar y obtener datos)
    public String getFolio(){ return folio;}
    public void setFolio(String folio) {this.folio = folio;}

    public String getNumCliente(){ return numCliente;}
    public void setNumCliente(String numCliente){this.numCliente=numCliente;}

    public BigDecimal getMontoReclamado() {
        return montoReclamado;
    }

    public void setMontoReclamado(BigDecimal montoReclamado) {
        this.montoReclamado = montoReclamado;
    }

    public String getTipoSector() {
        return tipoSector;
    }

    public void setTipoSector(String tipoSector) {
        this.tipoSector = tipoSector;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // 2. Agrega el Getter (en booleanos se usa "is" en lugar de "get")
    public boolean isContratoFirmadoAnexo() {
        return contratoFirmadoAnexo;
    }

    // 3. Agrega el Setter
    public void setContratoFirmadoAnexo(boolean contratoFirmadoAnexo) {
        this.contratoFirmadoAnexo = contratoFirmadoAnexo;
    }
}
