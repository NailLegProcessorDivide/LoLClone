package game;

import java.util.ArrayList;
import java.util.HashMap;

import engine.Utils;
import graphics.Geometry;
import graphics.Sprite;
import graphics.SpriteStruct;
import graphics.Texture;
import physics.Collider;

/*
	MAP STRUCTURE:
	(isSolid) * 2^20 + (relativeLayer) * 2^16 + tileIndex
	actualLayer = relativeLayer + layerOffset

	,-srtuctName, xPos, yPos, layerOffset
	-srtuctName, atlasFile, fileDim
	xDim,yDim	
*/

public class Scene {
	private ArrayList<Sprite> background;
	private ArrayList<Sprite> foreground;
	private ArrayList<Collider> colliders;
	
	private int width, height;
	
	private Texture textureMap;
	
	private HashMap<String, SpriteStruct> spriteMap;
	
	private ArrayList<Sprite>[] actionGroups;
	
	@SuppressWarnings("unchecked")
	public Scene(String file) {
		background = new ArrayList<Sprite>();
		foreground = new ArrayList<Sprite>();
		colliders = new ArrayList<Collider>();
		actionGroups = new ArrayList[256];
		for(int i = 0; i < 256; i++) {
			actionGroups[i] = new ArrayList<Sprite>();
		}
		
		spriteMap = new HashMap<String, SpriteStruct>();
		try {
			ArrayList<String> lines = Utils.readAllLines(file);
			
			String[] fDesc = lines.get(0).split(",");
			textureMap = new Texture("res/tex/" + fDesc[0], toInt(fDesc[1]));
			
			String[] sizeDesc = lines.get(1).split(",");
			width = toInt(sizeDesc[0]);
			height = toInt(sizeDesc[1]);
			
			for(int y = 2; y < height + 2; y++) {
				String[] lineLayout = lines.get(y).split(",");
				for(int x = 0; x < lineLayout.length; x++) {
					Sprite tile = new Sprite(Geometry.quad(), textureMap);
					tile.setPosition(x, 2 - y);
					tile.setPointer(toInt(lineLayout[x]));
					background.add(tile);
				}
			}
			int offset = height + 2;
			while(offset < lines.size() && lines.get(offset).isEmpty()) {
				offset++;
			}
			if(offset == lines.size()) {
				return;
			}
			while(lines.get(offset).startsWith("-")) {
				fDesc = lines.get(offset).split(",");
				
				Texture tex = new Texture("res/tex/" + fDesc[1], toInt(fDesc[2]));
						
				sizeDesc = lines.get(offset + 1).split(",");
				int swidth = toInt(sizeDesc[0]);
				int sheight = toInt(sizeDesc[1]);
				
				SpriteStruct layoutDesc = new SpriteStruct(tex, toInt(sizeDesc[0]), toInt(sizeDesc[1]));
				
				for(int y = offset + 2; y < offset + sheight + 2; y++) {
					layoutDesc.addRow(lines.get(y));
				}
				spriteMap.put(fDesc[0], layoutDesc);
				offset += sheight + 2;

				if(offset >= lines.size())
					break;

				while(lines.get(offset).isEmpty()) 
					offset++;
			}
			while(offset < lines.size() && lines.get(offset).startsWith(",")) {
				String[] structDesc = lines.get(offset).split(",");
				for(Sprite sp: spriteMap.get(structDesc[1]).generate(toInt(structDesc[2]), toInt(structDesc[3]), toInt(structDesc[4]))) {
					foreground.add(sp);
				}
				for(Collider col: spriteMap.get(structDesc[1]).generateColliders(toInt(structDesc[2]), toInt(structDesc[3]))) {
					colliders.add(col);
				}
				for(int i = 0; i < 26; i++) {
					//System.out.println(spriteMap.get(structDesc[1]).getActionGroups()[i].size());
					for(Sprite sp: spriteMap.get(structDesc[1]).getActionGroups()[i]) {
						actionGroups[i].add(sp);
					}
				}
				offset++;

				if(offset >= lines.size())
					break;

				while(lines.get(offset).isEmpty()) {
					offset++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Sprite> getBackground() {
		return background;
	}

	public ArrayList<Sprite> getForeground() {
		return foreground;
	}

	public ArrayList<Collider> getColliders() {
		return colliders;
	}

	public ArrayList<Sprite>[] getActionGroups() {
		return actionGroups;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	private static int toInt(String o) {
		return Integer.parseInt(o);
	}
}
