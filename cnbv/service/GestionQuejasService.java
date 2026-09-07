package cnbv.service;

import cnbv.model.Queja;

import java.util.List;
import java.util.ArrayList;

public class GestionQuejasService {
    private final List<Queja> repositorioQuejas;
    public GestionQuejasService(){
        this.repositorioQuejas = new ArrayList<>();
    }
    public void registrarQueja(Queja queja){
        //validamos antes d eguardar
        queja.validarRequisitosFormales();
        this.repositorioQuejas.add(queja);
    }

    public List<Queja> obtenerTodas(){
        return new ArrayList<>(this.repositorioQuejas); //retorna copia de la lista
    }
}
