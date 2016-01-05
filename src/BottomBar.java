import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class BottomBar extends JPanel implements ActionListener{
	
	JLabel currentScore = new JLabel("Current Score: " + 0);
	JButton instructions = new JButton("Instructions");
	JButton newGame = new JButton("New Game");
	
	public BottomBar() {
		setLayout(null);
		setBackground(Color.BLUE);
		
		add(currentScore);
		currentScore.setFont(new Font("Serif",Font.BOLD,18));
		currentScore.setSize(200, 40);
		currentScore.setLocation(30,5);
		
		add(instructions);
		instructions.setSize(120, 40);
		instructions.setLocation(240, 5);
		instructions.addActionListener(this);
		
		add(newGame);
		newGame.setSize(120, 40);
		newGame.setLocation(420, 5);
		newGame.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getSource().equals(instructions)) {
			JOptionPane.showMessageDialog(CaptureTheFliesMain.window, "The goal of the game is to trap all the flies \n" 
					+ "within in the green target square by placing or \n " 
					+ "removing blocks by clicking the screen.  If you \n" 
					+ "place a wall over a fly, it will freeze the fly \n"
					+ "and you will have to remove the block in order to\n"
					+ "free it. Your score records how many blocks \n"
					+"you add or remove.  Try to finish the game with\n" 
					+"the lowest score.");
		}
		if (evt.getSource().equals(newGame)){

			CaptureTheFliesPanel.endGame = false;
			CaptureTheFliesPanel.score = 0;
			
			currentScore.setText("Current Score: " + 0);
			//SideBar.reset();
			CaptureTheFliesMain.gamePanel.clearTilesAndFlies();
			CaptureTheFliesPanel.createTilesAndFlies();
			CaptureTheFliesMain.gamePanel.setEndGameText(false);
			SideBar.numFlies.setEnabled(true);
			SideBar.wallColors.setEnabled(true);
			SideBar.flySpeed.setEnabled(true);
			SideBar.reset.setEnabled(true);

			CaptureTheFliesPanel.endGame = false;
			
		}
		
	}

}
