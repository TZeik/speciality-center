package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import arrancar.Cliente;
import logica.Administrador;
import logica.Clinica;
import logica.Medico;
import logica.Secretario;
import logica.Usuario;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable mailTable;
	private JTable userTable;
	private Dimension dim;
	private JTextField chatField;
	private static Object[] row;
	public static DefaultTableModel mailModel;
	public static DefaultTableModel userModel;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	private Usuario selectedUser = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {

		dim = getToolkit().getScreenSize();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Clinica.getInstance().Logout();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Administraci\u00F3n de Cl\u00EDnica");
		setResizable(false);
		//setBounds(100, 100, 745, 516);
		setLocationRelativeTo(null);
		setBounds(-12, 0, (int)dim.getWidth()+24, (int)dim.getHeight()-34);
		
		try {
			sfd = new Socket("localhost", 6000);
			SalidaSocket = new DataOutputStream(sfd.getOutputStream());
			EntradaSocket = new DataInputStream(sfd.getInputStream());
		} catch (UnknownHostException e2) {
			System.out.println("Host no existe o inválido");
		} catch (IOException e2) {
			System.out.println("Se ha perdido la conexión con el servidor");
		}
		
		try {
			SalidaSocket.writeUTF(Clinica.getInstance().getLogedUser().getCodigo());
			SalidaSocket.writeUTF(Clinica.getInstance().getLogedUser().getNombre());
			SalidaSocket.writeUTF(Clinica.getInstance().getLogedUser().getEstado());
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			Clinica.getInstance().Logout();
			System.exit(1);
		}
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCrear = new JMenu("Crear");
		menuBar.add(mnCrear);
		
		JMenu mnNueva = new JMenu("Nueva");
		mnCrear.add(mnNueva);
	
		if(Clinica.getInstance().getLogedUser() instanceof Secretario || Clinica.getInstance().getLogedUser() instanceof  Administrador) {
			JMenuItem itemCita = new  JMenuItem("Cita");
			itemCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	
	
						CitaSelect ventanaCrearCita = new CitaSelect();
						ventanaCrearCita.setVisible(true);
					
				}
			});
			mnNueva.add(itemCita);
		}
		
		if(Clinica.getInstance().getLogedUser() instanceof Medico || Clinica.getInstance().getLogedUser() instanceof Administrador) {
			JMenuItem itemConsulta = new  JMenuItem("Consulta");
			itemConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	
						CrearConsulta ventanaCrearConsulta = new CrearConsulta();
						ventanaCrearConsulta.setVisible(true);
						
					
				}
			});
			mnNueva.add(itemConsulta);
		}	
		if(Clinica.getInstance().getLogedUser() instanceof Medico || Clinica.getInstance().getLogedUser() instanceof Administrador) {
			JMenuItem itemVacuna = new JMenuItem("Vacunación");
			itemVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CrearVacuna crearVacuna = new CrearVacuna();
					crearVacuna.setVisible(true);
				}
			});
			mnNueva.add(itemVacuna);
		}
		
		JMenu mnRevisar = new JMenu("Revisar");
		menuBar.add(mnRevisar);
		
		JMenuItem itemRPerfil = new JMenuItem("Perfil");
		itemRPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil windowPerfil = new Perfil();
				windowPerfil.setVisible(true);
			}
		});
		mnRevisar.add(itemRPerfil);
		
		JMenu mnRLista = new JMenu("Lista");
		mnRevisar.add(mnRLista);
		
		JMenuItem itemLPacientes = new JMenuItem("Lista de pacientes");
		itemLPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteList pacienteList = new PacienteList(0);
				pacienteList.setVisible(true);
			}
		});
		mnRLista.add(itemLPacientes);
		
		JMenuItem itemLCitas = new JMenuItem("Lista de citas");
		itemLCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CitaList citaList = new CitaList(0);
				citaList.setVisible(true);
			}
		});
		mnRLista.add(itemLCitas);
		
		JMenuItem itemLConsultas = new JMenuItem("Lista de consultas");
		itemLConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaList consultaList = new ConsultaList();
				consultaList.setVisible(true);
			}
		});
		mnRLista.add(itemLConsultas);

		if(Clinica.getInstance().getLogedUser() instanceof Secretario) {
			itemLConsultas.setEnabled(false);
			itemLPacientes.setEnabled(false);
		}
		
		JMenu mnAdministrar = new JMenu("Administrar");
		mnAdministrar.setEnabled(false);
		mnAdministrar.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnAdministrar);
		
		if(Clinica.getInstance().getLogedUser() instanceof Administrador) {
			mnAdministrar.setEnabled(true);
		}
		
		JMenu mnRegistrar = new JMenu("Registrar");
		mnAdministrar.add(mnRegistrar);
		
		JMenuItem itemRUsuario = new JMenuItem("Registrar usuario");
		itemRUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				regUser window = new regUser(null);
				window.setVisible(true);
				
			}
		});
		itemRUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		mnRegistrar.add(itemRUsuario);
		
		JMenuItem itemREnfermedad = new JMenuItem("Registrar enfermedad");
		itemREnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegEnfermedad regEnf = new RegEnfermedad(null);
				regEnf.setVisible(true);
				
			}
		});
		mnRegistrar.add(itemREnfermedad);
		
		JMenuItem itemRVacuna = new JMenuItem("Registrar vacuna");
		itemRVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna windowRegVacuna = new RegVacuna(null);
				windowRegVacuna.setVisible(true);
			}
		});
		mnRegistrar.add(itemRVacuna);
		
		JMenu mnALista = new JMenu("Lista");
		mnAdministrar.add(mnALista);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Lista de usuarios");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserList userlist = new UserList();
				userlist.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
		mnALista.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Lista de enfermedades");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnfermedadList enfList = new EnfermedadList();
				enfList.setVisible(true);
			}
		});
		mnALista.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Lista de vacunas");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacunaList vacList = new VacunaList();
				vacList.setVisible(true);
			}
		});
		mnALista.add(mntmNewMenuItem_10);
		
		JMenuItem itemRespaldo = new JMenuItem("Hacer respaldo");
		itemRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SalidaSocket.write(1);
					
				    int unByte;
				    int fin = -1;
				    FileInputStream r = new FileInputStream("clinica.dat");
				    int available = r.available();
				    SalidaSocket.writeInt(available);
				    while((unByte = r.read()) != -1) {
				    	SalidaSocket.write(unByte);
				    }
				    r.close();
				    
				} catch (UnknownHostException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Se ha realizado el respaldo", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAdministrar.add(itemRespaldo);
		
		JMenuItem itemCRespaldo = new JMenuItem("Cargar respaldo");
		itemCRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					SalidaSocket.write(2);

					int unByte;
					FileOutputStream w = new FileOutputStream("clinica.dat");
					if(EntradaSocket.read() == 0) {
						if(JOptionPane.showConfirmDialog(null, "Se ha encontrado un respaldo, ¿Desea continuar? (Se reiniciará la aplicación)", "Cargar respaldo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							SalidaSocket.write(0);
							int avaiable = EntradaSocket.readInt();
							for(int i = 0; i < avaiable; i++) {
								unByte = EntradaSocket.read();
								w.write(unByte);
							}
							w.close();
							Clinica.getInstance().setLogedUser(null);
							Clinica.getInstance().setSelectedCita(null);
							Clinica.getInstance().setSelectedPaciente(null);
							System.exit(1);
						}else {
							SalidaSocket.write(1);
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "No se ha encontrado un respaldo", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e1) {
					System.out.println("Ha ocurrido un error con el servidor");
				}
				


			}
		});
		mnAdministrar.add(itemCRespaldo);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem itemManual = new JMenuItem("Manual");
		mnAyuda.add(itemManual);
		
		JMenuItem itemAbout = new JMenuItem("Acerca de");
		mnAyuda.add(itemAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Bienvenido, " + Clinica.getInstance().getLogedUser().getNombre(), TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(10, 967, 1904, 14);
		panel.add(lblInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(1301, 30, 613, 926);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 198, 593, 676);
		panel_1.add(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Mensajer\u00EDa instant\u00E1nea", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(695, 30, 595, 926);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblInfoChat = new JLabel("Chat de: ");
		lblInfoChat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInfoChat.setBounds(10, 30, 65, 14);
		panel_2.add(lblInfoChat);
		
		JLabel DynamicName = new JLabel("<dynamic>");
		DynamicName.setFont(new Font("Tahoma", Font.BOLD, 14));
		DynamicName.setBounds(78, 30, 508, 14);
		panel_2.add(DynamicName);
		
		lblInfoChat.setVisible(false);
		DynamicName.setVisible(false);
		
		String[] headers = {"Nombre","Estado"};
		userModel = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		userModel.setColumnIdentifiers(headers);
		

		userTable = new JTable();
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = userTable.getSelectedRow();
				if(select != -1) {
					selectedUser = Clinica.getInstance().searchUsuarioByName((String)userTable.getValueAt(select, 0));
					lblInfoChat.setVisible(true);
					DynamicName.setVisible(true);
					DynamicName.setText(selectedUser.getNombre());
				}
			}
		});
		userTable.getTableHeader().setReorderingAllowed(false);
		userTable.setModel(userModel);
		userTable.setRowHeight(20);
		userTable.getTableHeader().setReorderingAllowed(false);
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(userTable);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		imagePanel.setBounds(10, 11, 180, 180);
		panel_1.add(imagePanel);
		imagePanel.setLayout(null);
		
		JLabel profilePhoto = new JLabel("");
		
		profilePhoto.setIcon(new ImageIcon(Principal.class.getResource("/images/default.png")));
		profilePhoto.setBounds(0, 0, 180, 180);
		imagePanel.add(profilePhoto);
		
		JLabel lblNombre = new JLabel(Clinica.getInstance().getLogedUser().getNombre());
		lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
		lblNombre.setBounds(200, 11, 403, 14);
		panel_1.add(lblNombre);
		
		JComboBox cbxEstado = new JComboBox();
		cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"En l\u00EDnea", "Ausente", "Invisible"}));
		cbxEstado.setBounds(200, 50, 403, 20);
		panel_1.add(cbxEstado);
		
		JLabel lblUserType = new JLabel("<dynamic>");
		lblUserType.setBounds(200, 92, 403, 14);
		panel_1.add(lblUserType);
		
		JButton btnLogout = new JButton("Cerrar sesi\u00F3n");
		btnLogout.setBounds(200, 162, 403, 25);
		panel_1.add(btnLogout);
		
		JButton btnNewButton_1 = new JButton("Abrir chat");
		btnNewButton_1.setBounds(10, 890, 166, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Redactar correo");
		btnNewButton_2.setBounds(186, 890, 241, 25);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ver informaci\u00F3n");
		btnNewButton_3.setBounds(437, 890, 166, 25);
		panel_1.add(btnNewButton_3);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(panel, "¿Está seguro de cerrar su sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					lblInfo.setText("Cerrando sesión...");
					dispose();
					try {
						SalidaSocket.write(0);
						sfd.close();
					} catch (IOException e1) {
					}
					Clinica.getInstance().Logout();
					Login newLogin = new Login();
					newLogin.setVisible(true);

				}else {
					
				}
				
			}
		});
		int opcion = Clinica.getInstance().OpcionUserClinica();
		switch (opcion) {
		case 0:
			lblUserType.setText("Administrador");
			break;
		case 1:
			lblUserType.setText("Médico");
			break;
		case 2:
			lblUserType.setText("Secretario");
			break;

		default:
			break;
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 30, 373, 114);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnCrear1 = new JButton("<dynamic>");
		btnCrear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Clinica.getInstance().getLogedUser() instanceof Medico || Clinica.getInstance().getLogedUser() instanceof Administrador) {
					CrearConsulta nuevaConsulta = new CrearConsulta();
					nuevaConsulta.setVisible(true);
				}
				
				if(Clinica.getInstance().getLogedUser() instanceof Secretario) {
					CrearCita nuevaCita =  new CrearCita(0);
					nuevaCita.setVisible(true);
				}
				
			}
		});
		btnCrear1.setBounds(10, 25, 170, 75);
		panel_3.add(btnCrear1);
		
		JButton btnCrear2 = new JButton("<dynamic>");
		btnCrear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(Clinica.getInstance().getLogedUser() instanceof Medico || Clinica.getInstance().getLogedUser() instanceof Administrador) {
					CrearVacuna nuevaVacuna = new CrearVacuna();
					nuevaVacuna.setVisible(true);
				}
				
				if(Clinica.getInstance().getLogedUser() instanceof Secretario) {
					PacienteList citaSelect = new PacienteList(1);
					citaSelect.setVisible(true);
				}
			}
		});
		btnCrear2.setBounds(190, 25, 170, 75);
		panel_3.add(btnCrear2);
		
		if(Clinica.getInstance().getLogedUser() instanceof Medico || Clinica.getInstance().getLogedUser() instanceof Administrador) {
			btnCrear1.setText("Consulta");
			btnCrear2.setText("Vacunación");
		}
		
		if(Clinica.getInstance().getLogedUser() instanceof Secretario) {
			btnCrear1.setText("<html><center>Cita<p>Nuevo paciente<html>");
			btnCrear2.setText("<html><center>Cita<p>Paciente existente<html>");
		}

		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Bandeja de entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 161, 675, 795);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 655, 761);
		panel_4.add(scrollPane);
		
		mailModel = new DefaultTableModel();
		String[] mailHeaders = {"Emisor","Asunto","Fecha","Hora"};
		mailModel.setColumnIdentifiers(mailHeaders);
		mailTable = new JTable();
		mailTable.setModel(mailModel);
		mailTable.getTableHeader().setReorderingAllowed(false);
		mailTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(mailTable);
		
		chatField = new JTextField();
		chatField.setBounds(10, 890, 477, 25);
		panel_2.add(chatField);
		chatField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SalidaSocket.write(4);
					SalidaSocket.writeUTF(chatField.getText());
					SalidaSocket.writeUTF(selectedUser.getNombre());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(497, 890, 89, 25);
		panel_2.add(btnNewButton);
		
		JTextArea chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setBounds(10, 57, 576, 822);
		panel_2.add(chatArea);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblNombre.setText(Clinica.getInstance().getLogedUser().getNombre());
				panel.setBorder(new TitledBorder(null, "Bienvenido, " + Clinica.getInstance().getLogedUser().getNombre(), TitledBorder.RIGHT, TitledBorder.TOP, null, null));
				loadUserTable();
			}
		});

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
	}
	
	public static void loadUserTable() {
		
		ArrayList<Usuario> onlineUsers = new ArrayList<Usuario>();
		
		try {
			SalidaSocket.write(3);
			
			int size = EntradaSocket.readInt();
			
			for(int i = 0; i < size; i++) {
				onlineUsers.add(Clinica.getInstance().SearchUsuarioCode(EntradaSocket.readUTF()));
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Se ha perdido la conexión con el servidor", "Conexión perdida", JOptionPane.ERROR_MESSAGE);
			Clinica.getInstance().Logout();
			System.exit(1);
		}
		
		userModel.setRowCount(0);
		row = new Object[userModel.getColumnCount()];
		
		for(Usuario user : onlineUsers) {
			
			row[0] = user.getNombre();
			row[1] = user.getEstado();
			
			userModel.addRow(row);
		}
		
	}
}
