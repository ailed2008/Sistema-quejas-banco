package cnbv.model;/*
*   Representa los estados del ciclo de vida de una queja normada por la CNVB,
*   garantizando la integridad en la transición de fases del expediente
*/

public enum EstadoQueja {
    REGISTRADA("Registrada en el portal oficial", false),
    EN_INVESTIGACION("En proceso de analisis y peritaje", false),
    DICTAMINADA("Dictamen emitido por la autoridad", false),
    CERRADA("Expediente concluido y archivado", true);

    private final String descripcion;
    private final boolean esFinal;

    EstadoQueja(String descripcion, boolean esFinal) {
        this.descripcion = descripcion;
        this.esFinal = esFinal;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public boolean isEsFinal() {
        return this.esFinal;
    }

    /*
     * Valida si el flujo de negocio permite avanzar el estado actual (this)
     * hacia el estado destino solicitado.
     *
     * @param nuevoEstado Estado al que se intenta cambiar
     * @return true si la transición es legal; false en caso contrario
     * */

    public boolean puedeTransicionarA(EstadoQueja nuevoEstado) {
        if (nuevoEstado == null) {
            return false;
        }
        //Si el estado actual ya es terminal, no puede cambiar a nada más
        if (this.esFinal) {
            return false;
        }

        return switch (this){
            case REGISTRADA -> nuevoEstado ==EN_INVESTIGACION;
            case EN_INVESTIGACION -> nuevoEstado== DICTAMINADA;
            case DICTAMINADA -> nuevoEstado == CERRADA;
            case CERRADA -> false; // Redundante por la validación previa de esFinal, pero explícito
        };


    }

}