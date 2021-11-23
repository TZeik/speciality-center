package logica;

public class Medico extends Usuario {
	
	private String especialidad;
	
	public Medico(String codigo) {
		super(codigo);
		
		this.especialidad = "";
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
