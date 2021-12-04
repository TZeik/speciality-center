package logica;

import java.io.Serializable;

public class Enfermedad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1412270773217006624L;
	private String codigo;
	private String nombre;
	private String tipo;
	private String descipcion;
	private boolean vigilancia;
	
	public Enfermedad(String codigo) {
		super();
		this.codigo = codigo;
		this.vigilancia = false;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public boolean isVigilancia() {
		return vigilancia;
	}

	public void setVigilancia(boolean vigilancia) {
		this.vigilancia = vigilancia;
	}
	
}
