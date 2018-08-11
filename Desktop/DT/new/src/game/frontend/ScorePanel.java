package game.frontend;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel scoreLabel;

	public ScorePanel(int width, int height) {
		setSize(width, height);
		setBackground(Color.CYAN);
		scoreLabel = new JLabel("0");
		scoreLabel.setBounds(1, 1, width, height);
		scoreLabel.setBackground(Color.RED);
		scoreLabel.setForeground(Color.RED);
		add(scoreLabel);
	}
	
	public void updateScore(String text) {
		scoreLabel.setText(text);
	}

}