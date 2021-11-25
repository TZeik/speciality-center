package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cita", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 445, 124);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JTextField comboBox = new JTextField();
		comboBox.setEditable(false);
		comboBox.setBounds(10, 50, 200, 25);
		panel_1.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(220, 50, 216, 61);
		panel_1.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Cita:");
		lblNewLabel.setBounds(10, 30, 241, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Finalidad:");
		lblNewLabel_1.setBounds(220, 30, 216, 14);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Buscar cita");
		btnNewButton.setBounds(10, 86, 200, 25);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 146, 445, 292);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(10, 50, 425, 70);
		panel_2.add(textArea_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sintomas: ");
		lblNewLabel_2.setBounds(10, 30, 250, 14);
		panel_2.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 150, 425, 25);
		panel_2.add(comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Enfermedad: ");
		lblNewLabel_3.setBounds(10, 130, 250, 14);
		panel_2.add(lblNewLabel_3);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(10, 204, 425, 70);
		panel_2.add(textArea_2);
		
		JLabel lblNewLabel_4 = new JLabel("Diagnostico:");
		lblNewLabel_4.setBounds(10, 184, 250, 14);
		panel_2.add(lblNewLabel_4);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(365, 449, 90, 25);
		panel.add(btnSalir);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(265, 449, 90, 25);
		panel.add(btnCrear);
	}
}
