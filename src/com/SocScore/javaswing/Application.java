package com.SocScore.javaswing;

import com.SocScore.framework.AccessManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

	private JFrame frmLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 485, 289);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Type your access code here");
		passwordField.setBounds(226, 148, 201, 43);
		frmLogin.getContentPane().add(passwordField);
		
		JLabel lblAccessCode = new JLabel("Access Code");
		lblAccessCode.setBounds(90, 155, 87, 29);
		frmLogin.getContentPane().add(lblAccessCode);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int password;
				boolean isAuth = false;
				
				try {
					
					password= Integer.parseInt(passwordField.getText());
					
					isAuth = AccessManager.authenticate(password);
					
					if (isAuth) {
					JOptionPane.showMessageDialog (null, "Hello Scorekeeper, login successfully. ");
					
					frmLogin.dispose();
					
					ScorekeeperType scorekeeper1 = new ScorekeeperType();
					
					scorekeeper1.setVisible(true);
					}
					
					
					
					
				}catch (Exception e1){
					
					JOptionPane.showMessageDialog (null, e1);
					
					
				}
				
				
			}
		});
		//BatchInput batchInput = (BatchInput) AccessManager.setInputType(ScoreKeeperType.BATCH_INPUT);
		btnLogin.setBounds(165, 214, 117, 29);
		frmLogin.getContentPane().add(btnLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(18, 109, 444, 27);
		frmLogin.getContentPane().add(separator);
		
		JLabel lblFansOfSoccer = new JLabel("Fans of Soccer?");
		lblFansOfSoccer.setBounds(77, 58, 123, 30);
		frmLogin.getContentPane().add(lblFansOfSoccer);
		
		JButton btnClickHere = new JButton("Click here!");
		btnClickHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmLogin.dispose();
				
				Viewer viewer1 = new Viewer();
				
				viewer1.setVisible(true);
			}
		});
		btnClickHere.setBounds(258, 60, 117, 29);
		frmLogin.getContentPane().add(btnClickHere);
	}
}
