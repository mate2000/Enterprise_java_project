package co.tiendaejb.logica;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.tiendaejb.interfaces.FachadaJPA;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanLogica<T> implements FachadaJPA<T> {

	@PersistenceContext(unitName = "db_tiendaPU")
	EntityManager entidad;

	@Override
	public void crear(T tipoEntidad) throws Exception {
		try {
			entidad.persist(tipoEntidad);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean actualizar(T tipoEntidad) throws Exception {

		try {
			entidad.merge(tipoEntidad);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void eliminar(T tipoEntidad) throws Exception {
		entidad.remove(entidad.contains(tipoEntidad) ? tipoEntidad:entidad.merge(tipoEntidad));
	}

	@Override
	public List<T> listar(Class<T> tipoEntidad) throws Exception {
		String namedQuery = tipoEntidad.getSimpleName().concat(".TODOS");
		List<T> list = new ArrayList<>();
		TypedQuery<T> consulta = entidad.createNamedQuery(namedQuery, tipoEntidad);
		list = consulta.getResultList();
		return list;
	}

	@Override
	public T buscar(Class<T> tipoEntidad, int identificador) throws Exception {
		T temp = null;
		temp = (T) entidad.find(tipoEntidad, identificador);
		return temp;
	}

	@Override
	public List<T> listar(Class<T> tipoEntidad, String namedQuery, String columnName, Integer valor) throws Exception {
		String mynamedQuery = tipoEntidad.getSimpleName().concat(namedQuery);
		List<T> list = new ArrayList<>();
		TypedQuery<T> consulta = entidad.createNamedQuery(mynamedQuery, tipoEntidad);

		if (!columnName.equals("")) {
			consulta.setParameter(columnName, valor);
		}

		list = consulta.getResultList();
		return list;
	}

}
