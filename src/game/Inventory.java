package game;

import java.util.ArrayList;

import graphics.Geometry;
import graphics.Sprite;
import graphics.Texture;

public class Inventory {
	private static int width = 5;
	private static int height = 3;
	private ItemStack[] slots;
	private ArrayList<Sprite> slotSprites;
	private Texture tex;
	
	public Inventory(int width, int height, float xPos, float yPos) {
		tex = new Texture("res/tex/inv.png", 4);
		slots = new ItemStack[width*height];
		for(int i = 0; i < slots.length; i++) {
			slots[i] = new ItemStack();
		}
		slotSprites = new ArrayList<Sprite>();
		if(height != 1) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					Sprite slot = new Sprite(Geometry.quad, tex);
					slot.setPosition(j+xPos, -i+yPos);
					int pointer = 0;
					if(i == 0) {
						pointer += 0;
					}
					else if(i == height-1) {
						pointer += 8;
					}
					else {
						pointer += 4;
					}
					if(j == 0) {
						pointer += 0;
					}
					else if(j == width-1) {
						pointer += 2;
					}
					else {
						pointer += 1;
					}
					slot.setPointer(pointer);
					slot.setDepthLayer(8);
					slotSprites.add(slot);
				}
			}
		}
		else {
			for(int j = 0; j < 5; j++) {
				Sprite slot = new Sprite(Geometry.quad, tex);
				slot.setPosition(j+xPos, yPos);
				int pointer = 12;
				if(j == 0) {
					pointer += 0;
				}
				else if(j == width-1) {
					pointer += 2;
				}
				else {
					pointer += 1;
				}
				slot.setPointer(pointer);
				slot.setDepthLayer(8);
				slotSprites.add(slot);
			}
		}
	}

	public ArrayList<Sprite> getSlotSprites() {
		return slotSprites;
	}
}
