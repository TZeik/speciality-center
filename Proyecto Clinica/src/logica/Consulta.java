package logica;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1170647756290026293L;
	private String codigo;
	private Date fecha;
	private String sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	
	public Consulta(String codigo, Date fecha, String sintomas, String diagnostico, Enfermedad enfermedad) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.enfermedad = enfermedad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
	
}
