package arrancar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Flow extends Thread {
	
	Socket nsfd = null;
	DataInputStream in;
	DataOutputStream out;
	int opcion;
	
	public Flow(Socket sfd) {
		
		nsfd = sfd;
		try {
			in = new DataInputStream(nsfd.getInputStream());
			out = new DataOutputStream(nsfd.getOutputStream());
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al tratar de conectar con el cliente");
		}
	}
	
	public void run() {
		
		System.out.println("Ejecutando servicios para: " + nsfd.getInetAddress());
		
		while(true) {
			
			try {
				opcion = in.read();
				
				switch (opcion) {
				case 0:
					
					break;
					
				case 1:
					guardarRespaldo();
					break;
					
				case 2:
					cargarRespaldo();
					break;

				}
			} catch (IOException e) {
				System.out.println("Se ha desconectado: " + nsfd.getInetAddress());
				break;
			}
			

		}

		
	}
	
	private void guardarRespaldo() {
		FileOutputStream w;
		int unByte;
		
		try {
			w = new FileOutputStream("C:\\Users\\The Mask Power\\git\\proyecto_clinica\\Proyecto Clinica\\respaldo\\respaldo.dat");
			int available = in.readInt();
			for(int i = 0; i < available; i++) {
				unByte = in.read();
				w.write(unByte);
			}

			System.out.println("Se ha realizado el respaldo");
			w.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo");
		} catch (IOException e) {
			System.out.println("Desconexión forzada del servidor");
		}
	}
	
	private void cargarRespaldo() {
		FileInputStream r;
		int unByte;
		
		try {
			r = new FileInputStream("C:\\Users\\The Mask Power\\git\\proyecto_clinica\\Proyecto Clinica\\respaldo\\respaldo.dat");
			out.write(0);
			if(in.read() == 0) {
				int avaiable = r.available();
				out.writeInt(avaiable);
				while((unByte = r.read()) != -1) {
					out.write(unByte);
				}
				r.close();
				System.out.println("Se ha enviado el respaldo");
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo");
			try {
				out.write(1);
			} catch (IOException e1) {
				System.out.println("Desconexión forzada del servidor");
			}
		} catch (IOException e) {
			System.out.println("Desconexión forzada del servidor");
		}
	}
}
