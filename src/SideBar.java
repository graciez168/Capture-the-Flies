import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class SideBar extends JPanel implements ChangeListener, ActionListener{

	static int initialWallColor = 0;
	static JSlider wallColors = new JSlider(0, 360, initialWallColor);
	JLabel wC = new JLabel("Wall Colors");
	
	static int initialNumFlies = 10;
	static JSlider numFlies = new JSlider(1, 50, initialNumFlies);
	JLabel nF = new JLabel("Number of Flies");
	
	static int initialFlySpeed = 10;
	static JSlider flySpeed = new JSlider(1, 20, initialFlySpeed);
	JLabel fS = new JLabel("Fly Speed");
	
	static JButton reset = new JButton("Reset values");
	
	String[] levelChoices = {"Easy", "Medium", "Hard"};
	JComboBox levels = new JComboBox(levelChoices);
	
	public void stateChanged(ChangeEvent evt) {
		if (evt.getSource().equals(wallColors)) {
			CaptureTheFliesPanel.setWallColorNum(wallColors.getValue());
		}
		
		if (evt.getSource().equals(numFlies)) {
			CaptureTheFliesPanel.setNumFlies(numFlies.getValue());
		}
		
		if (evt.getSource().equals(flySpeed)) {
			CaptureTheFliesPanel.setSpeedNum(flySpeed.getValue());
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		reset();
	}
	
	public static void reset() {
		wallColors.setValue(initialWallColor);
		numFlies.setValue(initialNumFlies);
		flySpeed.setValue(initialFlySpeed);
	}
	
	public SideBar() {
		setLayout(null);
		setBackground(Color.WHITE);

		add(wallColors);
		wallColors.setSize(150, 20);
		wallColors.setLocation(31, 26);
		wallColors.addChangeListener(this);
		
		add(wC);
		wC.setSize(100, 30);
		wC.setLocation(50, 49);
	
		add(numFlies);
		numFlies.setSize(150, 20);
		numFlies.setLocation(31, 131);
		numFlies.addChangeListener(this);
		
		add(nF);
		nF.setSize(100, 30);
		nF.setLocation(50, 149);
		
		add(flySpeed);
		flySpeed.setSize(150, 20);
		flySpeed.setLocation(31, 231);
		flySpeed.addChangeListener(this);
		
		add(fS);
		fS.setSize(100, 30);
		fS.setLocation(50, 249);
		
		add(reset);
		reset.setSize(150, 30);
		reset.setLocation(25, 325);
		reset.addActionListener(this);
		/*add(levels);
		levels.setSize(150, 30);
		levels.setLocation(25, 425);
		*/
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(0,0,199,499);
	}
}
