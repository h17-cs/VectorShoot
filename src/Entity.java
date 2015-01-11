import java.awt.Point;
import java.util.ArrayList;

public abstract class Entity{
	//occupied space is a square matrix of actual points that the image occupies
	boolean[][] occupied;
	double mass, energyTolerance;
	ArrayList<Vector2D> first_order_vectors, second_order_vectors;
	public Entity(boolean[][] space){
		occupied = space;
	}
	public void placeSelfInMatrix (Entity[][] matrix, Point origin){
		for (int i = origin.y; i < origin.y + occupied.length; i++)
			for (int j = origin.x; j < origin.x + occupied[0].length; j++)
				if (occupied[i - origin.y][j - origin.x])
						matrix[i][j] = this;
	}
	public double getMass(){
		return mass;
	}
	public Vector2D getVelocity(){
		Vector2D velocity = new Vector2D(0, 0);
		for(Vector2D vec : first_order_vectors){
			velocity.add(vec);
		}
		return velocity;
	}
	public ArrayList<Vector2D> getFirstOrderVectors(){
		return first_order_vectors;
	}
	public ArrayList<Vector2D> getSecondOrderVectors(){
		return second_order_vectors;
	}
	public void addFirstOrderVector(Vector2D newvec){
		first_order_vectors.add(newvec);
	}
	public void addSecondOrderVector(Vector2D newvec){
		second_order_vectors.add(newvec);
	}
	public double getMomentum(){
		Vector2D vel = getVelocity();
		return vel.getMagnitude() * mass;
	}
	public double getKineticEnergy(){
		Vector2D vel = getVelocity();
		return vel.getMagnitude() * vel.getMagnitude() * 0.5 * mass;
	}
	public double getTolerance(){
		return energyTolerance;
	}
}
