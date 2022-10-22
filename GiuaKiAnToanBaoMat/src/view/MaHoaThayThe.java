package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.border.LineBorder;

import model.AlternativeEncoding;
import model.DisplacementEncoding;
import model.Model;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;

public class MaHoaThayThe extends JPanel {
	private AlternativeEncoding alternativeModel;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MaHoaThayThe() {
		setLayout(null);

		JPanel panel = new JPanel(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 54, 289, 319);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Key (Number)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 86, 42);
		panel.add(lblNewLabel);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(106, 16, 173, 37);
		panel.add(scrollPane_3);

		textField = new JTextField();
		scrollPane_3.setViewportView(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Random key");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(String.valueOf(alternativeModel.createKey()));
			}
		});
		btnNewButton_1.setBounds(90, 80, 113, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Key in file");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int rVal = fileChooser.showOpenDialog(MaHoaThayThe.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						BufferedReader in = new BufferedReader(new FileReader(file));
						String line = in.readLine();
						while (line != null) {
							textField.setText(textField.getText() + "\n" + line);
							line = in.readLine();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}
		});
		btnNewButton_1_1.setBounds(90, 162, 113, 23);
		panel.add(btnNewButton_1_1);

		JPanel panel_1 = new JPanel(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(312, 54, 289, 319);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 269, 243);
		panel_1.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JButton btnNewButton_2 = new JButton("From file");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int rVal = fileChooser.showOpenDialog(MaHoaThayThe.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						BufferedReader in = new BufferedReader(new FileReader(file));
						String line = in.readLine();
						while (line != null) {
							textArea.setText(textArea.getText() + "\n" + line);
							line = in.readLine();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}
		});
		btnNewButton_2.setBounds(101, 285, 89, 23);
		panel_1.add(btnNewButton_2);

		JLabel lblNewLabel_2 = new JLabel("Input");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(128, 11, 34, 13);
		panel_1.add(lblNewLabel_2);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1_1.setBounds(608, 54, 289, 319);
		add(panel_1_1);
		panel_1_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 35, 269, 273);
		panel_1_1.add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);

		JLabel lblNewLabel_2_1 = new JLabel("Encrypt");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(125, 11, 42, 13);
		panel_1_1.add(lblNewLabel_2_1);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1_1_1.setBounds(905, 54, 289, 319);
		add(panel_1_1_1);
		panel_1_1_1.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 35, 269, 273);
		panel_1_1_1.add(scrollPane_2);

		JTextArea textArea_1_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1_1);
		textArea_1_1.setLineWrap(true);
		textArea_1_1.setWrapStyleWord(true);

		JLabel lblNewLabel_2_1_1 = new JLabel("Decypt");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(129, 11, 38, 13);
		panel_1_1_1.add(lblNewLabel_2_1_1);

		JButton btnNewButton = new JButton("Encrypt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(String.valueOf(alternativeModel.encrypt(textArea.getText(), textField.getText())));
			}
		});
		btnNewButton.setBounds(512, 384, 89, 23);
		add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Mã hóa thay thế");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(543, 11, 126, 32);
		add(lblNewLabel_1);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1_1.setText(String.valueOf(alternativeModel.decrypt(textArea.getText(), textField.getText())));
			}
		});
		btnDecrypt.setBounds(611, 384, 89, 23);
		add(btnDecrypt);

	}
}
