package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

import logica.Clinica;

import javax.swing.border.BevelBorder;

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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCrear = new JMenu("Crear");
		menuBar.add(mnCrear);
		
		JMenu mnNueva = new JMenu("Nueva");
		mnCrear.add(mnNueva);
		
		JMenuItem itemCC = new JMenuItem("Cita/Consulta ");
		mnNueva.add(itemCC);
		
		JMenu mnRevisar = new JMenu("Revisar");
		menuBar.add(mnRevisar);
		
		JMenuItem itemRPerfil = new JMenuItem("Perfil");
		mnRevisar.add(itemRPerfil);
		
		JMenu mnRLista = new JMenu("Lista");
		mnRevisar.add(mnRLista);
		
		JMenuItem itemLCitas = new JMenuItem("Lista de citas");
		mnRLista.add(itemLCitas);
		
		JMenuItem itemLConsultas = new JMenuItem("Lista de consultas");
		mnRLista.add(itemLConsultas);
		
		JMenuItem itemLPacientes = new JMenuItem("Lista de pacientes");
		mnRLista.add(itemLPacientes);
		
		JMenu mnAdministrar = new JMenu("Administrar");
		mnAdministrar.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnAdministrar);
		
		JMenu mnRegistrar = new JMenu("Registrar");
		mnAdministrar.add(mnRegistrar);
		
		JMenuItem itemRUsuario = new JMenuItem("Registrar usuario");
		itemRUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		mnRegistrar.add(itemRUsuario);
		
		JMenuItem itemREnfermedad = new JMenuItem("Registrar enfermedad");
		mnRegistrar.add(itemREnfermedad);
		
		JMenuItem itemRVacuna = new JMenuItem("Registrar vacuna");
		mnRegistrar.add(itemRVacuna);
		
		JMenu mnALista = new JMenu("Lista");
		mnAdministrar.add(mnALista);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Lista de usuarios");
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
		mnALista.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Lista de enfermedades");
		mnALista.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Lista de vacunas");
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
		panel.setBorder(new TitledBorder(null, "Bienvenido, " + Clinica.getInstance().getLogedUser(), TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(440, 30, 270, 400);
		panel.add(panel_1);
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
