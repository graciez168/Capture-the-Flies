
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.util.ArrayList;


import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CaptureTheFliesPanel extends JPanel implements MouseListener, ActionListener {


	
	

	static int score = 0;
	static ArrayList<Tiles> tileList = new ArrayList<Tiles>(108);
	static ArrayList<Flies> flyList = new ArrayList<Flies>(10);
	static int numFlies = SideBar.initialNumFlies;
	static int speedNum = SideBar.initialFlySpeed;
	Timer timer = new Timer(21 - speedNum,this);
	static boolean endGame = false;
	static int endGameTiles = 0;
	static int endGameColor = 255;
	SideBar sidePanel = new SideBar();
	BottomBar bottomPanel = new BottomBar();

	static JLabel scoreText = new JLabel ("Final Score: " + score);
	static JLabel won = new JLabel ("You won!");

	
	
	
	public void actionPerformed(ActionEvent evt) {

		won.setFont(new Font("Serif",Font.BOLD,25));
		won.setLocation(50, 50);
		won.setSize(500, 150);

		scoreText.setFont(new Font("Serif",Font.BOLD,25));
		scoreText.setLocation(50, 250);
		scoreText.setSize(500, 150);

		timer.setDelay(21 - speedNum);
		int i = 0;
		while (i < numFlies) {
			/*if (!isCurrentTileFree(flyList.get(i))) {
				//System.out.println(flyList.get(i).x + "," + flyList.get(i).y);
			}
			 */

			if (flyList.get(i).inWall) {

			}

			if (!flyList.get(i).inWall) {
				if (isPathFree(flyList.get(i))) {
					flyList.get(i).move(flyList.get(i).direction);
				}

				else {
					flyList.get(i).switchDirection();
					flyList.get(i).move(flyList.get(i).direction);
				}
			}
			i++;
		}

		if (!endGame) {
			int t = 0;
			while(t < numFlies) {
				if (flyList.get(t).isInTarget()) {
					t ++;
					//System.out.println(t +"in");
				}
				else {
					break;
				}

			}

			if (t == numFlies) {
				int x = Tiles.targetX;
				int y = Tiles.targetY;
				//System.out.println("all in");

				if (isCurrentTileFreeForTiles(x - 50, y)) {
					//System.out.println("1");
				}

				else if (isCurrentTileFreeForTiles(x - 50, y + 50)) {
					//System.out.println("2");
				}

				else if (isCurrentTileFreeForTiles(x - 50, y + 100)) {
					//System.out.println("3");
				}

				else if (isCurrentTileFreeForTiles(x, y - 50)) {
					//System.out.println("4");
				}

				else if (isCurrentTileFreeForTiles(x + 50, y - 50)) {
					//System.out.println("5");
				}

				else if (isCurrentTileFreeForTiles(x + 100, y -50)) {
					//System.out.println("6");
				}

				else if (isCurrentTileFreeForTiles(x + 150, y )) {
					//System.out.println("7");

				}

				else if (isCurrentTileFreeForTiles(x + 150, y + 50)) {
					//System.out.println("8");
				}

				else if (isCurrentTileFreeForTiles(x + 150, y + 100)) {
					//System.out.println("9");
				}					

				else if (isCurrentTileFreeForTiles(x, y + 150)) {
					//System.out.println("10");
				}

				else if (isCurrentTileFreeForTiles(x + 50, y + 150)) {
					//System.out.println("11");
				}

				else if (isCurrentTileFreeForTiles(x + 100, y + 150)) {
					//System.out.println("12");
				}

				else {
					//System.out.println("end game");
					endGame = true;
					SideBar.numFlies.setEnabled(false);
					SideBar.wallColors.setEnabled(false);
					SideBar.flySpeed.setEnabled(false);
					SideBar.reset.setEnabled(false);
				}

			}
		}

		if (endGame && endGameTiles == 1) {
			setEndGameText(true);
		}


		if (endGame && endGameTiles < 108) {


			if (endGameTiles > 0 && endGameTiles % 12 == 0) {
				endGameColor = 255 - ((endGameTiles / 12 + 1) * 12);
			}

			else {
				endGameColor -= 12;
			}
			tileList.get(endGameTiles).setColor(0, endGameColor, endGameColor);
			endGameTiles ++;

		}


		repaint();
	}

	public static void setEndGameText(boolean yes) {
		if (yes) {

			CaptureTheFliesMain.gamePanel.add(won);
			scoreText.setText("Final Score: " + score);
			CaptureTheFliesMain.gamePanel.add(scoreText);
		}
		else {
			CaptureTheFliesMain.gamePanel.remove(won);
			CaptureTheFliesMain.gamePanel.remove(scoreText);

		}
	}
	public CaptureTheFliesPanel() {

		setLayout(null);
		addMouseListener(this);
		
		//content.add(gamePanel);
		//gamePanel.setLocation(0,0);
		//gamePanel.setSize(600, 450);
		
	
		add(bottomPanel);
		bottomPanel.setLocation(0,450);
		bottomPanel.setSize(600, 50);
		
		
		add(sidePanel);
		sidePanel.setLocation(600, 0);
		sidePanel.setSize(200, 500);
		
		createTilesAndFlies();
		repaint();
		timer.start();
	}

	public static void clearTilesAndFlies() {
		tileList.clear();
		flyList.clear();
		Tiles.targetChosen = false;
		endGameTiles = 0;
		endGameColor = 255;
	}

	public static void createTilesAndFlies() {
		int x = 0;
		int y = 0;
		boolean isTargetChosen = false;
		int target = 0;
		int i = 1;
		while(i < 109) {

			if (isTargetChosen) {
				if ((i - target) < 3) {
					tileList.add(new Tiles(x,y,true));
				}

				else if ((i - 12 - target) < 3 && (i - 12 - target) >= 0) {
					tileList.add(new Tiles(x,y,true));
				}

				else if ((i - 24 - target) < 3 && (i - 24 - target) >= 0) {
					tileList.add(new Tiles(x,y,true));
				}

				else {
					tileList.add(new Tiles(x,y,false));
				}
			}

			if (!isTargetChosen) {
				int targetNum = (int)(Math.random() * 10) + 1;
				if (targetNum == 7 && x <= 450) {
					tileList.add(new Tiles(x,y,true));
					isTargetChosen = true;
					target = i;
				}
				else {
					tileList.add(new Tiles(x, y, false));
				}
			}



			if (i % 12 == 0) {
				x = 0;
				y += 50;
			}


			else {
				x += 50;
			}

			i++;

		}

		for (int f = 0; f < numFlies; f++) {
			flyList.add(new Flies());
			if (!isCurrentTileFree(flyList.get(f))) {
				flyList.remove(f);
				f--;
			}
		}
	}



	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		int i = 0;
		int f = 0;
		while(i < 108) {
			(tileList.get(i)).draw(g);

			i++;
		}

		while(f < numFlies) {
			(flyList.get(f)).draw(g);
			f++;
		}



	}

	public static ArrayList getTiles() {
		return tileList;
	}

	public static int thisTile(int x, int y) {
		if (x < 0 || y < 0 || x > 599 || y > 449) {
			return -1;
		}
		x = x - (x % Tiles.tileSize);
		y = y - (y % Tiles.tileSize);

		return (x / Tiles.tileSize) + (12 * y / Tiles.tileSize);
	}

	public boolean isPathFree(Flies fly) {
		int currentX = fly.x;
		int currentY = fly.y;
		if (fly.direction == 3 && (fly.x) % 50 == 0 && (fly.y + 1) % 50 == 0 ) {
			if (!isCurrentTileFreeForTiles(fly.x - 1, fly.y) || !isCurrentTileFreeForTiles(fly.x, fly.y + 1)) {
				fly.inCorner = true;
				return false;

			}
		}
		if (fly.direction == 4 && (fly.x + 1) % 50 == 0 && (fly.y + 1) % 50 == 0 ) {
			if (!isCurrentTileFreeForTiles(fly.x + 1, fly.y) || !isCurrentTileFreeForTiles(fly.x, fly.y + 1)) {
				fly.inCorner = true;
				return false;
			}
		}
		if (fly.direction == 1 && (fly.x + 1) % 50 == 0 && (fly.y) % 50 == 0 ) {
			if (!isCurrentTileFreeForTiles(fly.x + 1, fly.y) || !isCurrentTileFreeForTiles(fly.x, fly.y - 1)) {
				fly.inCorner = true;
				return false;
			}
		}
		if (fly.direction == 2 && (fly.x) % 50 == 0 && (fly.y) % 50 == 0 ) {
			if (!isCurrentTileFreeForTiles(fly.x, fly.y - 1) || !isCurrentTileFreeForTiles(fly.x - 1, fly.y)) {
				fly.inCorner = true;
				return false;
			}
		}


		fly.move(fly.direction);


		boolean free = isCurrentTileFree(fly);

		fly.x = currentX;
		fly.y = currentY;


		return free;
	}

	public static boolean isCurrentTileFree(Flies fly) {
		if (fly.x < 0 || fly.x > 596 || fly.y < 0 || fly.y > 446) {
			return false;
		}
		return (tileList.get(thisTile(fly.x, fly.y)).isTileWhite);

	}

	public boolean isCurrentTileFreeForTiles(int x, int y) {
		if (x < 0 || x > 599 || y < 0 || y > 449) {
			return false;
		}
		return (tileList.get(thisTile(x, y)).isTileWhite);

	}



	public void mousePressed(MouseEvent evt) {

		if (endGame) {
			return;
		}

		int x = evt.getX();  
		int y = evt.getY();

		if (!(tileList.get(thisTile(x,y)).isTarget)) {
			score++;
		}

		bottomPanel.currentScore.setText("Current Score: " + score);


		tileList.get(thisTile(x, y)).switchColor();
		repaint();



		int i = 0;
		while(i < numFlies) {
			if (tileList.get(flyList.get(i).thisTile()).equals(tileList.get(thisTile(x,y)))) {

				if (tileList.get(thisTile(x,y)).isTileWhite) {
					flyList.get(i).inWall = false;

				}
				else if (!(tileList.get(thisTile(x,y)).isTileWhite)) {
					flyList.get(i).inWall = true;

				}

			}

			i++;
		}



	} 


	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseReleased(MouseEvent evt) { }

	public static void setNumFlies(int n) {
		int diff = Math.abs(numFlies - n);

		if (n > numFlies) {
			while(diff > 0) {
				flyList.add(new Flies());
				while (!isCurrentTileFree(flyList.get(n - diff - 1))) {
					flyList.remove(n - diff - 1);
					flyList.add(new Flies());
				}
				diff --;
			}
		}

		if (n < numFlies) {
			while (diff > 0) {
				flyList.remove(n + diff - 1);
				diff --;
			}
		}

		numFlies = n; 
	}

	public static void setSpeedNum (int s) {
		speedNum = s;
	}

	public static void setWallColorNum(float colorNum) {

		int i = 0;
		while (i < 108) {
			tileList.get(i).setWallColor(colorNum);
			i++;
		}
	}

	

}



