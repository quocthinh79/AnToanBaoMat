package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import tcp.ClientTransport;
import tcp.ServerTransport;
import tcp.Transport;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class RSAvsDES extends JPanel {
	private List<Integer> key = new ArrayList<>();
	private JTextField textField;
	private JTextField textField_1;

	private ClientTransport client;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */

	public RSAvsDES() {
		client = new ClientTransport();
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(170, 49, 290, 320);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Public key:");
		lblNewLabel_1.setBounds(10, 11, 181, 22);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 270, 73);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		JLabel lblNewLabel_1_4 = new JLabel("Private key:");
		lblNewLabel_1_4.setBounds(10, 113, 181, 22);
		panel.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Secret key:");
		lblNewLabel_1_5.setBounds(10, 214, 181, 22);
		panel.add(lblNewLabel_1_5);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 134, 268, 71);
		panel.add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 238, 268, 71);
		panel.add(scrollPane_2);

		JTextArea textArea_1_1 = new JTextArea();
		textArea_1_1.setEditable(false);
		scrollPane_2.setViewportView(textArea_1_1);
		textArea_1_1.setWrapStyleWord(true);
		textArea_1_1.setLineWrap(true);

		JLabel lblNewLabel = new JLabel("RSA vs DES (Client Server)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(513, 11, 208, 27);
		add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(470, 49, 290, 320);
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
		panel_2.setBounds(770, 49, 290, 320);
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

		JButton btnNewButton_1_1 = new JButton("Start");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String fileLink = textField.getText();
					client.handleInClient(fileLink, "UPLOAD");
					textArea.setText(client.getPublicKey());
					textArea_1.setText(client.getPrivateKey());
					textArea_1_1.setText(client.getSecretKey());
					String pathFile = client.getPathFileInSerser();
					File file = new File(pathFile);
					textField_1.setText(file.getCanonicalPath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setBounds(574, 380, 89, 23);
		add(btnNewButton_1_1);

	}

	public void setKey(List<Integer> key) {
		this.key = key;
	}
}
