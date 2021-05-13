package com.udemweb.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.udemweb.utilidades.MostrarMensaje;

import co.tiendaejb.modelos.Categoria;
import co.tiendaejb.modelos.Producto;
import co.tiendaejb.servicios.serviciosBasicos;

@SessionScoped
@Named("beanProducto")
public class productoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Producto objProducto;
	private Categoria objCategoria;
	private Integer temp;

	private List<Producto> listProductos;

	@EJB
	serviciosBasicos<Producto> serviciosProductos;

	@EJB
	serviciosBasicos<Categoria> serviciosCategoria;

	public productoController() {
		super();
		objProducto = new Producto();
		objCategoria = new Categoria();
	}

	@PostConstruct
	public void init() {
		listarTodosProductos();
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Producto getObjProducto() {
		return objProducto;
	}

	public void setObjProducto(Producto objProducto) {
		this.objProducto = objProducto;
	}

	public List<Producto> getListProductos() {
		return listProductos;
	}

	public void setListProductos(List<Producto> listProductos) {
		this.listProductos = listProductos;
	}

	public void crearProducto() {

		try {
			objCategoria = serviciosCategoria.buscar(Categoria.class, temp);
			objProducto.setCategoria(objCategoria);
			serviciosProductos.crear(objProducto);
			objProducto = new Producto();
			objCategoria = new Categoria();
			temp = null;
			listarTodosProductos();
			MostrarMensaje.pantalla("productostxt", "exitoCrear", "exitoCrearDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("productostxt", "errorCrear", "errorCrearDetalle", "msgGrowl", "error");
		}
	}

	public List<SelectItem> getCategoriasComboJPA() {
		try {
			List<SelectItem> combito = new ArrayList<>();
			List<Categoria> listaCategorias = serviciosCategoria.listar(Categoria.class, ".XESTADOS", "codEstado", 1);
			for (Categoria laCategoria : listaCategorias) {
				SelectItem itemCat = new SelectItem(laCategoria.getCodcategoriaCat(),
						laCategoria.getNombrecategoriaCat());
				combito.add(itemCat);
			}
			return combito;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void listarTodosProductos() {
		try {
			listProductos = serviciosProductos.listar(Producto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public String Editar(Producto pro){
	   temp = pro.getCategoria().getCodcategoriaCat();
	   objProducto = pro;
	   return "editar.faces";
	} 
	
	
	public void editarProducto() {

		try {
			objCategoria = serviciosCategoria.buscar(Categoria.class, temp);
			objProducto.setCategoria(objCategoria);
			serviciosProductos.actualizar(objProducto);
			
			listarTodosProductos();
			MostrarMensaje.pantalla("productostxt", "exitoEditar", "exitoEditarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("productostxt", "errorEditar", "errorEditarDetalle", "msgGrowl", "error");
		}
	}
	

	public void borrar(Producto temporal){
		this.objProducto = temporal;
		
	}
	
	public void borrarProducto() {

		try {
			serviciosProductos.eliminar(objProducto);
			objProducto = new Producto();
			//objCategoria = new Categoria();
			temp = null;
			listarTodosProductos();
			MostrarMensaje.pantalla("productostxt", "exitoEliminar", "exitoEliminarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("productostxt", "errorEliminar", "errorEliminarDetalle", "msgGrowl", "error");
		}
	}
	
	public void resetearProducto(){
		objProducto = new Producto();
		//objCategoria = new Categoria();
		temp=null;
	}
	
}
