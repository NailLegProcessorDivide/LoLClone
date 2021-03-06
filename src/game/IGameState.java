package game;

import input.Keyboard;
import input.Mouse;

public interface IGameState {
	public void initState(Game game);
	public void loadState();
	public void playState(float elapsedTime, Game game);
	public void render(Renderer renderer);
}
