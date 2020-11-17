package game;

import java.util.ArrayList;

import graphics.Camera;
import graphics.RenderBlock;

public class DialogueWorldState  implements IGameState{
	
	private IGameState backGround;
	private String[][] strings;
	private ArrayList<RenderBlock> ui = new ArrayList<RenderBlock>();
	private TextBox tb = new TextBox(10, 3);
	private int index = 0;
	private int page = 0;
	private int x = 0;
	private int y = 0;
	private Camera textbgCam = new Camera();
	private Camera textCam = new Camera();
	private boolean pageDone = true;
	
	@Override
	public void initState(Game game) {
		// TODO Auto-generated method stub
		
		textbgCam = new Camera();
		textbgCam.setScale(96);
		textbgCam.setMinBright(1);
		textbgCam.setMaxBright(1);
		textbgCam.setPosition(4.5f, 0);
		
		textCam = new Camera();
		textCam.setScale(24);
		textCam.setMinBright(1);
		textCam.setMaxBright(1);
		textCam.setPosition(18, 0);
		tb.addLine("");
		ui.add(new RenderBlock(textbgCam, tb.getSprites()));
		ui.add(new RenderBlock(textCam, tb.getText()));
		page = 0;
		x = 0;
		y = 0;
	}

	@Override
	public void loadState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playState(float elapsedTime, Game game) {
		if(!pageDone) {
			if(strings.length > page) {
				if(strings[page].length > y) {
					if(strings[page][y].length() > x) {
						tb.addChar(strings[page][y].charAt(x));
						x++;
					}
					else {
						tb.addLine("");
						y += 1;
						x = 0;
					}
				}
				else {
					y = 0;
					x = 0;
					pageDone = true;
				}
			}
			else {
				initState(game);
				game.setGameState(backGround);
			}
		}
		else {
			if(game.isSpacePressed()) {
				tb = new TextBox(10, 3);
				tb.addLine("");
				ui = new ArrayList<RenderBlock>();
				ui.add(new RenderBlock(textbgCam, tb.getSprites()));
				ui.add(new RenderBlock(textCam, tb.getText()));
				page++;
				pageDone = false;
				//System.out.println(x+" "+y+" "+page+" ");
			}
		}
		
	}

	@Override
	public void render(Renderer renderer) {
		
		backGround.render(renderer);
		for(RenderBlock rb : ui) {
			renderer.render(rb.camera, rb.sprites, false);
		}
	}

	public IGameState getBackGround() {
		return backGround;
	}

	public void setBackGround(IGameState backGround) {
		this.backGround = backGround;
	}

	public void loadText(String[][] strings) {
		this.strings = strings;
		pageDone = !(strings.length > 0 && strings[0].length > 0 && strings[0][0].length() > 0);
	}
}
