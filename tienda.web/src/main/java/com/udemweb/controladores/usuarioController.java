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
import co.tiendaejb.modelos.Usuario;
import co.tiendaejb.servicios.serviciosBasicos;

@SessionScoped
@Named("beanUsuario")
public class usuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario objUsuario;
	private Listasprecio objListasprecio;
	private Integer temp;

	private List<Usuario> listUsuarios;

	@EJB
	serviciosBasicos<Usuario> serviciosUsuario;

	@EJB
	serviciosBasicos<Listasprecio> serviciosListaP;

	public usuarioController() {
		super();
		objUsuario = new Usuario();
		objListasprecio = new Listasprecio();
	}

	@PostConstruct
	public void init() {
		listarTodosUsuarios();
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Usuario getObjUsuario() {
		return objUsuario;
	}

	public void setObjUsuario(Usuario objUsuario) {
		this.objUsuario = objUsuario;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public void crearUsuario() {

		try {
			objListasprecio = serviciosListaP.buscar(Listasprecio.class, temp);
			objUsuario.setListasprecio(objListasprecio);
			serviciosUsuario.crear(objUsuario);
			objUsuario = new Usuario();
			objListasprecio = new Listasprecio();
			temp = null;
			listarTodosUsuarios();
			MostrarMensaje.pantalla("usuariostxt", "exitoCrear", "exitoCrearDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("usuariostxt", "errorCrear", "errorCrearDetalle", "msgGrowl", "error");
		}
	}

	public List<SelectItem> getListPComboJPA() {
		try {
			List<SelectItem> combito = new ArrayList<>();
			List<Listasprecio> listaListP = serviciosListaP.listar(Listasprecio.class, ".XESTADOS", "codEstado", 1);
			for (Listasprecio ListP : listaListP) {
				SelectItem itemCat = new SelectItem(ListP.getCodlistaLp(), ListP.getNombrelistaLp());
				combito.add(itemCat);
			}
			return combito;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void listarTodosUsuarios() {
		try {
			listUsuarios = serviciosUsuario.listar(Usuario.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String editar(Usuario pro) {
		temp = pro.getListasprecio().getCodlistaLp();
		objUsuario = pro;
		return "editar.faces";
	}

	public void editarProducto() {

		try {
			objListasprecio = serviciosListaP.buscar(Listasprecio.class, temp);
			objUsuario.setListasprecio(objListasprecio);
			serviciosUsuario.actualizar(objUsuario);

			listarTodosUsuarios();
			MostrarMensaje.pantalla("usuariostxt", "exitoEditar", "exitoEditarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("usuariostxt", "errorEditar", "errorEditarDetalle", "msgGrowl", "error");
		}
	}

	public void borrar(Usuario temporal) {
		this.objUsuario = temporal;

	}

	public void borrarUsuario() {

		try {
			serviciosUsuario.eliminar(objUsuario);
			objUsuario = new Usuario();
			temp = null;
			listarTodosUsuarios();
			MostrarMensaje.pantalla("usuariostxt", "exitoEliminar", "exitoEliminarDetalle", "msgGrowl", "exito");
		} catch (Exception e) {
			e.printStackTrace();
			MostrarMensaje.pantalla("usuariostxt", "errorEliminar", "errorEliminarDetalle", "msgGrowl", "error");
		}
	}

	public void reseterarUsuario() {
		objUsuario = new Usuario();
		temp=null;
	}
}
