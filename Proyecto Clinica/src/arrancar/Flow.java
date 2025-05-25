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

import logica.Usuario;

public class Flow extends Thread {
	
	Socket nsfd = null;
	DataInputStream in;
	DataOutputStream out;
	int opcion;
	Usuario logedUser;
	
	public Flow(Socket sfd) {
		
		nsfd = sfd;
		try {
			in = new DataInputStream(nsfd.getInputStream());
			out = new DataOutputStream(nsfd.getOutputStream());
			logedUser = new Usuario(in.readUTF());
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al tratar de conectar con el cliente");
		}
	}
	
	public void run() {
		
		try {
			logedUser.setNombre(in.readUTF());
			logedUser.setEstado(in.readUTF());
			System.out.println("Ejecutando servicios para: " + logedUser.getNombre());
			Servidor.onlineUsers.add(logedUser);
			
		} catch (IOException e1) {
			System.out.println("No se ha podido cargar el usuario");
		}
		
		while(true) {
			
			try {
				opcion = in.read();
				
				switch (opcion) {
				case 0:
					nsfd.close();
					break;
				case 1:
					guardarRespaldo();
					break;
					
				case 2:
					cargarRespaldo();
					break;
				case 3:
					cargarTablaUsuarios();
					break;
				case 4:
					enviarChat();
					break;
				}
			} catch (IOException e) {
				System.out.println("Se ha desconectado: " + logedUser.getNombre() + " | IP: " + nsfd.getInetAddress());
				Servidor.onlineUsers.remove(logedUser);
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
	
	private void cargarTablaUsuarios() {
	
		int size = Servidor.onlineUsers.size();
		
		try {
			out.writeInt(size);
			
			for(Usuario user : Servidor.onlineUsers) {
				out.writeUTF(user.getCodigo());
			}
		} catch (IOException e) {
			System.out.println("Desconexión forzada del servidor");

		}
		

		
	}
	
	private void enviarChat() {
		
		try {
			String userName = in.readUTF();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
