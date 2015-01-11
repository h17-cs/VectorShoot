 public class Vector2D{
	public static final Vector2D ZERO = new Vector2D(0.0, 0.0);
	private double x, y;
	public Vector2D(double height, double width){
		y = height;
		x = width;
	}
	public double getHeight(){
		return y; 
	}
	public double getWidth(){
		return x;
	}
	public double getMagnitude(){
		return Math.sqrt(x * x + y * y);
	}
	public double compare2D(Vector2D other){
		Vector2D a = this.clone();
		Vector2D b = other.clone();
		Vector2D c = a.clone().add(b.clone());
		Vector2D d = (c.clone());
		d.scale((a.dot(c))/(c.dot(c)));
		d.add(a);
		return d.getMagnitude() * c.getMagnitude() / 2;
	}
	public double scaleHeight(double factor){
		return (y *= factor);
	}
	public double scaleWidth(double factor){
		return (x *= factor);
	}
	public Vector2D scale(double factor){
		scaleHeight(factor);
		scaleWidth(factor);
		return this;
	}
	public Vector2D add(Vector2D other){
		//Vector2D temp = clone();
		x += other.x;
		y += other.y;
		return this;
		//return temp;
	}
	public Vector3D add(Vector3D other){
		return other.add(this);
	}
	public double dot(Vector2D other){
		return x * other.x + y * other.y;
	}
	public double dot(Vector3D other){
		return other.dot(this);
	}
	public Vector3D cross(Vector2D other){
		return clone3D().cross(other.clone3D());
	}
	public Vector3D cross(Vector3D other){
		return other.cross(this);
	}
	public Vector2D clone(){
		return new Vector2D(y, x);
	}
	public Vector3D clone3D(){
		return new Vector3D(y, x, 0);
	}
	public String toString(){
		return "<" + ((int)(x * 1000))/(double) 1000 + ", " + ((int)(y * 1000))/(double) 1000 + ">";
	}
	public static Vector2D add(Vector2D a, Vector2D b){
		return new Vector2D(a.getHeight() + b.getHeight(), a.getWidth() + b.getWidth());
	}
}
