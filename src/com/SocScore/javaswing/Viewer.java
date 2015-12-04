package com.SocScore.javaswing;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewer frame = new Viewer();
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
	public Viewer() {
		setTitle("Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 425);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		
		
		
		JList list = new JList();
		list.setBounds(153, 140, 293, 205);
		getContentPane().add(list);
		
		DefaultListModel DLM = new DefaultListModel();
		
		
		
		
		
		//AnalysisViewer analysisViewer1 = AccessManager.unAuthenticate();
		
		
		
	}
}
