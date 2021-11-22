package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Paciente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4750282174471169681L;
	private String nombre;
	private String cedula;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private HistoriaClinica historial;
	private ArrayList<Consulta> misConsultas;
	
	public Paciente(String nombre, String cedula, Date fechaNacimiento, String direccion, String telefono,
			HistoriaClinica historial, ArrayList<Consulta> misConsultas) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.historial = historial;
		this.misConsultas = misConsultas;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	
}
