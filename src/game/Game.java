package game;

import LeagueBits.RiftGameState;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import engine.Display;
import engine.IGameLogic;
import input.Keyboard;
import input.Mouse;
import sound.SoundManager;

public class Game implements IGameLogic {

	private final Renderer renderer;
	
	private Display window;
	
	private SoundManager soundManager;

	private IGameState gameState;
	
	private boolean[] wasdInput;
	private boolean tab;
	private boolean usePressed;
	private boolean spacePressed;
	private boolean leftClick;
	private boolean rightClick;
	private Vector2d mousePos;

	public Game() {
		renderer = new Renderer();
		gameState = new RiftGameState();
		soundManager = new SoundManager();
	}

	@Override
	public void init(Display window) throws Exception {
		this.window = window;
		renderer.init(window);
		gameState.initState(this);
		gameState.loadState();
	}

	@Override
	public void input(float interval, Keyboard keyboard, Mouse mouse) {
		wasdInput = new boolean[] { 
			keyboard.keyDown(GLFW.GLFW_KEY_W), 
			keyboard.keyDown(GLFW.GLFW_KEY_S), 
			keyboard.keyDown(GLFW.GLFW_KEY_A), 
			keyboard.keyDown(GLFW.GLFW_KEY_D), 
		};

		tab = keyboard.keyDown(GLFW.GLFW_KEY_TAB);
		usePressed = keyboard.keyDown(GLFW.GLFW_KEY_E);
		spacePressed = keyboard.keyDown(GLFW.GLFW_KEY_SPACE);

		leftClick = mouse.isLeftButtonDown();
		rightClick = mouse.isRightButtonDown();
		mousePos = mouse.getCurrentPos();
	}
	
	@Override
	public void update(float interval) {
		gameState.playState(interval, this);
	}

	@Override
	public void render() {
		gameState.render(renderer);
		
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		soundManager.cleanup();
	}

	public boolean[] getWasdInput() {
		return wasdInput;
	}

	public boolean isTab() {
		return tab;
	}

	public boolean isLeftClick() {
		return leftClick;
	}
	public boolean isRighted() {
		return rightClick;
	}
	public Vector2d getMousePos() { return mousePos; }

	public boolean isUsePressed() {
		return usePressed;
	}

	public boolean isSpacePressed() {
		return spacePressed;
	}

	public void setGameState(IGameState gameState) {
		this.gameState = gameState;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}

	public Display getWindow() {
		return window;
	}
	

}