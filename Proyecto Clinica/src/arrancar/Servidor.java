package arrancar;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import exceptions.CustomOutputStream;
import logica.Clinica;
import logica.Usuario;

public class Servidor extends JFrame implements Runnable {
	
	public static ArrayList<Usuario> onlineUsers = new ArrayList<Usuario>();
	static TextArea entrada;
	
	public Servidor() {
		
		setTitle("Servidor");
	    setSize(501,273);
		
	    entrada = new TextArea();
	    entrada.setEditable(false);
	    PrintStream printStream = new PrintStream(new CustomOutputStream(entrada));
	    PrintStream standardOut = System.out;
	    PrintStream standardErr = System.err;
		System.setOut(printStream);
		System.setErr(printStream);
	    
	    getContentPane().add("Center", entrada);
	    setVisible(true);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		Servidor server = new Servidor();
		
		ServerSocket sfd = null;
		
		try {
			sfd = new ServerSocket(6000);
			System.out.println("Se ha iniciado el servidor");
			System.out.println("PUERTO: " + sfd.getLocalPort());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			
		}catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "Comunicación rechazada "+ioe, "Error de conexión", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		while(true) {
			
			try {
				Socket nsfd = sfd.accept();
		        System.out.println("Se ha conectado: "+nsfd.getInetAddress());
		        Flow flow = new Flow(nsfd);
		        Thread t = new Thread(flow);
		        t.start();
			} catch (IOException e) {
				//JOptionPane.showMessageDialog(null, "Se ha producido un error de conexión", "Error de conexión", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error de conexión");
			}
			
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
