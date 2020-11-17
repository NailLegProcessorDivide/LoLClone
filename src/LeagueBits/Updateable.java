package LeagueBits;

import game.Game;

public interface Updateable {
    public void update(float elapsedTime, Game game, ILeagueGameState gs);
}
