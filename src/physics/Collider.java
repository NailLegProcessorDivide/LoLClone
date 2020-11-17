package physics;

import org.joml.Vector2f;

public class Collider {
	private float x1, y1, x2, y2;
	
	public Collider (float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean intersects(Collider col) {
		return intersectsX(col) && intersectsY(col);
	}
	public boolean intersectsY(Collider col) {
		return y2 < col.getY1() && y1 > col.getY2();
	}
	public boolean intersectsX(Collider col) {
		return x1 < col.getX2() && x2 > col.getX1();
	}

	public float getX1() {
		return x1;
	}

	public float getY1() {
		return y1;
	}

	public float getX2() {
		return x2;
	}

	public float getY2() {
		return y2;
	}
	public void translate(Vector2f v2) {
		x1 += v2.x;
		x2 += v2.x;
		y1 += v2.y;
		y2 += v2.y;
	}
}
