package game;

import physics.Collider;

public class ChatBoxInteractor implements IInteractable {
	private IGameState nextState;
	private Collider collider;
	public ChatBoxInteractor(float x, float y, float x2, float y2, IGameState gameState) {
		nextState = gameState;
		collider = new Collider(x, y, x2, y2);
	}
	
	@Override
	public void interact(Game game) {
		System.out.println("chat interacted");
		nextState.initState(game);
		nextState.loadState();
		game.setGameState(nextState);
	}
	
	@Override
	public Collider getCollider() {
		return collider;
	}
}
