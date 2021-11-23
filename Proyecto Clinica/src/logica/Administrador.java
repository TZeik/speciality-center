package logica;

public class Administrador extends Usuario {
	
	private String puesto;
	
	public Administrador(String codigo) {
		super(codigo);
		
		this.puesto = "";
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

}
