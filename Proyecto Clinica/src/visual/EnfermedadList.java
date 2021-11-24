package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Clinica;
import logica.Enfermedad;
import logica.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;

public class EnfermedadList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField txtBuscar;
	public static DefaultTableModel model;
	private static Object[] row;
	private Enfermedad selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnfermedadList frame = new EnfermedadList();
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
	public EnfermedadList() {
		setTitle("Lista de enfermedades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 452, 280);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				if(select != -1) {
					
				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"Codigo","Nombre","Tipo"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 705, 102);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 60, 300, 25);
		txtBuscar.setColumns(10);
		panel_1.add(txtBuscar);
		
		JLabel lblNewLabel = new JLabel("Buscar enfermedad:");
		lblNewLabel.setBounds(10, 35, 300, 14);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(625, 415, 90, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setBounds(525, 415, 90, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Editar");
		btnNewButton_2.setBounds(425, 415, 90, 25);
		panel.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(475, 124, 240, 280);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(10, 42, 220, 227);
		panel_2.add(lblNewLabel_1);
	}
	
	public static void loadUserTable(String search) {
		txtBuscar.setText("");
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

			for (Enfermedad enf : Clinica.getInstance().getMisEnfermedades()) {
				row[0] = enf.getCodigo();
				row[1] = enf.getNombre();
				row[2] = enf.getTipo();

				model.addRow(row);
			}


	}
}
