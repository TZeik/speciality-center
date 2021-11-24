package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Clinica;
import logica.Usuario;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.UIManager;
import java.awt.Color;

public class UserList extends JFrame {

	private JPanel contentPane;
	public static DefaultTableModel model;
	private JTable table;
	private static JComboBox cbxTipo;
	private static Object[] row;
	private Usuario selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserList frame = new UserList();
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
	public UserList() {
		setTitle("Lista de usuarios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 614, 96);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de usuario: ");
		lblNewLabel.setBounds(10, 35, 300, 14);
		panel_1.add(lblNewLabel);
		
		cbxTipo = new JComboBox();
		cbxTipo.setBounds(10, 60, 300, 25);
		panel_1.add(cbxTipo);
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Administrador", "M\u00E9dico", "Secretario"}));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 614, 222);
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
		String[] headers = {"Codigo","Nombre","ID","Contraseña","Teléfono"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(534, 351, 90, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setBounds(434, 352, 90, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Editar");
		btnNewButton_2.setBounds(334, 352, 90, 25);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Revisar");
		btnNewButton_3.setBounds(234, 352, 90, 25);
		panel.add(btnNewButton_3);
		
		loadUserTable(0);
	}
	
	public static void loadUserTable(int selection) {
		cbxTipo.setSelectedIndex(0);
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		switch (selection) {
		case 0:
			for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
				row[0] = user.getCodigo();
				row[1] = user.getNombre();
				row[2] = user.getId();
				row[3] = user.getPassword();
				row[4] = user.getTelefono();
				model.addRow(row);
			}
			break;
		case 1:
			for (Usuario user : Clinica.getInstance().tipoEspecifico(0)){
				row[0] = user.getCodigo();
				row[1] = user.getNombre();
				row[2] = user.getId();
				row[3] = user.getPassword();
				row[4] = user.getTelefono();
				model.addRow(row);
			}
			break;
		case 2:
			for (Usuario user : Clinica.getInstance().tipoEspecifico(1)){
				row[0] = user.getCodigo();
				row[1] = user.getNombre();
				row[2] = user.getId();
				row[3] = user.getPassword();
				row[4] = user.getTelefono();
				model.addRow(row);
			}
			break;
		case 3:
			for (Usuario user : Clinica.getInstance().tipoEspecifico(2)){
				row[0] = user.getCodigo();
				row[1] = user.getNombre();
				row[2] = user.getId();
				row[3] = user.getPassword();
				row[4] = user.getTelefono();
				model.addRow(row);
			}
			break;

		default:
			break;
		}

	}
}


