package com.udemweb.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.udemweb.utilidades.MostrarMensaje;

import co.tiendaejb.modelos.Listasprecio;
import co.tiendaejb.servicios.serviciosBasicos;

@SessionScoped
@Named("beanListaPrecio")
public class listaPrecioController implements Serializable {

	

	private static final long serialVersionUID = 1L;
	private Listasprecio objListPrecio;
	private List<Listasprecio> listListaPrecios;
	//private Integer temp;
	@EJB
	serviciosBasicos<Listasprecio> servicioListaPrecios;

	public listaPrecioController() {
		super();
		objListPrecio = new Listasprecio();
	}

	@PostConstruct
	public void init() {
		listarListPrecios();
	}

	public Listasprecio getObjListPrecio() {
		return objListPrecio;
	}

	public void setObjListPrecio(Listasprecio objListPrecio) {
		this.objListPrecio = objListPrecio;
	}

	public List<Listasprecio> getListListaPrecios() {
		return listListaPrecios;
	}

	public void setListListaPrecios(List<Listasprecio> listListaPrecios) {
		this.listListaPrecios = listListaPrecios;
	}

	public void crearListaPrecios() {
		try {
			servicioListaPrecios.crear(objListPrecio);
			objListPrecio = new Listasprecio();
			listarListPrecios();
			MostrarMensaje.pantalla("listaPreciotxt", "exitoCrear", "exitoCrearDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("listaPreciotxt", "errorCrear", "errorCrearDetalle", "msgGrowl", "error");
		}
	}

	public void listarListPrecios() {
		try {
			listListaPrecios = servicioListaPrecios.listar(Listasprecio.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getEstadoTexto(int estado) {
		String est;

		switch (estado) {
		case 1:
			est = "Activo";
			break;
		default:
			est = "Incativo";
			break;
		}
		return est;
	}

	public String Editar(Listasprecio pro) {
		//temp = pro.getCodcategoriaCat();
		objListPrecio = pro;
		return "editar.faces";
	}

	public void editarLista() {

		try {
			servicioListaPrecios.actualizar(objListPrecio);
			listarListPrecios();
			MostrarMensaje.pantalla("listaPreciotxt", "exitoEditar", "exitoEditarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("listaPreciotxt", "errorEditar", "errorEditarDetalle", "msgGrowl", "error");
		}
	}

	public void borrar(Listasprecio temporal) {
		this.objListPrecio = temporal;

	}

	public void borrarLista() {

		try {
			servicioListaPrecios.eliminar(objListPrecio);
			objListPrecio = new Listasprecio();
			// objCategoria = new Categoria();
			//temp = null;
			listarListPrecios();
			MostrarMensaje.pantalla("listaPreciotxt", "exitoEliminar", "exitoEliminarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("listaPreciotxt", "errorEliminar", "errorEliminarDetalle", "msgGrowl", "error");
		}
	}
	
	public void resetearListaPrecio(){
		objListPrecio = new Listasprecio();
	}

}