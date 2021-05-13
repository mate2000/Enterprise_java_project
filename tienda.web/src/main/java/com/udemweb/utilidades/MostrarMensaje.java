package com.udemweb.utilidades;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MostrarMensaje {

	private static void construir(Severity tipo, String titulo, String textoMensaje, String identificador) {
		if (identificador.equals("")) {
			identificador = null;
		}
		
		FacesMessage mensaje = new FacesMessage(tipo, titulo, textoMensaje);
		FacesContext.getCurrentInstance().addMessage(identificador, mensaje);
	}
	
	private static void error(String titulo, String textoMensaje, String identificador) {
		MostrarMensaje.construir(FacesMessage.SEVERITY_ERROR, titulo, textoMensaje, identificador);
	}
	
	private static void exito(String titulo, String textoMensaje, String identificador) {
		MostrarMensaje.construir(FacesMessage.SEVERITY_INFO, titulo, textoMensaje, identificador);
	}
	
	private static void advertencia(String titulo, String textoMensaje, String identificador) {
		MostrarMensaje.construir(FacesMessage.SEVERITY_WARN, titulo, textoMensaje, identificador);
	}
	
	private static void fatal(String titulo, String textoMensaje, String identificador) {
		MostrarMensaje.construir(FacesMessage.SEVERITY_FATAL, titulo, textoMensaje, identificador);
	}
	
	public static void pantalla (String archivoPropiedades, String titulo, String textoMensaje, String identificador, String tipo) {
		String tituloFinal = ResourceBundle.getBundle(archivoPropiedades).getString(titulo);
		String detalleFinal = ResourceBundle.getBundle(archivoPropiedades).getString(textoMensaje);
		
		switch (tipo) {
		case "exito":
			MostrarMensaje.exito(tituloFinal, detalleFinal, identificador);
			break;
			
		case "error":
			MostrarMensaje.error(tituloFinal, detalleFinal, identificador);
			break;
			
		case "advertencia":
			MostrarMensaje.advertencia(tituloFinal, detalleFinal, identificador);
			break;

		default:
			MostrarMensaje.fatal(tituloFinal, detalleFinal, identificador);
			break;
		}


	}
	
}
