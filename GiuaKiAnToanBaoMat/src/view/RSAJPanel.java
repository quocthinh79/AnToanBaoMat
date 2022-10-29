package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import model.RSA;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;

public class RSAJPanel extends JPanel {
	private List<Integer> key = new ArrayList<>();
	private RSA rsa = new RSA();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */

	public RSAJPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 43, 290, 320);
		add(panel);
		panel.setLayout(null);

		JButton createBtn = new JButton("Create key");
		createBtn.setBounds(90, 217, 115, 23);
		panel.add(createBtn);

		String bits[] = { "512", "1024", "2048", "4096" };
		JComboBox comboBox = new JComboBox(bits);
		comboBox.setBounds(99, 11, 181, 28);
		panel.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("Key size");
		lblNewLabel_2.setBounds(10, 11, 79, 28);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Public key");
		lblNewLabel_3.setBounds(10, 46, 79, 23);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Private key");
		lblNewLabel_3_1.setBounds(14, 124, 75, 23);
		panel.add(lblNewLabel_3_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 270, 42);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 146, 270, 42);
		panel.add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		JLabel lblNewLabel = new JLabel("RSA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(580, 11, 70, 27);
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
				int rVal = fileChooser.showOpenDialog(RSAJPanel.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						file.setReadable(true);
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
		textField.setToolTipText("Enter number");
		textField.setColumns(10);
		textField.setBounds(10, 44, 270, 28);
		panel_1.add(textField);

		JLabel lblNewLabel_1_1 = new JLabel("Input:");
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

		JLabel lblNewLabel_1_2 = new JLabel("Encrypt:");
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

		JLabel lblNewLabel_1_3 = new JLabel("Decrypt:");
		lblNewLabel_1_3.setBounds(10, 11, 181, 22);
		panel_3.add(lblNewLabel_1_3);

		JButton btnNewButton_1 = new JButton("Encrypt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				File file = new File(input);
				if (file.isFile()) {
					try {
						String extension = input.substring(input.lastIndexOf(".") + 1, input.length());
						File fileOut = new File("./OutEncrypt" + "." + extension);
						rsa.encryptFile(file.getAbsolutePath(), fileOut.getAbsolutePath());
						textField_1.setText(fileOut.getCanonicalPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						String stringEncrypt = Base64.getEncoder().encodeToString(rsa.encrypt(input));
						textField_1.setText(stringEncrypt);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton_1.setBounds(511, 374, 89, 23);
		add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Decrypt");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				File file = new File(input);
				if (file.isFile()) {
					try {
						String extension = input.substring(input.lastIndexOf(".") + 1, input.length());
						File fileOut = new File("./OutDecrypt" + "." + extension);
						rsa.decryptFile(file.getAbsolutePath(), fileOut.getAbsolutePath());
						textField_2.setText(fileOut.getCanonicalPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						String stringEncrypt = rsa.decrypt(Base64.getDecoder().decode((input.getBytes())));
						textField_2.setText(stringEncrypt);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1_1.setBounds(610, 374, 89, 23);
		add(btnNewButton_1_1);

		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int data = 0;
				if (comboBox.getSelectedIndex() != -1) {
					data = Integer.parseInt((String) comboBox.getItemAt(comboBox.getSelectedIndex()));
				}
				rsa.getKey(data);
				String publicKey = Base64.getEncoder().encodeToString(rsa.getPublicKey().getEncoded());
				String privateKey = Base64.getEncoder().encodeToString(rsa.getPrivateKey().getEncoded());
				textArea.setText(publicKey);
				textArea_1.setText(privateKey);
			}
		});
	}

	public void setKey(List<Integer> key) {
		this.key = key;
	}
}
