package com.SocScore.javaswing;

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.LeagueInput;
import com.SocScore.framework.scorekeeper.BatchInput;
import com.SocScore.framework.scorekeeper.LiveInput;
import com.SocScore.framework.scorekeeper.ScoreKeeperType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorekeeperType extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScorekeeperType frame = new ScorekeeperType();
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
	public ScorekeeperType() {
		setTitle("Mode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		AccessManager.authenticate(1234);
		JButton btnLiveInput = new JButton("Live input");
		btnLiveInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LiveInput liveInput1 = (LiveInput) AccessManager.setInputType(ScoreKeeperType.LIVE_INPUT);
				
			}
		});
		btnLiveInput.setBounds(35, 115, 117, 29);
		contentPane.add(btnLiveInput);
		
		JButton btnBatchInput = new JButton("Batch input");
		btnBatchInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BatchInput batchInput1 = (BatchInput) AccessManager.setInputType(ScoreKeeperType.BATCH_INPUT);
				
			}
		});
		btnBatchInput.setBounds(164, 115, 117, 29);
		contentPane.add(btnBatchInput);
		
		JButton btnLeagueInput = new JButton("League input");
		btnLeagueInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LeagueInput leagueInput1 = (LeagueInput) AccessManager.setInputType(ScoreKeeperType.LEAGUE_INPUT);
			}
		});
		btnLeagueInput.setBounds(289, 115, 117, 29);
		contentPane.add(btnLeagueInput);
	}
}
