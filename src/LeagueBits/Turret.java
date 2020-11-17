package LeagueBits;

import engine.DamageType;
import game.Game;
import game.Killable;
import graphics.Mesh;
import graphics.Texture;
import org.joml.Vector2f;

public class Turret extends Killable {
    private double armour;

    public Turret(Mesh mesh, Texture texture) {
        super(mesh, texture);
    }

    public boolean isDead() {
        return HP <= 0;
    }

    public void damage(int amount, DamageType damageType) {
        double multiplyer = 1;
        if (damageType == DamageType.PHYSICAL) {
            double armour = getArmour();
            if (armour >= 0) {
                multiplyer = 100d / (100+armour);
            }
            else {
                multiplyer = 2 - 100d / (100-armour);
            }
        }
        HP -= multiplyer*amount;
    }

    public Vector2f getPossition() {
        return null;
    }

    public double getArmour() {
        return armour;
    }

    public void update(float elapsedTime, Game game, ILeagueGameState gs) {

    }
}
