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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

import logica.Administrador;
import logica.Clinica;
import logica.Medico;
import logica.Secretario;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Principal extends JFrame {

	private JPanel contentPane;

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
		setTitle("Administraci\u00F3n de Cl\u00EDnica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 516);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCrear = new JMenu("Crear");
		menuBar.add(mnCrear);
		
		JMenu mnNueva = new JMenu("Nueva");
		mnCrear.add(mnNueva);
		
		JMenuItem itemCC = new  JMenuItem("Cita/Consulta");
		mnNueva.add(itemCC);
		
		//if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			JMenuItem itemVacuna = new JMenuItem("Vacuna");
			mnNueva.add(itemVacuna);
		//}

		if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			itemCC.setText("Consulta");
		}else if(Clinica.getInstance().getLogedUser() instanceof Secretario){
			itemCC.setText("Cita");
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
		
		JMenuItem itemLCitas = new JMenuItem("Lista de citas");
		mnRLista.add(itemLCitas);
		
		JMenuItem itemLConsultas = new JMenuItem("Lista de consultas");
		mnRLista.add(itemLConsultas);
		
		JMenuItem itemLPacientes = new JMenuItem("Lista de pacientes");
		mnRLista.add(itemLPacientes);

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
				
				regUser window = new regUser();
				window.setVisible(true);
				
			}
		});
		itemRUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		mnRegistrar.add(itemRUsuario);
		
		JMenuItem itemREnfermedad = new JMenuItem("Registrar enfermedad");
		itemREnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegEnfermedad regEnf = new RegEnfermedad();
				regEnf.setVisible(true);
				
			}
		});
		mnRegistrar.add(itemREnfermedad);
		
		JMenuItem itemRVacuna = new JMenuItem("Registrar vacuna");
		itemRVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna windowRegVacuna = new RegVacuna();
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
		
		JMenuItem itemReporte = new JMenuItem("Solicitar reporte");
		mnAdministrar.add(itemReporte);
		
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
		lblInfo.setBounds(10, 431, 700, 14);
		panel.add(lblInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(440, 30, 270, 400);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 250, 378);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnLogout = new JButton("Cerrar sesi\u00F3n");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(panel, "¿Está seguro de cerrar su sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					lblInfo.setText("Cerrando sesión...");
					dispose();
					Login newLogin = new Login();
					newLogin.setVisible(true);
					Clinica.getInstance().Logout();
				}else {
					
				}
				
			}
		});
		btnLogout.setBounds(70, 340, 125, 25);
		panel_2.add(btnLogout);
		
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
}
