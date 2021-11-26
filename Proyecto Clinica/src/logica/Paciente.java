package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Paciente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4750282174471169681L;
	private String codigo;
	private String nombre;
	private String cedula;
	private String genero;
	private Calendar fechaNacimiento;
	private String direccion;
	private String telefono;
	private HistoriaClinica historial;
	
	public Paciente(String codigo,String nombre, String cedula, String genero,Calendar fechaNacimiento, String direccion, String telefono) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cedula = cedula;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.historial = new HistoriaClinica();
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public HistoriaClinica getHistorial() {
		return historial;
	}

	public void setHistorial(HistoriaClinica historial) {
		this.historial = historial;
	}




	
}
