package game;

import java.util.ArrayList;

import graphics.Geometry;
import graphics.Sprite;
import graphics.Texture;

public class TextBox {

	private ArrayList<String> lines;
	private ArrayList<Sprite> baseSprites;
	private ArrayList<Sprite> text;

	private Texture background;
	private Texture font;
	
	private int width;
	private int height;
	
	public TextBox(int width, int height) {
		background = new Texture("res/tex/infoBox.png", 4);
		font = new Texture("res/tex/asciiWhite.png", 16);
		lines = new ArrayList<String>();
		baseSprites = new ArrayList<Sprite>();
		text = new ArrayList<Sprite>();
		
		this.width = width;
		this.height = height;
		
		if(height != 1) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					Sprite slot = new Sprite(Geometry.quad, background);
					slot.setPosition(j, -i);
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
					baseSprites.add(slot);
				}
			}
		}
		else {
			for(int j = 0; j < 5; j++) {
				Sprite slot = new Sprite(Geometry.quad, background);
				slot.setPosition(j, 0);
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
				baseSprites.add(slot);
			}
		}
	}
	
	public void addChar(char data) {
		int x = lines.get(lines.size()-1).length();
		Sprite sp = new Sprite(Geometry.quad, font);
		sp.setPointer(data);
		sp.setPosition(x, -lines.size());
		sp.setDepthLayer(9);
		text.add(sp);
		x++;
		lines.set(lines.size()-1, lines.get(lines.size()-1)+data);
	}
	
	public void addLine(String data) {
		int x = 0;
		for(char c : data.toCharArray()) {
			Sprite sp = new Sprite(Geometry.quad, font);
			sp.setPointer(c);
			sp.setPosition(x, -lines.size());
			sp.setDepthLayer(9);
			text.add(sp);
			x++;
		}
		lines.add(data);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Sprite> getSprites() {
		return baseSprites;
	}

	public ArrayList<Sprite> getText() {
		return text;
	}
}
