package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Cita;
import logica.Clinica;
import logica.Paciente;
import logica.Usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RevPaciente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	public Paciente miPaciente;
	private JComboBox cbxDia;
	private JComboBox cbxMes;
	private JComboBox cbxAnno;
	private JComboBox cbxGenero;
	private JTextArea txaDireccion;
	private JButton btnHistorial;
	private JButton btnEditar;
	private JButton btnGuardar;
	private JButton btnSalir;
	public static DefaultTableModel model;
	private static Object[] row;
	private Cita selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevPaciente frame = new RevPaciente(null);
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
	public RevPaciente(Paciente pac) {
		miPaciente = pac;
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		miPaciente = pac;
		setTitle("Revisar paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 538);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Paciente, " + Clinica.getInstance().getSelectedPaciente().getNombre() , TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 378, 467);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(10, 50, 358, 25);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 30, 358, 14);
		panel_1.add(lblNombre);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setBounds(10, 110, 358, 25);
		panel_1.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("C\u00E9dula: ");
		lblNewLabel.setBounds(10, 90, 358, 14);
		panel_1.add(lblNewLabel);
		
		cbxDia = new JComboBox();
		cbxDia.setEnabled(false);
		
		cbxMes = new JComboBox();
		cbxMes.setEnabled(false);
		cbxMes.setBounds(101, 180, 147, 25);
		panel_1.add(cbxMes);
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
		
		cbxDia.setBounds(10, 180, 81, 25);
		cbxDia.setModel(diaModel);
		panel_1.add(cbxDia);
		
		DefaultComboBoxModel annoModel  = new  DefaultComboBoxModel();
		int index = -1;
		for(int i = Calendar.getInstance().get(Calendar.YEAR) - 100; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
			annoModel.addElement(i);
			index++;
		}
		cbxAnno = new JComboBox();
		cbxAnno.setEnabled(false);
		cbxAnno.setBounds(258, 180, 110, 25);
		panel_1.add(cbxAnno);
		cbxAnno.setModel(annoModel);
		cbxAnno.setSelectedIndex(index);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_1.setBounds(10, 160, 358, 14);
		panel_1.add(lblNewLabel_1);
		
		cbxGenero = new JComboBox();
		cbxGenero.setEnabled(false);
		cbxGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		cbxGenero.setBounds(10, 250, 358, 25);
		panel_1.add(cbxGenero);
		
		JLabel lblNewLabel_2 = new JLabel("G\u00E9nero: ");
		lblNewLabel_2.setBounds(10, 230, 358, 14);
		panel_1.add(lblNewLabel_2);
		
		txaDireccion = new JTextArea();
		txaDireccion.setEditable(false);
		txaDireccion.setBounds(10, 380, 358, 67);
		panel_1.add(txaDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(10, 320, 358, 25);
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_3.setBounds(10, 300, 358, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_4.setBounds(10, 360, 358, 14);
		panel_1.add(lblNewLabel_4);
		
		btnHistorial = new JButton("Ver Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RevHistorial rev = new RevHistorial(miPaciente);
				rev.setVisible(true);
			}
		});
		btnHistorial.setBounds(408, 250, 120, 50);
		panel.add(btnHistorial);
		
		btnEditar = new JButton("Editar Paciente");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setEditable(true);
				txtCedula.setEditable(true);
				cbxDia.setEnabled(true);
				cbxMes.setEnabled(true);
				cbxAnno.setEnabled(true);
				cbxDia.setEditable(true);
				cbxMes.setEditable(true);
				cbxAnno.setEditable(true);
				cbxGenero.setEnabled(true);
				cbxGenero.setEditable(true);
				txtTelefono.setEditable(true);
				txaDireccion.setEditable(true);
				btnGuardar.setEnabled(true);
				
			}
		});
		btnEditar.setBounds(538, 250, 120, 50);
		panel.add(btnEditar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(408, 18, 250, 225);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 230, 188);
		panel_2.add(scrollPane);
		
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		String[] headers = {"Medico" , "Fecha de Cita"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				
				if(select != -1) {
					selected = pac.getHistorial().getMisCitas().get(select);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(580, 453, 90, 25);
		panel.add(btnSalir);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Calendar fechaNaci = Calendar.getInstance();
				fechaNaci.set((int)cbxAnno.getSelectedItem(), cbxMes.getSelectedIndex()+1, (int)cbxDia.getSelectedItem());
				miPaciente.setNombre(txtNombre.getText());
				miPaciente.setCedula(txtCedula.getText());
				miPaciente.setTelefono(txtTelefono.getText());
				miPaciente.setDireccion(txaDireccion.getText());
				miPaciente.setGenero(cbxGenero.getSelectedItem().toString());
				miPaciente.setFechaNacimiento(fechaNaci);
				

				Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(miPaciente)).setNombre(miPaciente.getNombre());
				Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(miPaciente)).setCedula(miPaciente.getCedula());
				Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(miPaciente)).setTelefono(miPaciente.getTelefono());
				Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(miPaciente)).setDireccion(miPaciente.getDireccion());
				Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(miPaciente)).setGenero(miPaciente.getGenero());
				Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(miPaciente)).setFechaNacimiento(miPaciente.getFechaNacimiento());
				Clinica.getInstance().guardarClinica();
				JOptionPane.showMessageDialog(panel, "El paciente ha sido editado con éxito", "Editar Paciente", JOptionPane.DEFAULT_OPTION);
				
				PacienteList.loadPacTable(0);
			}
		});
		btnGuardar.setBounds(480, 453, 90, 25);
		panel.add(btnGuardar);
		
		loadPaciente();
		loadCitaTable();
	}
	
	public String miMes(int index) {
		String mes = null;
		
		switch(index) {
		case 1:
			mes = "Enero";
			break;
		case 2:
			mes = "Febrero";
			break;
		case 3:
			mes = "Marzo";
			break;
		case 4:
			mes = "Abril";
			break;
		case 5:
			mes = "Mayo";
			break;
		case 6:
			mes = "Junio";
			break;
		case 7:
			mes = "Julio";
			break;
		case 8:
			mes = "Agosto";
			break;
		case 9:
			mes = "Septiembre";
			break;
		case 10:
			mes = "Octubre";
			break;
		case 11:
			mes = "Noviembre";
			break;
		case 12:
			mes = "Diciembre";
			break;
			
		default:
			
			break;
		}
		
		return mes;
	}
	
	public void loadPaciente() {
		if(miPaciente != null) {
			int genero;
			txtNombre.setText(miPaciente.getNombre());
			txtCedula.setText(miPaciente.getCedula());
			cbxDia.setSelectedItem(miPaciente.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
			cbxMes.setSelectedItem(miMes(miPaciente.getFechaNacimiento().get(Calendar.MONTH)));
			cbxAnno.setSelectedItem(miPaciente.getFechaNacimiento().get(Calendar.YEAR));
			if(miPaciente.getGenero().equalsIgnoreCase("Masculino")) {
				genero = 0;
			}else {
				genero = 1;
			}
			cbxGenero.setSelectedIndex(genero);
			txtTelefono.setText(miPaciente.getTelefono());
			txaDireccion.setText(miPaciente.getDireccion());
			
		}
	}
	
	public void loadCitaTable() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		String fecha = new String();
		
		for(Cita cit : miPaciente.getHistorial().getMisCitas()) {
			row[0] = cit.getMedico().getNombre();
			fecha = "" + cit.getFechaProgramada().get(Calendar.DAY_OF_MONTH) + " / " + cit.getFechaProgramada().get(Calendar.MONTH) + " / " + cit.getFechaProgramada().get(Calendar.YEAR) + " - " + cit.getFechaProgramada().get(Calendar.HOUR_OF_DAY) + ":" + cit.getFechaProgramada().get(Calendar.MINUTE);
			row[1] = fecha;
			model.addRow(row);
		}
	}
}
