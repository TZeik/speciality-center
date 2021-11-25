package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Cita;
import logica.Clinica;
import logica.Usuario;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.HierarchyEvent;

public class CrearCita extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEspecialidad;
	private JTextField txtFechaCita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCita frame = new CrearCita();
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
	public CrearCita() {
		setTitle("Crear cita");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		DefaultComboBoxModel diaModel = new DefaultComboBoxModel();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Nombre: ");
		lblName.setBounds(10, 30, 300, 14);
		panel.add(lblName);
		
		JLabel lblCedula = new JLabel("C\u00E9dula: ");
		lblCedula.setBounds(10, 100, 300, 14);
		panel.add(lblCedula);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setBounds(10, 170, 285, 14);
		panel.add(lblSexo);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono: ");
		lblTelefono.setBounds(10, 240, 300, 14);
		panel.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n: ");
		lblDireccion.setBounds(10, 310, 300, 14);
		panel.add(lblDireccion);
		
		JLabel lblEspecialidad = new JLabel("Especialidad: ");
		lblEspecialidad.setBounds(320, 30, 285, 14);
		panel.add(lblEspecialidad);
		
		JLabel lblNacimiento = new JLabel("Fecha de nacimiento: ");
		lblNacimiento.setBounds(320, 100, 285, 14);
		panel.add(lblNacimiento);
		
		JLabel lblFechaCita = new JLabel("Fecha de cita: ");
		lblFechaCita.setBounds(320, 170, 285, 14);
		panel.add(lblFechaCita);
		
		JLabel lblMedico = new JLabel("Medico: ");
		lblMedico.setBounds(320, 240, 285, 14);
		panel.add(lblMedico);
		
		JLabel lblFinalidad = new JLabel("Finalidad: ");
		lblFinalidad.setBounds(320, 310, 285, 14);
		panel.add(lblFinalidad);
		
		txtName = new JTextField();
		txtName.setBounds(10, 50, 285, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(10, 120, 285, 25);
		panel.add(txtCedula);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(10, 260, 285, 25);
		panel.add(txtDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 330, 285, 25);
		panel.add(txtTelefono);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(320, 50, 285, 25);
		panel.add(txtEspecialidad);
		
		JTextArea txaFinalidad = new JTextArea();
		txaFinalidad.setLineWrap(true);
		txaFinalidad.setBounds(320, 330, 285, 100);
		panel.add(txaFinalidad);
		
		JComboBox cbxDia = new JComboBox();
		
		JComboBox cbxMes = new JComboBox();
		cbxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbxMes.getSelectedIndex() == 0 || cbxMes.getSelectedIndex() == 2 || cbxMes.getSelectedIndex() == 4 || cbxMes.getSelectedIndex() == 6 || cbxMes.getSelectedIndex() == 7 || cbxMes.getSelectedIndex() == 9 || cbxMes.getSelectedIndex() == 11) {
					if(diaModel.getSize() == 30) {
						diaModel.addElement(31);
					}
					if(diaModel.getSize() == 28) {
						diaModel.addElement(29);
						diaModel.addElement(30);
						diaModel.addElement(31);
					}
				}
				
				if(cbxMes.getSelectedIndex() == 3 || cbxMes.getSelectedIndex() == 5 || cbxMes.getSelectedIndex() == 8 || cbxMes.getSelectedIndex() == 10) {
					if(diaModel.getSize() == 31) {
						if(cbxDia.getSelectedIndex()+1 < 31) {
							diaModel.removeElementAt(30);
						}else {
							cbxDia.setSelectedIndex(29);
							diaModel.removeElementAt(30);
						}
					}
					
					if(diaModel.getSize() == 28) {
						diaModel.addElement(30);
						diaModel.addElement(31);
					}
					

					
				}
				
				if(cbxMes.getSelectedIndex() == 1) {
					if(diaModel.getSize() == 31) {
						if(cbxDia.getSelectedIndex() + 1 < 30) {
							diaModel.removeElementAt(30);
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						} else {
							cbxDia.setSelectedIndex(27);
							diaModel.removeElementAt(30);
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						}
						
					}
					
					if(diaModel.getSize() == 30) {
						if(cbxDia.getSelectedIndex() + 1 < 30) {
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						} else {
							cbxDia.setSelectedIndex(27);
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						}
					}
					
				}
			}
		});

		cbxMes.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		cbxMes.setBounds(380, 120, 130, 25);
		panel.add(cbxMes);
		

		if(cbxMes.getSelectedIndex() == 0 || cbxMes.getSelectedIndex() == 2 || cbxMes.getSelectedIndex() == 4 || cbxMes.getSelectedIndex() == 6 || cbxMes.getSelectedIndex() == 7 || cbxMes.getSelectedIndex() == 9 || cbxMes.getSelectedIndex() == 11) {
			for(int i = 1; i <= 31; i++) {
				diaModel.addElement(i);
			}
		}
		
		if(cbxMes.getSelectedIndex() == 3 || cbxMes.getSelectedIndex() == 5 || cbxMes.getSelectedIndex() == 8 || cbxMes.getSelectedIndex() == 10) {
			for(int i = 1; i <= 30; i++) {
				diaModel.addElement(i);
			}
		}
		
		if(cbxMes.getSelectedIndex() == 1) {
			for(int i = 1; i <= 28; i++) {
				diaModel.addElement(i);
			}
		}
		

		cbxDia.setModel(diaModel);
		cbxDia.setBounds(320, 120, 50, 25);
		panel.add(cbxDia);
		
		DefaultComboBoxModel annoModel  = new  DefaultComboBoxModel();
		int index = -1;
		for(int i = Calendar.getInstance().get(Calendar.YEAR) - 100; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
			annoModel.addElement(i);
			index++;
		}
		JComboBox cbxAnno = new JComboBox();
		cbxAnno.setModel(annoModel);
		cbxAnno.setSelectedIndex(index);
		cbxAnno.setBounds(520, 120, 85, 25);
		panel.add(cbxAnno);
		
		txtFechaCita = new JTextField();
		txtFechaCita.setEditable(false);
		txtFechaCita.setBounds(320, 190, 285, 25);
		txtFechaCita.setText( + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR));
		panel.add(txtFechaCita);
		txtFechaCita.setColumns(10);
		
		
		DefaultComboBoxModel medicoModel = new DefaultComboBoxModel();
		for(Usuario user : Clinica.getInstance().misMedicos()) {
			medicoModel.addElement(user.getNombre());
		}
		JComboBox cbxMedico = new JComboBox();
		cbxMedico.setModel(medicoModel);
		cbxMedico.setBounds(320, 260, 285, 25);
		panel.add(cbxMedico);
		
		JComboBox cbxSexo = new JComboBox();
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		cbxSexo.setBounds(10, 190, 285, 25);
		panel.add(cbxSexo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(515, 461, 90, 25);
		panel.add(btnSalir);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cita newCita = new Cita(Clinica.getInstance().GenerateCitaCode());
				Calendar tempDate = Calendar.getInstance();
				tempDate.set((int)cbxAnno.getSelectedItem(), cbxMes.getSelectedIndex()+1, (int)cbxDia.getSelectedItem());
				newCita.setNombre(txtName.getText());
				newCita.setCedula(txtCedula.getText());
				newCita.setGenero(cbxSexo.getSelectedItem().toString());
				newCita.setTelefono(txtTelefono.getText());
				newCita.setDireccion(txtDireccion.getText());
				newCita.setFechaNacimiento(tempDate);
				newCita.setFechaCita(Calendar.getInstance());
				newCita.setEspecialidad(txtEspecialidad.getText());
				newCita.setFinalidad(txaFinalidad.getText());
				newCita.setMedico(Clinica.getInstance().SearchMedicoByName(cbxMedico.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(panel, "Se ha creado una cita correctamente", "Cita completada", JOptionPane.INFORMATION_MESSAGE);
				
				Clinica.getInstance().getMisCitas().add(newCita);
				Clinica.getInstance().guardarClinica();
				
				CrearCita refresh = new CrearCita();
				refresh.setVisible(true);
				dispose();
			}
		});
		btnCrear.setBounds(410, 461, 90, 25);
		panel.add(btnCrear);
	}
}
