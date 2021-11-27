package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logica.Clinica;
import logica.Enfermedad;
import logica.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacienteList extends JFrame {

	private JPanel contentPane;
	private static JTextField txtBuscar;
	private JTable table;
	public static DefaultTableModel model;
	private static Object[] row;
	private Paciente selected = null;
	private TableRowSorter<DefaultTableModel> trs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacienteList frame = new PacienteList();
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
	public PacienteList() {
		setTitle("Lista de pacientes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 751, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 138, 704, 257);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				if(select != -1) {
					selected = Clinica.getInstance().SearchPaciente(table.getValueAt(select, 0).toString());
				}
			}
		});
		model = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		String[] headers = {"Codigo","Nombre","Cedula","Edad","Genero"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 704, 111);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtBuscar.getText(), 1));
				}catch(PatternSyntaxException e1) {
					trs.setRowFilter(RowFilter.regexFilter("(?i)", 1));
				}

			}
		});
		
		txtBuscar.setBounds(10, 75, 374, 25);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Buscar paciente: ");
		lblNewLabel.setBounds(10, 55, 374, 14);
		panel_1.add(lblNewLabel);
		
		JComboBox cbxGenero = new JComboBox();
		cbxGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPacTable(cbxGenero.getSelectedIndex());
			}
		});
		cbxGenero.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Masculino", "Femenino"}));
		cbxGenero.setBounds(394, 75, 300, 25);
		panel_1.add(cbxGenero);
		
		JLabel lblNewLabel_1 = new JLabel("Genero: ");
		lblNewLabel_1.setBounds(394, 55, 300, 14);
		panel_1.add(lblNewLabel_1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(624, 410, 90, 25);
		panel.add(btnSalir);
		
		JButton btnRevisar = new JButton("Revisar");
		btnRevisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RevPaciente revpaciente = new RevPaciente(selected);
				revpaciente.setVisible(true);
			}
		});
		btnRevisar.setBounds(525, 410, 90, 25);
		panel.add(btnRevisar);
		
		loadPacTable(0);
	}
	
	public static void loadPacTable(int index) {
		txtBuscar.setText("");
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		int edad = 0;
		switch (index) {
		case 0:
			for (Paciente pac : Clinica.getInstance().getMisPacientes()) {
				row[0] = pac.getCodigo();
				row[1] = pac.getNombre();
				row[2] = pac.getCedula();
				for(int i = pac.getFechaNacimiento().get(Calendar.YEAR); i < Calendar.getInstance().get(Calendar.YEAR); i++)
				edad++;
				row[3] = edad;
				edad = 0;
				row[4] = pac.getGenero();
				model.addRow(row);
			}
			break;
		case 1:
			for (Paciente pac : Clinica.getInstance().getMisPacientes()) {
				if(pac.getGenero().equalsIgnoreCase("Masculino")) {
					row[0] = pac.getCodigo();
					row[1] = pac.getNombre();
					row[2] = pac.getCedula();
					for(int i = pac.getFechaNacimiento().get(Calendar.YEAR); i < Calendar.getInstance().get(Calendar.YEAR); i++)
					edad++;
					row[3] = edad;
					edad = 0;
					row[4] = pac.getGenero();
					model.addRow(row);
				}
			}
			break;
			
		case 2:
			for (Paciente pac : Clinica.getInstance().getMisPacientes()) {
				if(pac.getGenero().equalsIgnoreCase("Femenino")) {
					row[0] = pac.getCodigo();
					row[1] = pac.getNombre();
					row[2] = pac.getCedula();
					for(int i = pac.getFechaNacimiento().get(Calendar.YEAR); i < Calendar.getInstance().get(Calendar.YEAR); i++)
					edad++;
					row[3] = edad;
					edad = 0;
					row[4] = pac.getGenero();
					model.addRow(row);
				}
			}
			break;
		default:
			break;
		}


	}
}
