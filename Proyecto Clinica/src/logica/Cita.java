package logica;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6934284300176996654L;
	private String codigo;
	private String nombre;
	private Date fechaNacimiento;
	private Date fecha;
	private String finalidad;
	private String telefono;
	private String especialidad;
	private Medico medico;
	
	public Cita(String codigo, Date fecha, String finalidad, String nombre, String telefono, String especialidad,
			Medico medico) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.finalidad = finalidad;
		this.nombre = nombre;
		this.telefono = telefono;
		this.especialidad = especialidad;
		this.medico = medico;
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

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
