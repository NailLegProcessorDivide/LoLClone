package graphics;

import game.Main;
import org.joml.Vector2d;
import org.joml.Vector2f;

import engine.Display;
import engine.GameObject;

public class Camera extends GameObject {

	private float minBright;
	private float maxBright;
	
	public Camera() {
		position = new Vector2f().zero();
		rotation = 0;
		minBright = 1;
		maxBright = 1;
	}

	public void follow(GameObject target, float maxDist) {
		if (target == null) return;

		Vector2f diff = new Vector2f(position.x - target.getPosition().x, position.y - target.getPosition().y);
		if(diff.length() > maxDist) {
			diff.normalize(maxDist);
			
			Vector2f newPos = new Vector2f((target.getPosition().x + diff.x), (target.getPosition().y + diff.y));
			position.lerp(newPos, 0.1f);

			setPosition(Math.round(position.x * scale) / scale,
						Math.round(position.y * scale) / scale);
		}
	}
	
	public void lockPosition(float left, float top, float right, float bottom, Display window) {
		position.x = Math.max(position.x, left + window.getWidth()/scale/2-0.5f);
		position.x = Math.min(position.x, right - window.getWidth()/scale/2-0.5f);
		position.y = Math.max(position.y, -bottom + window.getHeight()/scale/2+0.5f);
		position.y = Math.min(position.y, -top - window.getHeight()/scale/2+0.5f);
	}

	public float getMinBright() {
		return minBright;
	}

	public void setMinBright(float minBright) {
		this.minBright = minBright;
	}

	public float getMaxBright() {
		return maxBright;
	}

	public void setMaxBright(float maxBright) {
		this.maxBright = maxBright;
	}

	public Vector2f screenToWorld(Vector2f screenPos) {
		Vector2f outVec = new Vector2f(screenPos.x-Main.WIDTH/2, -screenPos.y + Main.HEIGHT/2);
		outVec.div(scale, outVec);
		outVec.add(position.x, position.y);
		return outVec;
	}
}
