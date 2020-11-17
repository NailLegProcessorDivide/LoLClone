package game;

import graphics.Sprite;

public interface IItem{

	public Sprite genSprite(float xPos, float yPos);
	public int getMaxStack();
}
