package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Clinica;

import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CitaSelect extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitaSelect frame = new CitaSelect();
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
	public CitaSelect() {
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Tipo de cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 461, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNuevo = new JButton("Paciente nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCita nuevaCita = new CrearCita(0);
				nuevaCita.setVisible(true);
				dispose();
			}
		});
		btnNuevo.setBounds(48, 15, 150, 70);
		panel.add(btnNuevo);
		
		JButton btnExistente = new JButton("Paciente existente");
		btnExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteList seleccionar = new PacienteList(1);
				seleccionar.setVisible(true);
				dispose();
			}
		});
		btnExistente.setBounds(232, 15, 150, 70);
		panel.add(btnExistente);
	}

}
