 import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

enum STATE{SELECTING, RUNNING, STOPPED;}
public class Engine{
	public static final int RATIO = 100 /*pixels per meter*/;
	public static final double SCALETIME = 1000.0 /*milliseconds per second*/;
	public static final Vector2D GRAVITY = new Vector2D(-9.8, 0);
	private STATE state;
	private long last_update;
	private Point p;
	private Vector2D velocity;
	private Entity[][] matrix;
	private Timer t;
	
	public Engine(int fidelity){
		state = STATE.SELECTING;
		p = new Point(0,10);
        t = new Timer(10000 / fidelity, null);
        last_update = System.currentTimeMillis();
	}
	public void addActionListener(ActionListener al){
		t.addActionListener(al);
        t.start();
	}
	public void setMatrix(Entity[][] newM){
		matrix = newM;
	}
	public STATE getState(){
		return state;
	}
	public void run(Vector2D initialVelocity){
		state = STATE.RUNNING;
		velocity = initialVelocity;
	}
	public void stop(){
		state = STATE.SELECTING;
		p = new Point(0, 10);
		velocity = null;
		t.stop();
	}
	public void pause(){
		state = STATE.STOPPED;
		t.stop();
	}
	public void unpause(){
		stop();
		t.start();
	}
	public Point getPoint(){
		return p;
	}
	public Entity[][] getMatrix(){
		return matrix;
	}
	public Vector2D getVelocity(){
		return velocity;
	}
	public Point calculatePoint(Graphics2D g2){
		if (getState() == STATE.RUNNING){
			long now = System.currentTimeMillis();
			double instance_scale = RATIO * /*(now - last_update)*/ 1/ SCALETIME;
			velocity.scale(instance_scale);
			Vector2D g1 = GRAVITY.clone();
			g1.scale(instance_scale);
			velocity.add(g1);
//			g2.drawString("" + instance_scale, 200, 250);
//			g2.drawString("" + velocity, 200, 280);
//			g2.drawString("" + System.currentTimeMillis(), 200, 310);
//			g2.drawString("", 200, 320);
			p.translate((int)(velocity.getWidth()), (int)(velocity.getHeight()));
			if (p.x >= 0 && p.y < 0){
				p.y = 0;
				velocity.scaleHeight(-1);
				velocity.scale(.66);
			}
			velocity.scale(1 / instance_scale);
			if (velocity.compare2D(Vector2D.ZERO) < .01)
				pause();
			last_update = now;
//			g2.drawString("" + velocity, 200, 320);
			return p;
		}
		else return p;
	}
	public static void main(String[]args){
    	JFrame frame = new JFrame();
    	
        frame.setContentPane(new Display(2500, 500));
        frame.setSize(550, 550);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
