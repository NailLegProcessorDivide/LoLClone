package graphics;

import java.util.ArrayList;

import physics.Collider;

public class SpriteStruct {
	
	private int[] subSprites;
	private Texture texture;
	private int width, height;
	private int activeRow;
	
	private ArrayList<Sprite>[] actionGroups;
	
	public SpriteStruct(Texture texture, int width, int height) {
		this.texture = texture;
		this.width = width;
		this.height = height;
		subSprites = new int[width * height];
		activeRow = 0;
		actionGroups = new ArrayList[256];
		for(int i = 0; i < 256; i++) {
			actionGroups[i] = new ArrayList<Sprite>();
		}
	}
	
	public void addRow(String rowData) {
		String[] rowParts = rowData.split(",");
		for (int i = 0; i < width; i++) {
			subSprites[i + activeRow * width] = Integer.valueOf(rowParts[i]);
		}
		activeRow++;
	}
	public ArrayList<Sprite> generate(int x, int y, int layerOffset) {
		actionGroups = new ArrayList[256];
		for(int i = 0; i < 256; i++) {
			actionGroups[i] = new ArrayList<Sprite>();
		}
		Mesh q = Geometry.quad();
		ArrayList<Sprite> out = new ArrayList<Sprite>();
		for (int i = 0; i < subSprites.length; i++) {
			Sprite sprite = new Sprite(q, texture);
			sprite.setPointer(subSprites[i] % 0x10000);
			sprite.setPosition(x + i%width, height - 1 - (y + i/width));
			sprite.setDepthLayer(((subSprites[i] >> 16) & 0xf) + layerOffset);
			out.add(sprite);
			actionGroups[(subSprites[i] >> 21)&0xff].add(sprite);
		}
		return out;
	}
	public ArrayList<Collider> generateColliders(int x, int y) {
		ArrayList<Collider> out = new ArrayList<Collider>();
		for (int i = 0; i < subSprites.length; i++) {
			if (((subSprites[i] >> 20) & 1) == 1) {
				out.add(new Collider(x + i % width, height - 1 - (y + i / width), (x + 1) + i % width, height - 1 - ((y + 1) + i / width)));
			}
		}
		return out;
	}
	public ArrayList<Sprite>[] getActionGroups(){
		return actionGroups;
	}
}
