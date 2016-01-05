
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
//trailing color fading stuff


public class Flies {
	int flySize;
	int x;
	int y;
	boolean inWall = false;
	int direction;
	boolean freeFlying;
	int speed;
	boolean inCorner;
	
	public Flies() {
		
		flySize = 4;
		
		x = (int)(Math.random() * 600) + 1;
		y = (int)(Math.random() * 450) + 1;
		
		
		
		direction = (int)(Math.random() * 4) + 1;
		//each number is associated with a quadrant in 
		//xy plane so, 1 is in the northeast, 2 is northwest etc.
		
		
		
	}
	
	
	public int thisTile() {
		int tileX = x - (x % 50);
		int tileY = y - (y % 50);
		
		
		return (tileX / 50) + (12 * tileY / 50);
	}
	
	public int nextTile() {
		int currentX = x;
		int currentY = y;
		this.move(direction);
		int nextX = x;
		int nextY = y;
		x = currentX;
		y = currentY;
		
		nextX = nextX - (nextX % 50);
		nextY = nextY - (nextX % 50);
		
		return (nextX / 50) + (12 * nextY / 50);
	}
	
	public boolean isInTarget() {
		if (x >= Tiles.targetX && x<= Tiles.targetX + 150 - 4 &&
				y >= Tiles.targetY && y <= Tiles.targetY + 150 - 4) {
			return true;
		}
		return false;
	}
	
	public void switchDirection() {
		
		
		switch(direction) {
		case 1:
			if (inCorner) {
				direction = 3;
				inCorner = false;
			}
			if (x >= 596) {
				direction = 2;
				break;
			}
			if (y <= 4) {
				direction = 4;
				break;
			}
			int currentX = x;
			int currentY = y;
			this.move(2);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 2;
				
			x = currentX;
			y = currentY;
			
			currentX = x;
			currentY = y;
			this.move(4);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 4;
			x = currentX;
			y = currentY;
			
			break;
			
		case 2:
			if (inCorner) {
				direction = 4;
				inCorner = false;
			}
			if (x <= 4) {
				direction = 1;
				break;
			}
			if (y <= 4) {
				direction = 3;
				break;
			}
			currentX = x;
			currentY = y;
			this.move(1);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 1;
			x = currentX;
			y = currentY;
			
			currentX = x;
			currentY = y;
			this.move(3);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 3;
			x = currentX;
			y = currentY;

			break;
			
		case 3:
			if (inCorner) {
				direction = 1;
				inCorner = false;
			}
			if (y >= 446) {
				direction = 2;
				break;
			}
			if (x <= 4) {
				direction = 4;
				break;
			}
			currentX = x;
			currentY = y;
			this.move(2);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 2;
			x = currentX;
			y = currentY;
			
			currentX = x;
			currentY = y;
			this.move(4);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 4;
			x = currentX;
			y = currentY;

			break;
			
		case 4:
			if (inCorner) {
				direction = 2;
				inCorner = false;
			}
			if (x >= 596) {
				direction = 3;
				break;
			}
			if (y >= 446) {
				direction = 1;
				break;
			}
			currentX = x;
			currentY = y;
			this.move(1);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 1;
			x = currentX;
			y = currentY;
			
			currentX = x;
			currentY = y;
			this.move(3);
			if (CaptureTheFliesPanel.isCurrentTileFree(this)) 
				direction = 3;
			x = currentX;
			y = currentY;
			break;
		}		
	}
	
	public void move(int direction) {
		switch(direction) {
		
		case 1:
			x ++;
			y --;
			break;
			
		case 2:
			x --;
			y --;
			break;
			
		case 3:
			x --;
			y ++;
			break;
			
		case 4:
			x ++;
			y ++;
			break;
	
			
		}
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, flySize, flySize);
	}
}
