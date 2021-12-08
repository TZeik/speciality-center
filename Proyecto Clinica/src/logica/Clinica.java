package logica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import arrancar.Servidor;
import exceptions.AuthenticationException;
import exceptions.UserRegistrationException;

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
	private Cita selectedCita = null;
	private Paciente selectedPaciente = null;
	private int userCodeGenerator;
	private int enfermedadCodeGenerator;
	private int vaccineCodeGenerator;
	private int citaCodeGenerator;
	private int consultaCodeGenerator;
	private int pacienteCodeGenerator;
	
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
		this.citaCodeGenerator = 1;
		this.consultaCodeGenerator = 1;
		this.pacienteCodeGenerator = 1;
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

	public int getCitaCodeGenerator() {
		return citaCodeGenerator;
	}

	public void setCitaCodeGenerator(int citaCodeGenerator) {
		this.citaCodeGenerator = citaCodeGenerator;
	}
	
	public int getConsultaCodeGenerator() {
		return consultaCodeGenerator;
	}

	public void setConsultaCodeGenerator(int consultaCodeGenerator) {
		this.consultaCodeGenerator = consultaCodeGenerator;
	}
	
	public int getPacienteCodeGenerator() {
		return pacienteCodeGenerator;
	}

	public void setPacienteCodeGenerator(int pacienteCodeGenerator) {
		this.pacienteCodeGenerator = pacienteCodeGenerator;
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
	
	public Cita getSelectedCita() {
		return selectedCita;
	}

	public void setSelectedCita(Cita selectedCita) {
		this.selectedCita = selectedCita;
	}
	
	public Paciente getSelectedPaciente() {
		return selectedPaciente;
	}

	public void setSelectedPaciente(Paciente selectedPaciente) {
		this.selectedPaciente = selectedPaciente;
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
	
	public String GenerateCitaCode() {
		
		String codigo;
		codigo = "C-" + citaCodeGenerator;
		return codigo;
	}
	
	public String GenerateConsultaCode() {
		
		String codigo;
		codigo = "O-" + consultaCodeGenerator;
		return codigo;
	}
	
	public String GeneratePacienteCode() {
		
		String codigo;
		codigo = "P-" + pacienteCodeGenerator;
		return codigo;
	}
	
	public Paciente buscarPacienteByCed(String CedPaciente) {
		
		Paciente paciente = null;
		
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			if(CedPaciente.equals(pac.getCedula())) {
				paciente = pac;
			}
		}
		return paciente;
	}
	
	public int buscarPacienteIndex(String codigo) {
		
		int i = 0;
		int index = 0;
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			if(pac.getCodigo().equals(codigo)) {
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public boolean confirmLogin (String id, String password) {
		
		boolean login = false;
		
		for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
			
			if(user.getId().equals(id) && user.getPassword().equals(password)) {
				Clinica.getInstance().setLogedUser(user);
				
				login = true;
			}
		}
		return login;
	}
	
	public void Logout() {
		Clinica.getInstance().setLogedUser(null);
		Clinica.getInstance().setSelectedCita(null);
		Clinica.getInstance().setSelectedPaciente(null);
		Clinica.getInstance().guardarClinica();
		
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
			
			if(enfermedad.getCodigo().equalsIgnoreCase(enf.getCodigo())) {
				enfermedad = enf;
			}
			
		}
		
		return enfermedad;
	}
	
	public Enfermedad SearchEnfermedadByName(String name) {
		
		Enfermedad enfermedad = null;
		
		for(Enfermedad enf :  Clinica.getInstance().getMisEnfermedades()) {
			if(name.equals(enf.getNombre())) {
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
	
	public Cita SearchCita(String codigo) {
		Cita cita = new Cita(codigo);
		
		for(Cita cit : Clinica.getInstance().getMisCitas()) {
			if(cita.getCodigo().equals(cit.getCodigo())) {
				cita = cit;
			}
		}
		
		return cita;
	}
	
	public Vacuna SearchVacunaByName(String nombre) {
		Vacuna vacuna = new Vacuna(null);
		
		for (Vacuna vac : Clinica.getInstance().getMisVacunas()) {
			
			if(nombre.equalsIgnoreCase(vac.getNombre())) {
				vacuna =  vac;
			}
			
		}
		
		return vacuna;
	}
	
	public Cita SearchCitaByName(String nombre) {
		Cita cita = new Cita(null);
		
		for(Cita cit : Clinica.getInstance().getMisCitas()) {
			if(nombre.equalsIgnoreCase(cit.getNombre())) {
				cita = cit;
			}
		}
		
		return cita;
	}
	
	public void EditUsuario(Usuario newUser) {
	
		for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
			if(newUser.getCodigo().equals(user.getCodigo())) {
				
				if(newUser instanceof  Administrador) {
					
					Administrador newAdmin = (Administrador)newUser;
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setNombre(newAdmin.getNombre());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setPassword(newAdmin.getPassword());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setId(newAdmin.getId());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setTelefono(newAdmin.getTelefono());
					((Administrador) Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user))).setPuesto(newAdmin.getPuesto());
					
				}
				if(newUser instanceof  Medico) {
					
					Medico newMed = (Medico)newUser;
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setNombre(newMed.getNombre());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setPassword(newMed.getPassword());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setId(newMed.getId());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setTelefono(newMed.getTelefono());
					((Medico) Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user))).setEspecialidad(newMed.getEspecialidad());
					
				}
				if(newUser instanceof Secretario) {
					
					Secretario newSec = (Secretario)newUser;
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setNombre(newSec.getNombre());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setPassword(newSec.getPassword());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setId(newSec.getId());
					Clinica.getInstance().getMisUsuarios().get(SearchUsuarioIndex(user)).setTelefono(newSec.getTelefono());
					
				}
			}
		}
		
	}
	
	public int SearchUsuarioIndex(Usuario usuario) {
		int i = 0;
		int index = 0;
		
		for (Usuario user : Clinica.getInstance().misUsuarios) {
			if(usuario.getCodigo().equals(user.getCodigo())) {
				index = i;
			}
			i++;
		}
		
		return index;
	}
	
	public ArrayList<Usuario> misMedicos(){
		
		ArrayList<Usuario> misMedicos = new ArrayList<Usuario>();
		
		for(Usuario user : Clinica.getInstance().getMisUsuarios()) {
			if(user instanceof Medico) {
				misMedicos.add(user);
			}
		}
		
		return misMedicos;
	}
	
	public ArrayList<Cita> SoloCitasMedico(){
		
		ArrayList<Cita> misCitas = new ArrayList<Cita>();
		
		if(Clinica.getInstance().getLogedUser() instanceof Administrador) {
			for(Cita cit : Clinica.getInstance().getMisCitas()) {
				misCitas.add(cit);
			}
		}else {
			for(Cita cit : Clinica.getInstance().getMisCitas()) {
				if(cit.getMedico().equals(Clinica.getInstance().getLogedUser())) {
					misCitas.add(cit);
				}
			}
		}

		return misCitas;
	}
	
	public Medico SearchMedicoByName(String name) {
		Medico medico = new Medico(null);
		
		for(Usuario user : Clinica.getInstance().misMedicos()) {
			if(user.getNombre().equals(name)) {
				medico = (Medico)user;
			}
		}
		
		return medico;
	}
	
	public void EditLogedUser(Usuario user) {
		
		Clinica.getInstance().logedUser.setNombre(user.getNombre());
		
	}
	
	public Paciente getPacienteByCita(Cita cita) {
		Paciente paciente = null;
				
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			if(cita.getCedula().equalsIgnoreCase(pac.getCedula())) {
				paciente =  pac;
			}
		}
		
		return paciente;
	}
	
	public void nuevoPaciente(Cita cita, Consulta consulta, Vacuna vacuna) {
		
		Paciente newPaciente = new Paciente(Clinica.getInstance().GeneratePacienteCode(),cita.getNombre(),cita.getCedula(),cita.getGenero(),cita.getFechaNacimiento(),cita.getDireccion(),cita.getTelefono());
		newPaciente.getHistorial().getMisCitas().add(cita);
		if(vacuna != null) {
			newPaciente.getHistorial().getMisVacunas().add(vacuna);

		}
		if(consulta != null) {
			newPaciente.getHistorial().getMisConsultas().add(consulta);
		}
		Clinica.getInstance().getMisPacientes().add(newPaciente);
		Clinica.getInstance().setPacienteCodeGenerator(Clinica.getInstance().getPacienteCodeGenerator() + 1);
		Clinica.getInstance().guardarClinica();
	}

	public Usuario SearchUsuarioCode(String code) {
		Usuario auxU = null;
		for(Usuario usua : misUsuarios ) {
			if (usua.getCodigo().equalsIgnoreCase(code)) {
				auxU = usua;
			}
		}
		return auxU;

	}
	
	public Paciente BuscarPacienteByConsultaCode(String codigo) {
		Paciente paciente = null;
		
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			for(Consulta cons : pac.getHistorial().getMisConsultas()) {
				if(cons.getCodigo().equalsIgnoreCase(codigo)) {
					paciente = pac;
				}
			}
		}
		
		return paciente;
	}

	public Paciente SearchPaciente(String codigo) {
		Paciente paciente = null;
		
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			if(pac.getCodigo().equalsIgnoreCase(codigo)) {
				paciente = pac;
			}
		}
		
		return paciente;
	}
	
	public Consulta SearchConsulta(String codigo) {
		Consulta  consulta = null;
		
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			for(Consulta cons : pac.getHistorial().getMisConsultas()) {
				if(cons.getCodigo().equalsIgnoreCase(codigo)) {
					consulta = cons;
				}
			}
		}
		return consulta;
	}
	
	public int GetLogedMedicoIndex() {
		int index = 0;
		int i = 1;
		if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			for(Usuario user : Clinica.getInstance().misMedicos()) {
				if(Clinica.getInstance().getLogedUser().getCodigo().equals(user.getCodigo())) {
					index = i;
				}
				i++;
			}
		}
		return index;
	}
	
	public int OpcionUserClinica() {
		int e = -1;
		
		if(Clinica.getInstance().getLogedUser() instanceof Administrador) {
			e = 0;
		}
		if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			e = 1;
		}
		if(Clinica.getInstance().getLogedUser() instanceof Secretario) {
			e = 2;
		}
		
		return e;
	}

	public void EliminarCita(String codigo) {
		
		int index = 0;
		
		for(Cita cit : Clinica.getInstance().getMisCitas()) {
			if(cit.getCodigo().equalsIgnoreCase(codigo)) {
				Clinica.getInstance().getMisCitas().remove(index);
			}
			index++;
		}
		
	}
	
	public void EliminarConsulta(String codigo) {
		
		int indexPaciente = 0;
		int indexConsulta;
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			indexConsulta = 0;
			for(Consulta cons : pac.getHistorial().getMisConsultas()) {
				if(cons.getCodigo().equalsIgnoreCase(codigo)) {
					Clinica.getInstance().getMisPacientes().get(indexPaciente).getHistorial().getMisConsultas().remove(indexConsulta);
				}
				indexConsulta++;
			}
			indexPaciente++;
		}
	}
	
	public Paciente SearchPacienteByName(String nombre) {
		Paciente paciente = null;
		
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			if(pac.getNombre().equalsIgnoreCase(nombre)) {
				paciente = pac;
			}
		}
		return paciente;
	}
	
	public ArrayList<Consulta> misConsultas(){
		
		ArrayList<Consulta> misConsultas =  new ArrayList<Consulta>();
		
		for(Paciente pac : Clinica.getInstance().getMisPacientes()) {
			for(Consulta cons : pac.getHistorial().getMisConsultas()) {
				misConsultas.add(cons);
			}
		}
		
		return misConsultas;
	}

	public int buscarEnfermedadIndex(Enfermedad enfermedad) {
		int index = 0;
		int i = 0;
		for(Enfermedad enf : Clinica.getInstance().getMisEnfermedades()) {
			if(enfermedad.getCodigo().equalsIgnoreCase(enf.getCodigo())) {
				index = i;
			}
			i++;
		}
			
		return index;
	}

	public int buscarVacunaIndex(Vacuna vacuna) {
		int i = 0;
		int index = 0;
		
		for(Vacuna vac : Clinica.getInstance().getMisVacunas()) {
			
			if(vac.getCodigo().equalsIgnoreCase(vacuna.getCodigo())) {
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public  int buscarPacienteIndex(Paciente paciente) {
		
		int index = 0;
		int i = 0;
		
		return index;
		
		
	}

	public int buscarUsuarioIndex(Usuario update) {
		int i = 0;
		int index = 0;
		
		for(Usuario usua : Clinica.getInstance().getMisUsuarios()) {
			
			if(usua.getCodigo().equalsIgnoreCase(update.getCodigo())) {
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public Usuario searchUsuarioByName(String name) {
		
		Usuario aux = null;
		
		for(Usuario user : Clinica.getInstance().getMisUsuarios()) {
			if(user.getNombre().equalsIgnoreCase(name)) {
				aux = user;
			}
		}
		
		return aux;
		
	}

	public void confirmRegistration(Usuario nuevoUsuario) throws UserRegistrationException {
		
		for(Usuario user : Clinica.getInstance().getMisUsuarios()) {
			
			if(nuevoUsuario.getId().equalsIgnoreCase(user.getId())) {
				throw new UserRegistrationException("Este nombre de usuario ya existe");
			}
			
			
			
		}
		
	}

}