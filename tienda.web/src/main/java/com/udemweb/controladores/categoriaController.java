package com.udemweb.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.udemweb.utilidades.MostrarMensaje;

import co.tiendaejb.modelos.Categoria;
import co.tiendaejb.servicios.serviciosBasicos;

@SessionScoped
@Named("beanCategoria")
public class categoriaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Categoria objCategoria;
	private List<Categoria> listCategorias;
	//private Integer temp;
	@EJB
	serviciosBasicos<Categoria> servicioCategoria;

	public categoriaController() {
		super();
		objCategoria = new Categoria();
	}

	@PostConstruct
	public void init() {
		listarTodasCategorias();
	}

	public Categoria getObjCategoria() {
		return objCategoria;
	}

	public void setObjCategoria(Categoria objCategoria) {
		this.objCategoria = objCategoria;
	}

	public List<Categoria> getListCategorias() {
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

	public void crearCategoria() {
		try {
			servicioCategoria.crear(objCategoria);
			objCategoria = new Categoria();
			listarTodasCategorias();
			MostrarMensaje.pantalla("categoriastxt", "exitoCrear", "exitoCrearDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("categoriastxt", "errorCrear", "errorCrearDetalle", "msgGrowl", "error");
		}
	}

	public void listarTodasCategorias() {
		try {
			listCategorias = servicioCategoria.listar(Categoria.class);
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

	public String Editar(Categoria pro) {
		//temp = pro.getCodcategoriaCat();
		objCategoria = pro;
		return "editar.faces";
	}

	public void editarCategoria() {

		try {
			servicioCategoria.actualizar(objCategoria);
			listarTodasCategorias();
			MostrarMensaje.pantalla("categoriastxt", "exitoEditar", "exitoEditarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("categoriastxt", "errorEditar", "errorEditarDetalle", "msgGrowl", "error");
		}
	}

	public void borrar(Categoria temporal) {
		this.objCategoria = temporal;

	}

	public void borrarCategoria() {

		try {
			servicioCategoria.eliminar(objCategoria);
			objCategoria = new Categoria();
			// objCategoria = new Categoria();
			//temp = null;
			listarTodasCategorias();
			MostrarMensaje.pantalla("categoriastxt", "exitoEliminar", "exitoEliminarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("categoriastxt", "errorEliminar", "errorEliminarDetalle", "msgGrowl", "error");
		}
	}
	
	public void resetearCategoria(){
		objCategoria = new Categoria();
	}

}