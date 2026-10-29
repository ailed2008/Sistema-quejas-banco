package cnbv.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *Define la prioridad de atención de una queja y calcula la fecha limite normativa
 * omitiendo fines de semana
 */
public enum Prioridad{
    BAJA("20 días hábiles de SLA",20),
    MEDIA("10 días hábiles de SLA",10),
    ALTA("5 días hábiles de SLA",5);

    private final String descripcion;
    private final int diasHabilesSLA;

    Prioridad(String descripcion, int diasHabilesSLA){
        this.descripcion=descripcion;
        this.diasHabilesSLA=diasHabilesSLA;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDiasHabilesSLA() {
        return diasHabilesSLA;
    }

    /**
     * Calcula la fecha limite sumando los dias habiles de SLA
     * a partir de la fecha de inicio
     * excluyendo sabados y domingos
     * */
    public LocalDate calcularFechaLimite(LocalDate fechaInicio){
        if(fechaInicio==null){
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
        }
        LocalDate fechaActual = fechaInicio;
        int diasAgregados=0;
        while(diasAgregados < this.diasHabilesSLA){
            fechaActual=fechaActual.plusDays(1);

            //Verificar si es dia habil (lunes a viernes)
            DayOfWeek diaSemana = fechaActual.getDayOfWeek();
            if(diaSemana != DayOfWeek.SATURDAY && diaSemana != DayOfWeek.SUNDAY){
                diasAgregados++;
            }
        }
        return fechaActual;
    }
}