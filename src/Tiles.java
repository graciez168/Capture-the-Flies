import java.awt.Color;
import java.awt.Graphics;



public class Tiles {

	boolean isTileWhite;
	boolean isTarget;
	static boolean targetChosen = false;
	static int targetX;
	static int targetY;
	Color color;
	int x;
	int y;
	static int tileSize = 50;
	static float wallColorNum = SideBar.initialWallColor;

	public Tiles(int x, int y, boolean isTarget) {

		this.isTarget = isTarget;

		if (isTarget) {
			if (!targetChosen) {
				targetX = x;
				targetY = y;
				targetChosen = true;
			}
			
				
			color = Color.GREEN;
		
			this.x = x;
			this.y = y;

			isTileWhite = true;
		}


		else {

			int num = (int)(Math.random() * 10) + 1;
			if (num < 10) {
				isTileWhite = true;
				color = Color.WHITE;
			}
			else {
				isTileWhite = false;
				color = Color.getHSBColor(wallColorNum, 0.8F, 0.8F);
			}

			this.x = x;
			this.y = y;
		}
	}


	public void setColor(int r, int g, int b) {
		color = new Color(r,g,b);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getTileColor() {
		return isTileWhite;
	}

	public void setWallColor(float h) {
		wallColorNum = h / 360;
		if (!isTileWhite) {
			color = Color.getHSBColor(h / 360,0.8F,0.8F);
		}
	}

	public void switchColor() {
		if (!isTarget) {

			if (!isTileWhite) {
				color = Color.WHITE;
				isTileWhite = true;
			}
			
			else {
				color = Color.getHSBColor(wallColorNum, 0.8F, 0.8F);
				isTileWhite = false;
			}
		}

		if (isTarget) {
			return;
		}
	}
	
	

	public void draw (Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, tileSize, tileSize);

	}


}
