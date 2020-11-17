package game;

import physics.Collider;

public class LevelTransition implements IInteractable {
	private IGameState nextState;
	private Collider collider;
	public LevelTransition(float x, float x2, float y, float y2, IGameState gameState) {
		nextState = gameState;
		collider = new Collider(x, y, x2, y2);
	}
	
	@Override
	public void interact(Game game) {
		nextState.initState(game);
		nextState.loadState();
		game.setGameState(nextState);
	}
	
	@Override
	public Collider getCollider() {
		return collider;
	}
}
