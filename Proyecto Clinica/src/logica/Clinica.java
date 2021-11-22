package logica;

import java.util.ArrayList;

public class Clinica {

	public static Clinica soul = null;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Cita> misCitas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	
	
	private Clinica() {
		super();
		
		this.misPacientes = misPacientes;
		this.misUsuarios = misUsuarios;
		this.misCitas = misCitas;
		this.misEnfermedades = misEnfermedades;
		this.misVacunas = misVacunas;
	}
	
	public static Clinica getInstance() {
		if(soul == null) {
			soul = new Clinica();
		}
		
		return soul;
	}
	
}