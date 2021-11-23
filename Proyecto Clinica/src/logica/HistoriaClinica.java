package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8551858366988969720L;
	private String codigo;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	
	public HistoriaClinica(String codigo, ArrayList<Consulta> misConsultas, ArrayList<Vacuna> misVacunas) {
		super();
		this.codigo = codigo;
		this.misConsultas = new ArrayList<Consulta>();
		this.misVacunas = new ArrayList<Vacuna>();;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
