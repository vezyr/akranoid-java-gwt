package pl.vezyr.arkanoidgwt.client.helper;

/**
 * Helper class to represent the pair of Numbers,
 * ie. the coordinates of the point on plane.
 * @author vezyr
 *
 * @param <T> Any class that extends Number
 */
public class Vector2<T extends Number> {

	private T x;
	private T y;
	
	public Vector2() { 
		this(null, null);
	}
	
	public Vector2(T x, T y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Return the first value of the pair.
	 * @return T First value.
	 */
	public T getX() {
		return x;
	}

	/**
	 * Sets the first value of the pair.
	 * @param T Value to set.
	 */
	public void setX(T x) {
		this.x = x;
	}

	/**
	 * Return the second value of the pair.
	 * @return T second value.
	 */
	public T getY() {
		return y;
	}

	/**
	 * Sets the second value of the pair.
	 * @param T Value to set.
	 */
	public void setY(T y) {
		this.y = y;
	}
	
	/**
	 * Sets the first and the second value of the pair.
	 * @param x T Value to set to first value.
	 * @param y T Value to set to second value.
	 */
	public void set(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("Vector2(").append(x).append(", ").append(y).append(")");
		return result.toString();
	}
}
