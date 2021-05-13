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

import co.tiendaejb.modelos.Listasprecio;
import co.tiendaejb.modelos.Producto;
import co.tiendaejb.modelos.Precio;
import co.tiendaejb.servicios.serviciosBasicos;

@SessionScoped
@Named("beanPrecio")
public class precioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Precio objPrecio;
	private Producto objProducto;
	private Listasprecio objListasprecio;
	private Integer tempProducto;
	private Integer tempListaP;

	private List<Precio> listPrecios;

	@EJB
	serviciosBasicos<Precio> serviciosPrecios;

	@EJB
	serviciosBasicos<Producto> serviciosProducto;

	@EJB
	serviciosBasicos<Listasprecio> serviciosListasprecio;

	public precioController() {
		super();
		objPrecio = new Precio();
		objProducto = new Producto();
		objListasprecio = new Listasprecio();
	}

	@PostConstruct
	public void init() {
		listarTodosProductos();
	}

	public Integer getTempProducto() {
		return tempProducto;
	}

	public void setTempProducto(Integer tempProducto) {
		this.tempProducto = tempProducto;
	}

	public Integer getTempListaP() {
		return tempListaP;
	}

	public void setTempListaP(Integer tempListaP) {
		this.tempListaP = tempListaP;
	}

	public Precio getObjPrecio() {
		return objPrecio;
	}

	public void setObjPrecio(Precio objPrecio) {
		this.objPrecio = objPrecio;
	}

	public List<Precio> getListPrecios() {
		return listPrecios;
	}

	public void setListPrecios(List<Precio> listPrecios) {
		this.listPrecios = listPrecios;
	}

	public void crearPrecio() {

		try {
			objProducto = serviciosProducto.buscar(Producto.class, tempProducto);
			System.out.println("tempP " + tempProducto);		
			objPrecio.setProducto(objProducto);
			objListasprecio = serviciosListasprecio.buscar(Listasprecio.class, tempListaP);
			System.out.println("tempL " + tempListaP);
			objPrecio.setListasprecio(objListasprecio);
			serviciosPrecios.crear(objPrecio);

			objPrecio = new Precio();
			objProducto = new Producto();
			objListasprecio = new Listasprecio();
			tempProducto = null;
			tempListaP = null;
			listarTodosProductos();
			MostrarMensaje.pantalla("preciostxt", "exitoCrear", "exitoCrearDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("preciostxt", "errorCrear", "errorCrearDetalle", "msgGrowl", "error");
		}
	}

	public List<SelectItem> getProductosComboJPA() {
		try {
			List<SelectItem> combito = new ArrayList<>();
			List<Producto> listaProductos = serviciosProducto.listar(Producto.class);
			for (Producto producto : listaProductos) {
				SelectItem itemCat = new SelectItem(producto.getCodproductoPro(),
						producto.getNombreproductoPro());
				combito.add(itemCat);
			}
			return combito;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
     
	public List<SelectItem> getListPComboJPA() {
		try {
			List<SelectItem> combito = new ArrayList<>();
			List<Listasprecio> listaListP = serviciosListasprecio.listar(Listasprecio.class, ".XESTADOS", "codEstado", 1);
			for (Listasprecio ListP : listaListP) {
				SelectItem itemCat = new SelectItem(ListP.getCodlistaLp(),
						ListP.getNombrelistaLp());
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
			// listProductos = serviciosProductos.listar(Producto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String Editar(Producto pro) {
		// temp = pro.getCategoria().getCodcategoriaCat();
		objProducto = pro;
		return "editar.faces";
	}

	public void editarProducto() {

		try {
			// objCategoria = serviciosCategoria.buscar(Categoria.class, temp);
			// objProducto.setCategoria(objCategoria);
			// serviciosProductos.actualizar(objProducto);

			listarTodosProductos();
			MostrarMensaje.pantalla("productostxt", "exitoEditar", "exitoEditarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("productostxt", "errorEditar", "errorEditarDetalle", "msgGrowl", "error");
		}
	}

	public void borrar(Producto temporal) {
		this.objProducto = temporal;

	}

	public void borrarProducto() {

		try {
			// serviciosProductos.eliminar(objProducto);
			objProducto = new Producto();
			// objCategoria = new Categoria();
			// temp = null;
			listarTodosProductos();
			MostrarMensaje.pantalla("productostxt", "exitoEliminar", "exitoEliminarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("productostxt", "errorEliminar", "errorEliminarDetalle", "msgGrowl", "error");
		}
	}

}
