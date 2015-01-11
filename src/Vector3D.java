
public class Vector3D
{
	double x, y, z;
	public Vector3D(double height, double width, double depth)
	{
		y = height;
		x = width;
		z = depth;
	}
	public double getHeight()
	{
		return y;
	}
	public double getWidth()
	{
		return x;
	}
	public double getDepth()
	{
		return z;
	}
	public double getMagnitude()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	public double scaleHeight(double factor)
	{
		return (y *= factor);
	}
	public double scaleWidth(double factor)
	{
		return (y *= factor);
	}
	public double scaleDepth(double factor)
	{
		return (z *= factor);
	}
	public double scale(double factor)
	{
		x *= factor;
		y *= factor;
		z *= factor;
		return getMagnitude();
	}
	public Vector3D add(Vector3D other)
	{
		double temp1 = x, temp2 = y, temp3 = z;
		x += other.x;
		y += other.y;
		z += other.z;
		return new Vector3D(temp1, temp2, temp3);
	}
	public Vector3D add(Vector2D other)
	{
		return add(other.clone3D());
	}
	public double dot(Vector3D other)
	{
		return x * other.x + y * other.y + z * other.z;
	}
	public double dot(Vector2D other)
	{
		return dot(other.clone3D());
	}
	public Vector3D cross(Vector3D other)
	{
		double i = y * other.z - z * other.y;
		double j = -(x * other.z - z * other.x);
		double k = x * other.y - y * other.x;
		return new Vector3D(i, j, k);
		
	}
	public Vector3D cross(Vector2D other)
	{
		return cross(other.clone3D());
	}
	public Vector3D clone()
	{
		return new Vector3D(x, y, z);
	}
	

}
