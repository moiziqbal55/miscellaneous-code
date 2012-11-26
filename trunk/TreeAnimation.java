/**
 * Copyright © 2011 Parag Patil
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * You may not use this file except in compliance with  Apache License, Version 2.0
 * You may obtain a copy of the license at
 * http://www.apache.org/licenses/LICENSE-2.0
 
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TreeAnimation {
   public static final int SIZE_X = 1680;
   public static final int SIZE_Y = 1050;
   public static void main(String[] args) {
		
		PFrame pframe = new PFrame();			
		pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						
		PCanvas c = new PCanvas((int)pframe.getBounds().getWidth(),(int)pframe.getBounds().getHeight());
		pframe.add(c);
		pframe.setVisible(true);
    }
}
class PFrame extends JFrame {
	public void paint(Graphics g) {
		System.out.println("PFrame paint called");	
	}
	PFrame() {
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width, screenSize.height);
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0, false);
		Action escapeAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke,"ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}
}
class PCanvas extends Canvas {
	public static final double TRIM_FACTOR = 0.80;
	public static int SIZE_X;
	public static int SIZE_Y;
	public static final double INITIAL_LENGTH = 180;
	public static final double EXIT_LENGTH = 10;
	public static final double BRANCH_ANGLE = Math.PI / 9.0;
	public static final int WAIT = 15;
	public void paint(Graphics g) {
		drawLine2(g, SIZE_X / 2, 0, INITIAL_LENGTH, Math.PI / 2);
	}
	PCanvas(int sizex, int sizey) {
		SIZE_X = sizex;
		SIZE_Y = sizey - sizey / 20;
		setBackground(Color.black);
		setForeground(Color.white);
	}	
	public void drawLine2(Graphics g, double x1, double y1, double l, double theta) {
		
		if (l < EXIT_LENGTH) {
			return;
		}
		double x2 = x1 + l * Math.cos(theta);
		double y2 = y1 + l * Math.sin(theta);
		
		
		g.drawLine((int)x1, (int)(SIZE_Y - y1), (int)x2, (int)(SIZE_Y - y2));

		drawLine2(g, x2, y2, l * TRIM_FACTOR, theta - BRANCH_ANGLE);
		drawLine2(g, x2, y2, l * TRIM_FACTOR, theta + BRANCH_ANGLE);
	}
	
}