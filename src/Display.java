//the going px to meter ratio is 75

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Display extends JPanel implements ActionListener{
	//ACCURACY notes how accurate the central int value is to realtime and absolute location:
	//	a low ACCURACY value means less accuracy, and therefore "choppy," less-absolute measurements
	private final int ACCURACY;
	private final int windowSize/* = 1000*/;
	private Vector2D vector;
	private Engine game;
	private int count;
	private double theta;
	private double magnitude;
	private long starttime;
	public Display(int fidelity, int size){
		
		windowSize = size;
		game = new Engine(fidelity);
		game.addActionListener(this);
		game.setMatrix(new Entity[windowSize][windowSize]);
		ACCURACY = fidelity;
		count = 0;
		theta = 0;
		magnitude = 0;
		starttime = System.currentTimeMillis();
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
            	if (game.getState() == STATE.SELECTING){
            		game.run(vector);
            	}
            	else if (game.getState() == STATE.RUNNING){
            		game.pause();
            	}
            	else if (game.getState() == STATE.STOPPED){
            		game.unpause();
            	}
                repaint();
            }
        });
	}
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		setBackground(Color.WHITE);
		g2.setColor(Color.BLACK);
		g2.drawString("p: " + game.getPoint().x + ", " + game.getPoint().y, windowSize - 70, windowSize - 50);
		g2.drawLine(0, windowSize, windowSize, windowSize);
//		for(int i = 0; i < )
		if (game.getState() == STATE.SELECTING){
			count = (int)(System.currentTimeMillis() - starttime) / 10 + 1;
			double temp = (180 / Math.PI) / ACCURACY * count;
			theta = Math.PI / 4 + Math.PI * (Math.sin(temp)) / 4;
			magnitude = 200 * Math.sin(temp) * Math.sin(temp) * Math.cos(temp) * Math.cos(temp);
			double y1 = windowSize - game.getPoint().y;
			double x1 = game.getPoint().x;
			double y2 = y1 - magnitude * Math.cos(theta);
			double x2 = x1 + magnitude * Math.sin(theta);
            vector = new Vector2D(magnitude * Math.cos(theta), magnitude * Math.sin(theta));
            vector.scale(5);
            g2.drawLine((int)x1, (int)y1, (int)x2 , (int)y2);
			g2.drawLine((int)x1, (int)y1, (int)x1, (int)y2);
//			g2.drawString("" + theta, 200, 250);
//			g2.drawString("" + magnitude, 200, 280);
//			g2.drawString("" + vector, 200, 310);
		}
		else if (game.getState() == STATE.RUNNING){
			Point p1 = game.calculatePoint(g2);
			Point p = new Point(p1);
			int x, y; boolean isdrawable = true;
//			if (p.y < windowSize/ 10)
				y = windowSize - p.y - 3;
//			else if (p.y <= windowSize)
//				y = windowSize - windowSize / 10;
//			else /*if (p.y > windowSize)*/
//			{
//				y = 0;
//				isdrawable = false;
//			}
//			if (p.x > windowSize / 2)
//				x = windowSize / 2;
//			else
				x = p.x;
			if (isdrawable){
				g2.drawOval(x, y, 3, 3);
			}
			//g2.drawString("p: " + game.getPoint().x + ", " + game.getPoint().y, windowSize - 70, windowSize - 50);
//			g2.drawString("w: " + game.getVelocity().getWidth(), windowSize - 70, windowSize - 30);
//			g2.drawString("h: " + game.getVelocity().getHeight(), windowSize - 70, windowSize - 10);
		}
		else if (game.getState() == STATE.STOPPED);
	}	
}