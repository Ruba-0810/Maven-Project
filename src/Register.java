import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.DriverManager;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtmobile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
		Connect();
	}
	
	Connection con;//it use use to connect the database
	PreparedStatement pst;// it is use to store tha data
	String name,mobile,gender,opition;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gschool","root","");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(100, 100, 655, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registation");
		lblNewLabel.setBounds(224, 11, 275, 50);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(193, 157, 0, 0);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(68, 115, 534, 281);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(37, 48, 114, 29);
		panel_1.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(178, 48, 285, 29);
		panel_1.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(37, 106, 92, 45);
		panel_1.add(lblNewLabel_2);
		
		txtmobile = new JTextField();
		txtmobile.setColumns(10);
		txtmobile.setBounds(178, 106, 285, 29);
		panel_1.add(txtmobile);
		
		JLabel lblNewLabel_2_1 = new JLabel("Gender");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2_1.setBounds(37, 173, 92, 29);
		panel_1.add(lblNewLabel_2_1);
		
		JRadioButton rmale = new JRadioButton("Male");
		rmale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rmale.setBounds(178, 179, 61, 23);
		panel_1.add(rmale);
		
		JRadioButton rfemale = new JRadioButton("Female");
		rfemale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rfemale.setBounds(371, 179, 92, 23);
		panel_1.add(rfemale);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Opition");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2_1_1.setBounds(37, 225, 92, 29);
		panel_1.add(lblNewLabel_2_1_1);
		
		JComboBox txtopition = new JComboBox();
		txtopition.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtopition.setModel(new DefaultComboBoxModel(new String[] {"Java", "HTML", "CSS", "JavaScript", "Sql"}));
		txtopition.setBounds(178, 227, 285, 29);
		panel_1.add(txtopition);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				name=txtname.getText();
				mobile=txtmobile.getText();
				if(rmale.isSelected()) {//return type boolean
					gender="Male";
				}
				if(rfemale.isSelected()) {
					gender="Female";
				}
				opition=txtopition.getSelectedItem().toString();
				try {
					pst=con.prepareStatement("insert into Student(name,mobile,gender,opition)values(?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, mobile);
					pst.setString(3, gender);
					pst.setString(4, opition);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Addeddd.....");
					txtname.setText("");
					txtmobile.setText("");
					txtopition.setSelectedIndex(-1);
					txtname.requestFocus();
					
					
					
				}catch(SQLException exe){
					exe.printStackTrace();
					
				}
				
				
				
			}
		});
		btnNewButton.setBounds(503, 407, 89, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnNewButton);
	}
}
