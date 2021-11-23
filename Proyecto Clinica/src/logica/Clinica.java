package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Clinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1863189289817691555L;
	public static Clinica soul = null;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Cita> misCitas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	private Usuario logedUser = null;
	private int userCodeGenerator;
	
	private Clinica() {
		super();
		
		this.misPacientes = new ArrayList<Paciente>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.misCitas = new ArrayList<Cita>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.userCodeGenerator = 1;
	}
	
	public static Clinica getInstance() {
		if(soul == null) {
			soul = new Clinica();
		}
		return soul;
	}

	public static Clinica getSoul() {
		return soul;
	}

	public static void setSoul(Clinica soul) {
		Clinica.soul = soul;
	}

	public Usuario getLogedUser() {
		return logedUser;
	}

	public void setLogedUser(Usuario logedUser) {
		this.logedUser = logedUser;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	
	
	public void insertarPaciente (Paciente auxP) {
		misPacientes.add(auxP);
	}
	public void insertarCita (Cita AuxC) {
		misCitas.add(AuxC);
	}
	public void insertarEnfermedad(Enfermedad auxE) {
		misEnfermedades.add(auxE);
	}
	public void insertarVacuna(Vacuna auxV) {
		misVacunas.add(auxV);
	}
	public void insertarUsuario(Usuario auxU) {
		misUsuarios.add(auxU);
	}
	
	public void cargarClinica() {
		FileInputStream archivo;
		ObjectInputStream oos;
		
		try {
			archivo = new FileInputStream("clinica.dat");
			oos = new ObjectInputStream(archivo);
			Clinica.setSoul((Clinica)oos.readObject());
			oos.close();
		} catch (IOException | ClassNotFoundException e) {
			Clinica.getInstance().guardarClinica();
		}


	}
	
	public void guardarClinica() {
		
		FileOutputStream archivo;
			try {
				archivo = new FileOutputStream("clinica.dat");
				ObjectOutputStream oos = new  ObjectOutputStream(archivo);
				oos.writeObject(Clinica.getInstance());
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public String getUserCodeGenerator() {
		String codigo;
		codigo = "U-" + userCodeGenerator;
		
		userCodeGenerator++;
		return codigo;
	}
	
	public Paciente buscarPacienteByCed(String CedPaciente) {
		Paciente auxP= null;
		boolean encontrado = false ;
		int i=0;
		while (!encontrado || i<misPacientes.size()) {
			if(misPacientes.get(i).getCedula().equalsIgnoreCase(CedPaciente)) {
				auxP = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return auxP;
		
	}
	
	public boolean confirmLogin (String id, String password) {
		
		boolean login = false;
		
		
		for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
			
			System.out.println(user.getPassword());
			System.out.println(password);
			
			if(user.getId().equals(id) && user.getPassword().equals(password)) {
				logedUser = user;
				login = true;
			}
		}
		return login;
	}
	
	public boolean isFirst() {
		
		boolean first = false;
		
		if(Clinica.getInstance().misUsuarios.isEmpty() == true) {
			first = true;
		}
		
		return first;
	}


	
}