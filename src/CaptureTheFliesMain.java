import javax.swing.JFrame;
import javax.swing.JPanel;



public class CaptureTheFliesMain {
		
		static CaptureTheFliesPanel gamePanel;
		static BottomBar bottomPanel;
		static SideBar sidePanel;
		static JFrame window;
		
	public static void main(String[] args) {
		
		
		window = new JFrame("Capture the Flies");
		int windowX = 600 + 200 + 16;
		int windowY = 450 + 50 + 38;
		window.setSize(windowX, windowY);
		window.setLocation(100,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel = new CaptureTheFliesPanel();
		gamePanel.setLocation(0,0);
		gamePanel.setSize(800, 500);
		window.setContentPane(gamePanel);
		window.setVisible(true);

		
	}
	
	public static void startNewGame() {
		JPanel content = new JPanel();
		content.setLayout(null);
		gamePanel = new CaptureTheFliesPanel();
		bottomPanel = new BottomBar();
		sidePanel = new SideBar();
		
		content.add(gamePanel);
		gamePanel.setLocation(0,0);
		gamePanel.setSize(600, 450);
				
				content.add(bottomPanel);
				bottomPanel.setLocation(0,450);
				bottomPanel.setSize(600, 50);
				
				
				content.add(sidePanel);
				sidePanel.setLocation(600, 0);
				sidePanel.setSize(200, 500);
	}
	
	
	
	
}
