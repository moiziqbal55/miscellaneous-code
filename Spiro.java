import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Spiro {
	public static void main(String[] args) {
		PFrame pframe = new PFrame();
	}
}

class PFrame extends JFrame {
	private PCanvas canvas;	
	PFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setUndecorated(true);
		canvas = new PCanvas(screenSize.width, screenSize.height);
		add(canvas);
		setVisible(true);	
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
						System.exit(0);
				}
			}
		});	
	}
	
	public PCanvas getCanvas() {
		return canvas;
	}
}

class PCanvas extends Canvas {
	private int x;
	private int y;
	public static int SIZE_X;
	public static int SIZE_Y;
	
	private double R1 = 157;
	private double R2 = 231;
	private double R3 = 133;
	
	private double RA1 = 223;
	private double RA2 = 446;
	
	private BufferedImage img;
	PCanvas(int sizex, int sizey) {
		SIZE_X = sizex;
		SIZE_Y = sizey;
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
		setBackground(Color.black);		
		setForeground(Color.white);	

		
	}
	public void paint(Graphics g) {		
	
	for (double theta1 = 0 ; theta1 < 360; theta1+=.001) {
			double theta2=RA1 * theta1;
			double theta3=RA2 * theta1;
			
			
			
			g.fillOval(SIZE_X/2 - (int)(R1 * Math.cos(Math.toRadians(theta1)) 
										- R2 * Math.cos(Math.toRadians(theta2)))
										//+ R3 * Math.cos(Math.toRadians(theta3)))										
										
						,SIZE_Y/2 - (int)(R1 * Math.sin(Math.toRadians(theta1)) 
										- R2 * Math.sin(Math.toRadians(theta2)))
										//+ R3 * Math.sin(Math.toRadians(theta3))) 
										
						,1 
						,1);
		}
	
	}
	
}
