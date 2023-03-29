package pl.vezyr.arkanoidgwt.client.helper;

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

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public T getY() {
		return y;
	}

	public void setY(T y) {
		this.y = y;
	}
	
	public void set(T x, T y) {
		this.x = x;
		this.y = y;
	}
}
