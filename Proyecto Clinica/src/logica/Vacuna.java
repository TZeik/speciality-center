package logica;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2769523749078563771L;
	private String codigo;
	private String nombre;
	private int AnnoCreacion;
	
	public Vacuna(String codigo) {
		super();
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnnoCreacion() {
		return AnnoCreacion;
	}

	public void setAnnoCreacion(int annoCreacion) {
		AnnoCreacion = annoCreacion;
	}
	
}
