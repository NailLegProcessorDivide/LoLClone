package LeagueBits;

import game.IGameState;
import game.Killable;
import graphics.Camera;
import graphics.Sprite;
import physics.Collider;

import java.util.ArrayList;

public interface ILeagueGameState extends IGameState {
    public ArrayList<Collider> getColliders();
    public ArrayList<Killable> getKillable();
    public ArrayList<Champion> getChampions();
    public void addRemove(Object obj);
    public void addUpdateable(Updateable updateable);
    public void addSprite(Sprite sprite);
    public Camera getCam();
}
