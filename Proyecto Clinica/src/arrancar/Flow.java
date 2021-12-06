package arrancar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import logica.Clinica;
import logica.Usuario;

public class Flow extends Thread {

	  Socket nsfd;
	  DataInputStream FlujoLectura;
	  DataOutputStream FlujoEscritura;
	  
	public Flow(Socket sfd) {
		
	    nsfd = sfd;
	    try {
	      FlujoLectura = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
	      FlujoEscritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
	    } catch(IOException ioe) {
	     System.out.println("IOException(Flujo): "+ioe);
	   }
		
	}
	
	@Override
	public void run() {
		Servidor.usuarios.add((Object) this);
		while(true) {
			try {
				int isSaved = FlujoLectura.readInt();
				if(isSaved == 1) {
					sincronizarClinica();
				}
			}catch(IOException ioe) {
				
				Servidor.usuarios.remove(this);
				System.out.println("El usuario " + nsfd.getInetAddress() + " se ha desconectado");
				break;
			}
		}
	}

	private void sincronizarClinica() {
		// TODO Auto-generated method stub
		
	}
	
}
