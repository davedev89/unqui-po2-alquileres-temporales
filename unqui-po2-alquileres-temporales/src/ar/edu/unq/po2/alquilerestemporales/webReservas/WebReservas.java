package ar.edu.unq.po2.alquilerestemporales.webReservas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.alquilerestemporales.filtro.Filtro;
import ar.edu.unq.po2.alquilerestemporales.filtro.FiltroBasico;
import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;
import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public class WebReservas {
	
	private List<Usuario> usuarios;
    private BibliotecaDePublicaciones bibliotecaDePublicaciones;
    private BibliotecaDeReservas bibliotecaDeReservas;
    private List<String> categoriasCalificables;
    private List<String> servicios;
    private List<String> tiposDeInmueble;
    private Buscador buscador;

    public WebReservas() {
		this.usuarios = new ArrayList<Usuario>();
		this.bibliotecaDePublicaciones = new BibliotecaDePublicaciones();
		this.bibliotecaDeReservas = new BibliotecaDeReservas();
		this.categoriasCalificables = new ArrayList<String>();
		this.tiposDeInmueble = new ArrayList<String>();
		this.servicios = new ArrayList<String>();
		this.buscador = new Buscador();
	}

    public void registrarUsuario(Usuario usuario){
        if(!this.esUsuarioRegistrado(usuario)){
            this.usuarios.add(usuario);
        }
    }

    public boolean esUsuarioRegistrado(Usuario usuario){
        return this.getUsuarios().contains(usuario);
    }

    public List<Usuario> getUsuarios(){
        return this.usuarios;
    }

    public void publicar(Publicacion publicacion){
        this.bibliotecaDePublicaciones.cargarPublicacion(publicacion);
    }

    public void agregarTipoDeInmueble(String tipoInmueble){
        if(!hayTipoDeInmueble(tipoInmueble)){
            this.tiposDeInmueble.add(tipoInmueble);
        }
    }

    public boolean hayTipoDeInmueble(String tipoInmueble) {
		return this.getTiposDeInmueble().contains(tipoInmueble);
	}

    public List<String> getTiposDeInmueble(){
        return this.tiposDeInmueble;
    }

    public void agregarCategoriaCalificable(String nombreCategoria) {
		if(!hayCategoria(nombreCategoria)) {
			this.categoriasCalificables.add(nombreCategoria);
		}
	}

    public boolean hayCategoria(String nombreCategoria) {
		return this.getCategoriasCalificables().contains(nombreCategoria);
	}

    public List<String> getCategoriasCalificables() {
		return this.categoriasCalificables;
	}

    public void agregarServicio(String nombreServicio) {
		if(!hayServicio(nombreServicio)) {
			this.servicios.add(nombreServicio);
		}
	}

    private boolean hayServicio(String nombreServicio) {
		return this.getServicios().contains(nombreServicio);
	}

    public List<String> getServicios() {
		return this.servicios;
	}

    public void solicitarReserva(Reserva reserva){
        this.bibliotecaDeReservas.crearReserva(reserva);
    }

    public void aceptarReserva(Reserva reserva){
        this.bibliotecaDeReservas.concretarReserva(reserva);
    }

    public void rechazarReserva(Reserva reserva){
        this.bibliotecaDeReservas.rechazarReserva(reserva);
    }

    public void cancelarReserva(Reserva reserva){
        this.bibliotecaDeReservas.declinarReserva(reserva);
    }

    public List<Reserva> getTodasLasReservas(){
        return this.bibliotecaDeReservas.getTodasLasReservas();
    }

    public List<Reserva> todasLasReservas(Usuario usuario){
        return this.bibliotecaDeReservas.getReservasDeUsuario(usuario);
    }

    public List<Reserva> reservasFuturas(Usuario usuario){
        return this.bibliotecaDeReservas.getReservasFuturas(usuario);
    }

    public List<String> ciudadesConReserva(Usuario usuario){
        return this.bibliotecaDeReservas.getCiudadesReservadas(usuario);
    }

    public List<Reserva> reservasDeUsuarioEnCiudad(Usuario usuario, String ciudad){
        return this.bibliotecaDeReservas.getReservasEnCiudadDelUsuario(usuario,ciudad);
    }

    public List<Publicacion> hacerBusqueda(FiltroBasico filtroBasico, List<Filtro> filtrosExtra){
        return this.buscador.buscar(this.getPublicaciones(), filtroBasico, filtrosExtra);
    }
    
    public List<Publicacion> getPublicaciones(){
    	return this.bibliotecaDePublicaciones.getPublicaciones();
    }

	public void setBibliotecaDePublicaciones(BibliotecaDePublicaciones bibliotecaDePublicaciones) {
		this.bibliotecaDePublicaciones=bibliotecaDePublicaciones;
	}

	public void setBibliotecaDeReservas(BibliotecaDeReservas bibliotecaDeReserva) {
		this.bibliotecaDeReservas=bibliotecaDeReserva;
	}

	public void setBuscador(Buscador buscador) {
		this.buscador=buscador;
	}
	
	public void calificarPropietario(Reserva reserva, Calificacion calificacionPropietartio, Calificacion calificacionInmueble) {
		this.bibliotecaDeReservas.calificarEstadia(reserva, calificacionPropietartio, calificacionInmueble);
	}
	
	public void calificarInquilino(Reserva reserva, Calificacion calificacion) {
		this.bibliotecaDeReservas.calificarInquilinato(reserva,calificacion);
	}
	
}
