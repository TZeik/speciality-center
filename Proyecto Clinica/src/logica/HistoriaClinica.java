package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8551858366988969720L;
	private ArrayList<Cita> misCitas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	
	public HistoriaClinica() {
		super();
		this.misCitas = new ArrayList<Cita>();
		this.misConsultas = new ArrayList<Consulta>();
		this.misVacunas = new ArrayList<Vacuna>();;
	}
	
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	
	public void insertarVacuna(Vacuna auxV) {
		misVacunas.add(auxV);
	}
	
	public void insertarCoonsulta (Consulta auxC) {
		misConsultas.add(auxC);
	}

	
}
