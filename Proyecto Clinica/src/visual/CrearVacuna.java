package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Cita;
import logica.Clinica;
import logica.Paciente;
import logica.Vacuna;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearVacuna extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearVacuna frame = new CrearVacuna();
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
	public CrearVacuna() {
		setTitle("Crear reporte de vacunaci\u00F3n");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vacunaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		DefaultComboBoxModel<String> vacunaModel = new  DefaultComboBoxModel<String>();
		vacunaModel.addElement("<< Seleccione >>");
		for(Vacuna vac : Clinica.getInstance().getMisVacunas())
			vacunaModel.addElement(vac.getNombre());
		JComboBox<String> cbxVacuna = new JComboBox<String>();
		cbxVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxVacuna.getSelectedIndex() != 0) {
					vacunaModel.removeElementAt(0);
				}
			}
		});
		cbxVacuna.setModel(vacunaModel);
		cbxVacuna.setBounds(10, 125, 414, 25);
		panel.add(cbxVacuna);
		
		
		DefaultComboBoxModel<String> citaModel = new  DefaultComboBoxModel<String>();
		citaModel.addElement("<< Seleccione >>");
		for(Cita cita : Clinica.getInstance().SoloCitasMedico())
			citaModel.addElement(cita.getNombre());
		JComboBox<String> cbxCita = new JComboBox<String>();
		cbxCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxCita.getSelectedIndex() != 0) {
					citaModel.removeElementAt(0);
				}
			}
		});
		cbxCita.setModel(citaModel);
		cbxCita.setBounds(10, 50, 414, 25);
		panel.add(cbxCita);
		
		JLabel lblCita = new JLabel("Seleccione la cita: ");
		lblCita.setBounds(10, 30, 414, 14);
		panel.add(lblCita);
		
		JLabel lblVacuna = new JLabel("Seleccione la vacuna: ");
		lblVacuna.setBounds(10, 105, 414, 14);
		panel.add(lblVacuna);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(334, 178, 90, 25);
		panel.add(btnCancelar);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cancel = false;
				Paciente paciente = null;
				Cita cita = Clinica.getInstance().SearchCitaByName(cbxCita.getSelectedItem().toString());
				Vacuna vacuna = Clinica.getInstance().SearchVacunaByName(cbxVacuna.getSelectedItem().toString());
				
				paciente = Clinica.getInstance().buscarPacienteByCed(cita.getCedula());
				if(paciente == null) {
					if(JOptionPane.showConfirmDialog(panel, "Se creará un nuevo paciente de nombre: " + cita.getNombre(), "Nuevo paciente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Clinica.getInstance().nuevoPaciente(cita, null, vacuna);
					}else {
						JOptionPane.showMessageDialog(panel, "No se ha podido crear la vacunación", "Crear vacunación", JOptionPane.ERROR_MESSAGE);
						cancel = true;
					}
					
				}
				if(paciente != null) {
					Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(paciente.getCodigo())).getHistorial().getMisVacunas().add(vacuna);
				}
				
				if(cancel == false) {
					JOptionPane.showMessageDialog(panel, "Se ha creado la vacunación correctamente", "Crear vacunación", JOptionPane.INFORMATION_MESSAGE);
					Clinica.getInstance().guardarClinica();
				}
				CrearVacuna refresh = new CrearVacuna();
				refresh.setVisible(true);
				dispose();
			}
		});
		btnCrear.setBounds(234, 178, 90, 25);
		panel.add(btnCrear);
	}
}
