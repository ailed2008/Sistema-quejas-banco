package cnbv.exception;
//Esta clase se utiliza en las clases de servicio o modelo
// Heredar de RuntimeException permite lanzarla sin obligar a usar 'throws' en cada método
public class ReglaNegocioException extends RuntimeException{
    public ReglaNegocioException(String mensaje){
        super(mensaje);
    }
    public ReglaNegocioException(String mensaje, Throwable causa){
        super(mensaje,causa);
    }
}
