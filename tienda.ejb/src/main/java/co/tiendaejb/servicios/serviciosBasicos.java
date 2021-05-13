package co.tiendaejb.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import co.tiendaejb.interfaces.FachadaJPA;

@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
public class serviciosBasicos <T>{
	
	@EJB
	FachadaJPA<T> logica;
	
	public void crear(T tipoEntidad) throws Exception{
		logica.crear(tipoEntidad);
	}
	
    public boolean actualizar(T tipoEntidad) throws Exception{
    	return logica.actualizar(tipoEntidad);
    }
	
	public void eliminar(T tipoEntidad) throws Exception{
		logica.eliminar(tipoEntidad);
	}
	
	public List<T> listar(Class<T> tipoEntidad)throws Exception{
		return logica.listar(tipoEntidad);
	}
	
	public T buscar(Class<T> tipoEntidad, int identificador)throws Exception{
		return logica.buscar(tipoEntidad, identificador);
	}
	
	public List<T> listar(Class<T> tipoEntidad, String namedQuery, String columnName, Integer valor) throws Exception {
		return logica.listar(tipoEntidad, namedQuery, columnName, valor);
	}
	

}
