package LeagueBits;

import java.util.ArrayList;

import game.Game;
import game.Killable;
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
			//System.out.println("rclick");
			Vector2d ntarget = game.getMousePos();

			Vector2f worldClickPos = gs.getCam().screenToWorld(new Vector2f((float) ntarget.x, (float) ntarget.y));

			boolean enemy = false;
			//System.out.println("nkbl: " + gs.getKillable().size());
			for(Killable k : gs.getKillable()) {
				//System.out.println("dist: " + k.getDistance(worldClickPos));
				if (k.getTeam() != campion.getTeam() && k.clickRange() > k.getDistance(worldClickPos)) {
					enemy = true;
					campion.setTarget(k);
					//System.out.println("ATTK");
					break;
				}
			}
			if(!enemy){
				getChamp().setTarget(worldClickPos);
			}
		}
	}

	public Champion getChamp() {
		return campion;
	}
	
}
