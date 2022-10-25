package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import tcp.ClientTransport;
import tcp.ServerTransport;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class RSAvsDES extends JPanel {
	private JTextField createWithSize;
	private List<Integer> key = new ArrayList<>();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private ClientTransport client;
	private ServerTransport server;

	/**
	 * Create the panel.
	 */

	public RSAvsDES() {
		client = new ClientTransport();
		server = new ServerTransport();
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 43, 290, 320);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Key file link:");
		lblNewLabel_1.setBounds(10, 11, 181, 22);
		panel.add(lblNewLabel_1);

		createWithSize = new JTextField();
		createWithSize.setToolTipText("Enter number");
		createWithSize.setBounds(10, 44, 270, 28);
		panel.add(createWithSize);
		createWithSize.setColumns(10);

		JButton createBtn = new JButton("Create key");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createBtn.setBounds(99, 177, 115, 23);
		panel.add(createBtn);

		JLabel lblNewLabel = new JLabel("RSA vs DES (Client Server)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(513, 11, 208, 27);
		add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(310, 43, 290, 320);
		add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int rVal = fileChooser.showOpenDialog(RSAvsDES.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						textField.setText(file.getAbsolutePath());
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}
		});
		btnNewButton.setBounds(102, 176, 89, 23);
		panel_1.add(btnNewButton);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 44, 270, 28);
		panel_1.add(textField);

		JLabel lblNewLabel_1_1 = new JLabel("File in client:");
		lblNewLabel_1_1.setBounds(10, 11, 181, 22);
		panel_1.add(lblNewLabel_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(610, 43, 290, 320);
		add(panel_2);
		panel_2.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter number");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 44, 270, 28);
		panel_2.add(textField_1);

		JLabel lblNewLabel_1_2 = new JLabel("File in server:");
		lblNewLabel_1_2.setBounds(10, 11, 181, 22);
		panel_2.add(lblNewLabel_1_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(910, 43, 290, 320);
		add(panel_3);
		panel_3.setLayout(null);

		textField_2 = new JTextField();
		textField_2.setToolTipText("Enter number");
		textField_2.setColumns(10);
		textField_2.setBounds(10, 44, 270, 28);
		panel_3.add(textField_2);

		JLabel lblNewLabel_1_3 = new JLabel("Decrypt file link:");
		lblNewLabel_1_3.setBounds(10, 11, 181, 22);
		panel_3.add(lblNewLabel_1_3);

		JButton btnNewButton_1_1 = new JButton("Send");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.serverAccepted();
					client.handleInClient(textField.getText(), "UPLOAD");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setBounds(560, 374, 89, 23);
		add(btnNewButton_1_1);

	}

	public void setKey(List<Integer> key) {
		this.key = key;
	}
}
