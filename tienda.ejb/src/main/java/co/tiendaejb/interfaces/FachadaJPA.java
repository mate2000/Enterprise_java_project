package co.tiendaejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface FachadaJPA<T> {

	
	public void crear(T tipoEntidad) throws Exception;
	
	public boolean actualizar(T tipoEntidad) throws Exception;
	
	public void eliminar(T tipoEntidad) throws Exception;
	
	public List<T> listar(Class<T> tipoEntidad)throws Exception;
	
	public T buscar(Class<T> tipoEntidad, int identificador)throws Exception;
	
	public List<T> listar(Class<T> tipoEntidad,String namedQuery, String columnName,Integer valor)throws Exception;			
}
