package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Administrador;
import logica.Cita;
import logica.Clinica;
import logica.Consulta;
import logica.Enfermedad;
import logica.Medico;
import logica.Paciente;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class CrearConsulta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearConsulta frame = new CrearConsulta();
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
	public CrearConsulta() {
		setResizable(false);
		setTitle("Crear consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		CitaList buscarCita = new CitaList(1);
		buscarCita.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cita", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 434, 124);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JTextField txtSelectCita = new JTextField();
		txtSelectCita.setEditable(false);
		txtSelectCita.setBounds(10, 50, 200, 25);
		panel_1.add(txtSelectCita);
		
		JTextArea txaFinalidad = new JTextArea();
		txaFinalidad.setEditable(false);
		txaFinalidad.setLineWrap(true);
		txaFinalidad.setBounds(220, 50, 204, 61);
		panel_1.add(txaFinalidad);
		
		txtSelectCita.setText("");
		txaFinalidad.setText("");
		
		JLabel lblNewLabel = new JLabel("Seleccionar Cita:");
		lblNewLabel.setBounds(10, 30, 241, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Finalidad:");
		lblNewLabel_1.setBounds(220, 30, 216, 14);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Buscar cita");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buscarCita.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 86, 200, 25);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 146, 434, 292);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea txaSintomas = new JTextArea();
		txaSintomas.setLineWrap(true);
		txaSintomas.setBounds(10, 50, 414, 70);
		panel_2.add(txaSintomas);
		
		JLabel lblSintomas = new JLabel("Sintomas: ");
		lblSintomas.setBounds(10, 30, 250, 14);
		panel_2.add(lblSintomas);
		
		DefaultComboBoxModel<String> enfermedadModel = new DefaultComboBoxModel<String>();
		enfermedadModel.addElement("<< Seleccione >>");
		for(Enfermedad enf : Clinica.getInstance().getMisEnfermedades()) {
			enfermedadModel.addElement(enf.getNombre());
		}
		
		JComboBox<String> cbxEnfermedad = new JComboBox<String>();
		cbxEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxEnfermedad.getSelectedIndex() != 0) {
					enfermedadModel.removeElementAt(0);
				}
			}
		});
		cbxEnfermedad.setModel(enfermedadModel);
		cbxEnfermedad.setBounds(10, 150, 414, 25);
		panel_2.add(cbxEnfermedad);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad: ");
		lblEnfermedad.setBounds(10, 130, 250, 14);
		panel_2.add(lblEnfermedad);
		
		JTextArea txaDiagnostico = new JTextArea();
		txaDiagnostico.setLineWrap(true);
		txaDiagnostico.setBounds(10, 204, 414, 70);
		panel_2.add(txaDiagnostico);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");
		lblDiagnostico.setBounds(10, 184, 250, 14);
		panel_2.add(lblDiagnostico);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				buscarCita.dispose();
			}
		});
		btnSalir.setBounds(354, 449, 90, 25);
		panel.add(btnSalir);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean cancel = false;
				Consulta newConsulta = new Consulta(Clinica.getInstance().GenerateConsultaCode());
				Paciente paciente = null;
				newConsulta.setFecha(Calendar.getInstance());
				newConsulta.setDiagnostico(txaDiagnostico.getText());
				newConsulta.setSintomas(txaSintomas.getText());
				newConsulta.setEnfermedad(Clinica.getInstance().SearchEnfermedadByName(cbxEnfermedad.getSelectedItem().toString()));
				if(Clinica.getInstance().getLogedUser() instanceof Administrador) {
					Medico adminMedico = new Medico(Clinica.getInstance().getLogedUser().getCodigo());
					Administrador temp = (Administrador)Clinica.getInstance().getLogedUser();
					adminMedico.setId(temp.getId());
					adminMedico.setNombre(temp.getNombre());
					adminMedico.setPassword(temp.getPassword());
					adminMedico.setTelefono(temp.getTelefono());
					adminMedico.setEspecialidad(temp.getPuesto());
					newConsulta.setMiMedico(adminMedico);
				}else {
					newConsulta.setMiMedico((Medico) Clinica.getInstance().getLogedUser());
				}

				paciente = Clinica.getInstance().buscarPacienteByCed(Clinica.getInstance().getSelectedCita().getCedula());
				if(paciente == null) {
					if(JOptionPane.showConfirmDialog(panel, "Se creará un nuevo paciente de nombre: " + Clinica.getInstance().getSelectedCita().getNombre(), "Nuevo paciente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Clinica.getInstance().nuevoPaciente(Clinica.getInstance().getSelectedCita(), newConsulta, null);
					}else {
						JOptionPane.showMessageDialog(panel, "No se ha podido crear la consulta", "Crear consulta", JOptionPane.ERROR_MESSAGE);
						cancel = true;
						
					}
				}
				if(paciente != null) {
					Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(paciente.getCodigo())).getHistorial().getMisConsultas().add(newConsulta);
				}
				
				if(cancel == false) {
					JOptionPane.showMessageDialog(panel, "Se ha creado la consulta correctamente", "Crear consulta", JOptionPane.INFORMATION_MESSAGE);
					Clinica.getInstance().guardarClinica();
				}
				CrearConsulta refresh = new CrearConsulta();
				refresh.setVisible(true);
				Clinica.getInstance().setSelectedCita(null);
				dispose();

				
			}
		});
		btnCrear.setBounds(254, 449, 90, 25);
		panel.add(btnCrear);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
				txtSelectCita.setText(Clinica.getInstance().getSelectedCita().getNombre());
				txaFinalidad.setText(Clinica.getInstance().getSelectedCita().getFinalidad());
				} catch(NullPointerException e1) {
					if(Clinica.getInstance().SoloCitasMedico().size() == 0) {
						JOptionPane.showMessageDialog(panel, "No hay citas para realizar consultas", "Información", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}else {
						Clinica.getInstance().setSelectedCita(Clinica.getInstance().SoloCitasMedico().get(0));
						txtSelectCita.setText("");
						txaFinalidad.setText("");
					}
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				Clinica.getInstance().setSelectedCita(null);
			}
			@Override
			public void windowOpened(WindowEvent e) {
				Clinica.getInstance().setSelectedCita(null);
			}
		});
	}
}
