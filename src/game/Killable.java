package game;

import LeagueBits.IWorldObject;
import LeagueBits.Team;
import LeagueBits.Updateable;
import engine.DamageType;
import graphics.Mesh;
import graphics.Sprite;
import graphics.Texture;
import org.joml.Vector2f;

public abstract class Killable extends Sprite implements Updateable {

    public int HP;
    protected Team team;

    public Killable(float x, float y, Mesh mesh, Texture texture) {
        super(x, y, mesh, texture);
    }
    public Killable(Mesh mesh, Texture texture) {
        super(mesh, texture);
    }

    public boolean isDead() {
        return HP>0;
    };

    public void damage(float amount, DamageType damageType) {
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
    public float getDistance(Vector2f clickPos) {
        System.out.println(position.distance(clickPos));
        System.out.println(position);
        System.out.println(clickPos);
        return position.distance(clickPos);
    }
    public float clickRange(){
        return 1.5f;
    }
    public Team getTeam() {
        return team;
    }
}
