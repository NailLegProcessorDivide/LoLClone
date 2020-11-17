package game;

import physics.Collider;

public interface IInteractable {
	public void interact(Game game);
	public Collider getCollider();
}
