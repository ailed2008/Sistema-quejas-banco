package cnbv.model;

import cnbv.service.Notificable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Queja{
    private String folio;
    private LocalDate fechaRecepcion;
    private String numCliente;
    private BigDecimal montoReclamado;
    private Prioridad prioridad;
    private EstadoQueja estado;
    private String dictamen;

    public Queja( String folio, LocalDate fechaRecepcion, String numCliente,BigDecimal montoReclamado, Prioridad prioridad ){
        this.folio = Objects.requireNonNull(folio,"El folio no puede ser nulo");
        this.fechaRecepcion = Objects.requireNonNull(fechaRecepcion, "La fecha de recepción no puede ser nula");
        this.numCliente=Objects.requireNonNull(numCliente,"El numero de cliente no puede ser nulo");
        if(montoReclamado == null || montoReclamado.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El monto reclamado debe ser mayor o igual a cero");
        }
        this.montoReclamado = montoReclamado;
        this.prioridad=Objects.requireNonNull(prioridad,"La prioridad no puede ser nula");
        this.estado=EstadoQueja.REGISTRADA;
        /**
         * Estado inicial obligatorio por norma
         **/
    }

    //=========================================
    //METODOS CONCRETOS DE NEGOCIO
    //============================================

    /*
     * Cambia el estado de la queja asegurando la integridad de a
     */
    public void cambiarEstado(EstadoQueja nuevoEstado){
         if (!this.estado.puedeTransicionarA(nuevoEstado)){
             throw new IllegalStateException(String.format("Transicion no permitida: No se puede pasar de %s a %s en el folio %s",this.estado,nuevoEstado,this.folio));
        }
         this.estado = nuevoEstado;
    }

    /*
     * Emite el dictamen final del expediente y actualiza su estado
     */
    public void emitirDictamen(String dictamen){
        if(dictamen==null || dictamen.isBlank()){
            throw new IllegalArgumentException("El dictamen no puede estar vacio");
        }
        cambiarEstado(EstadoQueja.DICTAMINADA);
        this.dictamen =dictamen;
    }

    public LocalDate getFechaLimiteSLA(){
        return this.prioridad.calcularFechaLimite(this.fechaRecepcion);
    }

    //====================================================================
    //METODOS ABSTRACTOS (Contrato obligatorio para clases hijas)
    //====================================================================
    public abstract void validarRequisitosFormales();

    //=====================================================================
    //GETTERS (Lectura controlada, sin setters)
    //=====================================================================
    public String getFolio(){ return folio;}
    public LocalDate getFechaRecepcion(){return fechaRecepcion;}
    public String getNumCliente(){return numCliente;}
    public  BigDecimal getMontoReclamado(){ return montoReclamado;}
    public EstadoQueja getEstado(){ return estado;}

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public String getDictamen() {
        return dictamen;
    }

    public void setPrioridad(Prioridad prioridad){
        this.prioridad = Objects.requireNonNull(prioridad,"La prioridad no puede ser nula");
    }



}
