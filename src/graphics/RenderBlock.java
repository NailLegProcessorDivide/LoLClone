package graphics;

import java.util.ArrayList;

public class RenderBlock {
	public Camera camera;
	public ArrayList<Sprite> sprites;
	
	public RenderBlock() {}
	
	public RenderBlock(Camera cam, ArrayList<Sprite> sp) {
		camera = cam;
		sprites = sp;
	}
}
