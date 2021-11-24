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
	private int enfermedadCodeGenerator;
	private int vaccineCodeGenerator;
	
	private Clinica() {
		super();
		
		this.misPacientes = new ArrayList<Paciente>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.misCitas = new ArrayList<Cita>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.userCodeGenerator = 1;
		this.enfermedadCodeGenerator = 1;
		this.vaccineCodeGenerator = 1;
	}
	
	public static Clinica getInstance() {
		if(soul == null) {
			soul = new Clinica();
		}
		return soul;
	}
	
	public void setUserCodeGenerator(int userCodeGenerator) {
		this.userCodeGenerator = userCodeGenerator;
	}

	public void setEnfermedadCodeGenerator(int enfermedadCodeGenerator) {
		this.enfermedadCodeGenerator = enfermedadCodeGenerator;
	}

	public int getUserCodeGenerator() {
		return userCodeGenerator;
	}

	public int getEnfermedadCodeGenerator() {
		return enfermedadCodeGenerator;
	}
	
	public int getVaccineCodeGenerator() {
		return vaccineCodeGenerator;
	}

	public void setVaccineCodeGenerator(int vaccineCodeGenerator) {
		this.vaccineCodeGenerator = vaccineCodeGenerator;
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
	
	public String GenerateUserCode() {
		
		String codigo;
		codigo = "U-" + userCodeGenerator;
		return codigo;
	}
	
	public String GenerateEnfCode() {
		
		String codigo;
		codigo = "E-" + enfermedadCodeGenerator;
		return codigo;
	}
	
	public String GenerateVacCode() {
		
		String codigo;
		codigo = "V-" + vaccineCodeGenerator;
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
	
	public ArrayList<Usuario> tipoEspecifico(int tipo){
		// Sea tipo = 0 -> Administrador
		// Sea tipo = 1 -> Medico
		// Sea tipo = 2 -> Secretario
		
		ArrayList<Usuario> especificos = new ArrayList<Usuario>();
		for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
			
			switch (tipo) {
			case 0:
				if(user instanceof Administrador) {
					especificos.add(user);
				}
				break;
			case 1:
				if(user instanceof Medico) {
					especificos.add(user);
				}
				break;
			case 2:
				if(user instanceof Secretario) {
					especificos.add(user);
				}
				break;
			default:
				break;
			}
			
		}
			
		return especificos;

	}
	
	public Enfermedad SearchEnfermedad(String codigo) {
		Enfermedad enfermedad = new Enfermedad(codigo);
		
		for (Enfermedad enf : Clinica.getInstance().getMisEnfermedades()) {
			
			if(enfermedad.getCodigo().equals(enf.getCodigo())) {
				enfermedad = enf;
			}
			
		}
		
		return enfermedad;
	}
	
	public Vacuna SearchVacuna(String codigo) {
		Vacuna vacuna = new Vacuna(codigo);
		
		for (Vacuna vac : Clinica.getInstance().getMisVacunas()) {
			
			if(vacuna.getCodigo().equals(vac.getCodigo())) {
				vacuna =  vac;
			}
			
		}
		
		return vacuna;
	}
	
	public void EditUsuario(Usuario newUser) {
	
		for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
			if(newUser.getCodigo().equals(user.getCodigo())) {
				Clinica.getInstance().getMisUsuarios().remove(user);
				Clinica.getInstance().getMisUsuarios().add(newUser);
			}
		}
		
	}
	

	public void Logout() {
		Clinica.getInstance().logedUser = null;
		Clinica.getInstance().guardarClinica();
		
	}


}