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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.DropMode;

public class EnfermedadList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField txtBuscar;
	public static DefaultTableModel model;
	private static Object[] row;
	private Enfermedad selected = null;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JTextArea txaDescripcion;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 751, 500);
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
		scrollPane.setBounds(10, 124, 452, 280);
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(475, 124, 240, 280);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		txaDescripcion = new JTextArea();
		txaDescripcion.setLineWrap(true);
		txaDescripcion.setEditable(false);
		txaDescripcion.setBounds(10, 26, 220, 243);
		panel_2.add(txaDescripcion);
		
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					if(JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar " + selected.getNombre() + "?", "Enfermedades", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Clinica.getInstance().getMisEnfermedades().remove(selected);
						Clinica.getInstance().guardarClinica();
						loadEnfTable("");
						botonesDef();
						} else {
							botonesDef();
					}
					
				}
			}

		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(525, 415, 90, 25);
		panel.add(btnEliminar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(425, 415, 90, 25);
		panel.add(btnEditar);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				if(select != -1) {
					selected = Clinica.getInstance().SearchEnfermedad((String)table.getValueAt(select, 0));
					txaDescripcion.setText(selected.getDescipcion());
					btnEliminar.setEnabled(true);
					btnEditar.setEnabled(true);
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
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(625, 415, 90, 25);
		panel.add(btnSalir);
		
		loadEnfTable(null);
	}
	
	public static void loadEnfTable(String search) {
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
	
	private void botonesDef() {
		btnEliminar.setEnabled(false);
		btnEditar.setEnabled(false);
		txtBuscar.setText("");
		txaDescripcion.setText("");
	}
}
