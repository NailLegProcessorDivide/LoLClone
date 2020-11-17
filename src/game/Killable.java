package game;

import LeagueBits.IWorldObject;
import LeagueBits.Updateable;
import engine.DamageType;
import graphics.Mesh;
import graphics.Sprite;
import graphics.Texture;

public abstract class Killable extends Sprite implements Updateable {

    public int HP;

    public Killable(float x, float y, Mesh mesh, Texture texture) {
        super(x, y, mesh, texture);
    }
    public Killable(Mesh mesh, Texture texture) {
        super(mesh, texture);
    }

    public abstract boolean isDead();

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

    public double getArmour() {
        return 0;
    }
}
