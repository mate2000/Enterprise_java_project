package co.tiendaejb.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codproducto_pro")
	private Integer codproductoPro;

	@Column(name="descripcion_pro")
	private String descripcionPro;

	@Column(name="iva_pro")
	private BigDecimal ivaPro;

	@Column(name="nombreproducto_pro")
	private String nombreproductoPro;

	@Column(name="referencia_pro")
	private String referenciaPro;

	//bi-directional many-to-one association to Detallespedido
	@OneToMany(mappedBy="producto")
	private List<Detallespedido> detallespedidos;

	//bi-directional many-to-one association to Imagene
	@OneToMany(mappedBy="producto")
	private List<Imagene> imagenes;

	//bi-directional many-to-one association to Precio
	@OneToMany(mappedBy="producto")
	private List<Precio> precios;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codcategoria_cat")
	private Categoria categoria;

	public Producto() {
	}

	public Integer getCodproductoPro() {
		return this.codproductoPro;
	}

	public void setCodproductoPro(Integer codproductoPro) {
		this.codproductoPro = codproductoPro;
	}

	public String getDescripcionPro() {
		return this.descripcionPro;
	}

	public void setDescripcionPro(String descripcionPro) {
		this.descripcionPro = descripcionPro;
	}

	public BigDecimal getIvaPro() {
		return this.ivaPro;
	}

	public void setIvaPro(BigDecimal ivaPro) {
		this.ivaPro = ivaPro;
	}

	public String getNombreproductoPro() {
		return this.nombreproductoPro;
	}

	public void setNombreproductoPro(String nombreproductoPro) {
		this.nombreproductoPro = nombreproductoPro;
	}

	public String getReferenciaPro() {
		return this.referenciaPro;
	}

	public void setReferenciaPro(String referenciaPro) {
		this.referenciaPro = referenciaPro;
	}

	public List<Detallespedido> getDetallespedidos() {
		return this.detallespedidos;
	}

	public void setDetallespedidos(List<Detallespedido> detallespedidos) {
		this.detallespedidos = detallespedidos;
	}

	public Detallespedido addDetallespedido(Detallespedido detallespedido) {
		getDetallespedidos().add(detallespedido);
		detallespedido.setProducto(this);

		return detallespedido;
	}

	public Detallespedido removeDetallespedido(Detallespedido detallespedido) {
		getDetallespedidos().remove(detallespedido);
		detallespedido.setProducto(null);

		return detallespedido;
	}

	public List<Imagene> getImagenes() {
		return this.imagenes;
	}

	public void setImagenes(List<Imagene> imagenes) {
		this.imagenes = imagenes;
	}

	public Imagene addImagene(Imagene imagene) {
		getImagenes().add(imagene);
		imagene.setProducto(this);

		return imagene;
	}

	public Imagene removeImagene(Imagene imagene) {
		getImagenes().remove(imagene);
		imagene.setProducto(null);

		return imagene;
	}

	public List<Precio> getPrecios() {
		return this.precios;
	}

	public void setPrecios(List<Precio> precios) {
		this.precios = precios;
	}

	public Precio addPrecio(Precio precio) {
		getPrecios().add(precio);
		precio.setProducto(this);

		return precio;
	}

	public Precio removePrecio(Precio precio) {
		getPrecios().remove(precio);
		precio.setProducto(null);

		return precio;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}