package LeagueBits;

import java.util.ArrayList;

import game.Game;
import org.joml.Vector2d;
import org.joml.Vector2f;
import physics.Collider;

public class Player implements Updateable {

	private Champion campion;
	
	public Player(Champion champ) {
		campion = champ;
	}

	public void update(float elapsedTime, Game game, ILeagueGameState gs) {
		if (game.isRighted()) {
			System.out.println("rclick");
			Vector2d ntarget = game.getMousePos();

			getChamp().setTarget(gs.getCam().screenToWorld(new Vector2f((float) ntarget.x, (float) ntarget.y)));
		}
	}

	public Champion getChamp() {
		return campion;
	}
	
}
