package com.daviddu64;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Chifoumi extends JFrame {

	private JPanel contentPane;
	private JButton btnPierre;
	private JButton btnPapier;
	private JButton btnCiseaux;
	private JButton btnStart;
	private JButton btnStop;
	private JLabel lblScore;
	private JLabel lblSteps;
	private JLabel lblResultat;
	private JProgressBar pbCountDown;
	private String userChoice = "";
	private String botChoice = "";
	private int pbValue = 10;
	private int stepsNumber = 1;
	private int userScore = 0;
	private String[] listChoice = { "Pierre", "Papier", "Ciseaux" };
	private Random random = new Random();
	private int rdm;
	private String matchResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chifoumi frame = new Chifoumi();
					frame.setLocationRelativeTo(null);
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
	public Chifoumi() {

		setTitle("Chifoumi");
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 481);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnJeuxDuChifoumi = new JTextPane();
		txtpnJeuxDuChifoumi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnJeuxDuChifoumi.setText(
				"Jeux du chifoumi en 10 étapes.\r\nPour commencer cliquez sur le bouton jouer.\r\nPuis cliquez sur l'image de votre choix.\r\nVous avez 10 secondes entre chaques étapes pour faire un choix.\r\n");
		txtpnJeuxDuChifoumi.setBounds(43, 35, 467, 84);
		contentPane.add(txtpnJeuxDuChifoumi);

		btnPierre = new JButton("");
		btnPierre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userChoice = "Pierre";
				btnPierre.setEnabled(false);
				btnPapier.setEnabled(false);
				btnCiseaux.setEnabled(false);
				StartGame();
			}
		});
		btnPierre.setEnabled(false);
		ImageIcon imageIconPierre = new ImageIcon(getClass().getClassLoader().getResource("Pierre.jpg"));
		btnPierre.setIcon(imageIconPierre);
		btnPierre.setBounds(43, 136, 150, 150);
		contentPane.add(btnPierre);

		btnPapier = new JButton("");
		btnPapier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userChoice = "Papier";
				btnPierre.setEnabled(false);
				btnPapier.setEnabled(false);
				btnCiseaux.setEnabled(false);
				StartGame();
			}
		});
		btnPapier.setEnabled(false);
		ImageIcon imageIconPapier = new ImageIcon(getClass().getClassLoader().getResource("Papier.jpg"));
		btnPapier.setIcon(imageIconPapier);
		btnPapier.setBounds(203, 136, 150, 150);
		contentPane.add(btnPapier);

		btnCiseaux = new JButton("");
		btnCiseaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userChoice = "Ciseaux";
				btnPierre.setEnabled(false);
				btnPapier.setEnabled(false);
				btnCiseaux.setEnabled(false);
				StartGame();
			}
		});
		btnCiseaux.setEnabled(false);
		ImageIcon imageIconCiseaux = new ImageIcon(getClass().getClassLoader().getResource("Ciseaux.jpg"));
		btnCiseaux.setIcon(imageIconCiseaux);
		btnCiseaux.setBounds(363, 136, 150, 150);
		contentPane.add(btnCiseaux);

		btnStart = new JButton("Jouer");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPierre.setEnabled(true);
				btnPapier.setEnabled(true);
				btnCiseaux.setEnabled(true);
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);

				RunGame();

			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.setBounds(43, 297, 94, 33);
		contentPane.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(false);

				// StopGame();
			}
		});
		btnStop.setEnabled(false);
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStop.setBounds(416, 297, 94, 33);
		contentPane.add(btnStop);

		pbCountDown = new JProgressBar();
		pbCountDown.setValue(10);
		pbCountDown.setMaximum(10);
		pbCountDown.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pbCountDown.setBounds(153, 297, 253, 33);
		contentPane.add(pbCountDown);

		lblScore = new JLabel("Score: 0");
		lblScore.setForeground(Color.BLUE);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setBounds(43, 367, 115, 23);
		contentPane.add(lblScore);

		lblSteps = new JLabel("Etapes 0 sur 10");
		lblSteps.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSteps.setForeground(Color.BLUE);
		lblSteps.setBounds(367, 367, 143, 23);
		contentPane.add(lblSteps);

		lblResultat = new JLabel("En attente d'une nouvelle partie ...");
		lblResultat.setForeground(new Color(0, 0, 204));
		lblResultat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblResultat.setBounds(43, 401, 419, 30);
		contentPane.add(lblResultat);

	}

	private void StopGame() {
		btnPierre.setEnabled(false);
		btnPapier.setEnabled(false);
		btnCiseaux.setEnabled(false);
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		JOptionPane.showMessageDialog(null, "La partie est terminée, votre score est de: " + userScore);
		userChoice = "";
		botChoice = "";
		matchResult = "";
		stepsNumber = 1;
		userScore = 0;
		pbValue = 10;
		lblResultat.setText("En attente d'une nouvelle partie");
		lblResultat.setForeground(Color.blue);
		lblScore.setText("Score: " + userScore);
		lblSteps.setText("Etapes " + stepsNumber + " sur 10");
		pbCountDown.setValue(pbValue);
	}

	private void StartGame() {
		// on genere le choix aléatoire
		rdm = random.nextInt(0, 3);
		botChoice = listChoice[rdm];
		getResult();
		if (matchResult == "Perdu") {
			lblResultat.setText("Vous avez perdu, le bot a joué: " + botChoice);
			lblResultat.setForeground(Color.red);
		} else if (matchResult == "Gagné") {
			lblResultat.setText("Vous avez Gagné, le bot a joué: " + botChoice);
			lblResultat.setForeground(Color.green);
			userScore += 2;
		} else if (matchResult == "Ex aequo") {
			lblResultat.setText("Vous etes ex aequo, le bot a joué: " + botChoice);
			lblResultat.setForeground(Color.magenta);
			userScore += 1;
		}
		lblScore.setText("Score: " + userScore);

	}

	private void RunGame() {
		// on creer un thread pour rafraichir les elements
		new Thread(new Runnable() {
			public void run() {

				while (stepsNumber <= 10) {
					if (btnStop.isEnabled() == false) {
						StopGame();
						return;
					}
					if (pbValue >= 0) {
						pbCountDown.setValue(pbValue);
						pbValue--;
					} else {
						if (stepsNumber == 10) {
							StopGame();
							return;
						} else {
							stepsNumber++;
							btnPierre.setEnabled(true);
							btnPapier.setEnabled(true);
							btnCiseaux.setEnabled(true);
							lblScore.setText("Score: " + userScore);
							lblResultat.setText("Jouer");
						}
						lblSteps.setText("Etapes " + stepsNumber + " sur 10");
						pbValue = 10;
					}

					// On fait une pause d'une seconde pour la barre de progression
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return;
				// on lance le thread
			}
		}).start();

	}

	private void getResult() {
		switch (userChoice) {
		case "Pierre":
			if (botChoice == "Pierre") {
				matchResult = "Ex aequo";
			}
			if (botChoice == "Papier") {
				matchResult = "Perdu";
			}
			if (botChoice == "Ciseaux") {
				matchResult = "Gagné";
			}
			break;
		case "Papier":
			if (botChoice == "Pierre") {
				matchResult = "Gagné";
			}
			if (botChoice == "Papier") {
				matchResult = "Ex aequo";
			}
			if (botChoice == "Ciseaux") {
				matchResult = "Perdu";
			}
			break;
		case "Ciseaux":
			if (botChoice == "Pierre") {
				matchResult = "Perdu";
			}
			if (botChoice == "Papier") {
				matchResult = "Gagné";
			}
			if (botChoice == "Ciseaux") {
				matchResult = "Ex aequo";
			}
			break;
		}
	}
}
