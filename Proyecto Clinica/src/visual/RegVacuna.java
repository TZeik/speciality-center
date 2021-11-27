package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Clinica;
import logica.Vacuna;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegVacuna extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private Vacuna update;
	private JComboBox cbxFecha;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegVacuna frame = new RegVacuna();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public RegVacuna(Vacuna aux) {
		setResizable(false);
		update = aux;
		if (update== null) {
		setTitle("Registrar vacuna");	
		}else {
			setTitle("Editar vacuna");
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(10, 108, 302, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(10, 83, 302, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A\u00F1o de creaci\u00F3n: ");
		lblNewLabel_1.setBounds(10, 152, 302, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 302, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		int index = -1;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		DefaultComboBoxModel fechaModel = new DefaultComboBoxModel();
		for(int i = 1950; i <= currentYear; i++) {
			fechaModel.addElement(i);
			index++;
		}
		cbxFecha = new JComboBox();
		cbxFecha.setModel(fechaModel);
		cbxFecha.setSelectedIndex(index);
		cbxFecha.setBounds(10, 177, 302, 25);
		panel.add(cbxFecha);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(222, 237, 90, 25);
		panel.add(btnCancelar);
		
		JButton btnRegistrar = new JButton("");
		if (update== null) {
			btnRegistrar.setText("Registrar");	
			}else {
				btnRegistrar.setText("Editar");
			
			}
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (update == null) {
				Vacuna newVacuna = new Vacuna(Clinica.getInstance().GenerateVacCode());
				newVacuna.setNombre(txtName.getText());
				newVacuna.setAnnoCreacion((int)cbxFecha.getSelectedItem());
				
				Clinica.getInstance().getMisVacunas().add(newVacuna);
				Clinica.getInstance().setVaccineCodeGenerator(Clinica.getInstance().getVaccineCodeGenerator() + 1);
				Clinica.getInstance().guardarClinica();
				
				JOptionPane.showMessageDialog(panel, "La vacuna se ha registrado con éxito", "Registro de vacuna", JOptionPane.INFORMATION_MESSAGE);
				txtName.setText("");
				}else {
					update.setNombre(txtName.getText());
					update.setAnnoCreacion((int)cbxFecha.getSelectedItem());
					Clinica.getInstance().getMisVacunas().get(Clinica.getInstance().buscarVacunaIndex(update)).setNombre(update.getNombre());
					Clinica.getInstance().getMisVacunas().get(Clinica.getInstance().buscarVacunaIndex(update)).setAnnoCreacion(update.getAnnoCreacion());
					Clinica.getInstance().guardarClinica();
					JOptionPane.showMessageDialog(panel, "La vacuna se ha editado con éxito", "Editar vacuna", JOptionPane.DEFAULT_OPTION);
					dispose();
				}
			}
		});
		btnRegistrar.setBounds(122, 237, 90, 25);
		panel.add(btnRegistrar);
		loadVacuna();
	}
	
	private void loadVacuna() {
		if (update != null) {
			txtName.setText(update.getNombre());
			cbxFecha.setSelectedItem(update.getAnnoCreacion());
		}
	}
}
