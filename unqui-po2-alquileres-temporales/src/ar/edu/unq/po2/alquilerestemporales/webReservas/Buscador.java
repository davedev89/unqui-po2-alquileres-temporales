package ar.edu.unq.po2.alquilerestemporales.webReservas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.alquilerestemporales.filtro.Filtro;
import ar.edu.unq.po2.alquilerestemporales.filtro.FiltroBasico;
import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public class Buscador {

    public Buscador(){}

    public List<Publicacion> buscar (List<Publicacion> publicaciones, FiltroBasico filtroBasico, List<Filtro> filtrosExtra){
        
        List<Publicacion> filtradas = new ArrayList<Publicacion>();
        for(Publicacion publicacion : publicaciones){
            if(this.pasaFiltros(publicacion, filtroBasico, filtrosExtra)){
                filtradas.add(publicacion);
            }
        }
        return filtradas;
    }

    public boolean pasaFiltros(Publicacion publicacion, FiltroBasico filtroBasico, List<Filtro>filtrosExtra){
        
        boolean resultado = filtroBasico.cumpleFiltrado(publicacion);
        for(Filtro filtroExtra : filtrosExtra){
            resultado = resultado && filtroExtra.cumpleFiltrado(publicacion);
        }
        return resultado;
    }
}

	
