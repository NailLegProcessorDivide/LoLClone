package LeagueBits;

import game.IGameState;
import graphics.Camera;
import physics.Collider;

import java.util.ArrayList;

public interface ILeagueGameState extends IGameState {
    public ArrayList<Collider> getColliders();
    public Camera getCam();
}
