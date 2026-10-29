package cnbv.service;

import cnbv.model.EstadoQueja;
import cnbv.model.Queja;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GestionQuejasService {
    private final List<Queja> repositorioQuejas;

    public GestionQuejasService()
    {
        this.repositorioQuejas = new ArrayList<>();
    }

    public void registrarQueja(Queja queja){
        //validamos antes de guardar
        queja.validarRequisitosFormales();

        if(buscarPorFolio(queja.getFolio()).isPresent()){
            throw new IllegalArgumentException("Ya existe una queja registrada con con el folio: " + queja.getFolio());
        }

        this.repositorioQuejas.add(queja);
    }

    public Optional<Queja> buscarPorFolio(String folio){
        if(folio ==null|| folio.isBlank()){
            return  Optional.empty();
        }
        return this.repositorioQuejas.stream()
                .filter(q -> q.getFolio().equals(folio))
                .findFirst();
    }

    public void avanzarEstadoQueja(String folio, EstadoQueja nuevoEstado){
        Queja queja = buscarPorFolio(folio)
                .orElseThrow(()-> new IllegalArgumentException("No se encontró la queja con el folio: " + folio));
        queja.cambiarEstado(nuevoEstado);
    }

    public List<Queja> obtenerTodas(){
        return new ArrayList<>(this.repositorioQuejas); //retorna copia de la lista
    }

    public void emitirDictamenQueja(String folio, String textoDictamen){
        Queja queja = buscarPorFolio(folio)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la queja con el folio: " + folio));

        queja.emitirDictamen(textoDictamen);
    }

    public List<Queja> filtarPorEstado(EstadoQueja estadoBuscado){
        return this.repositorioQuejas.stream()
                .filter(q -> q.getEstado() == estadoBuscado)
                .collect(Collectors.toList());
    }

}
