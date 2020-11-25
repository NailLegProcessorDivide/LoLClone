package LeagueBits;

import engine.DamageType;
import game.Game;
import game.Killable;
import graphics.Geometry;
import graphics.Mesh;
import graphics.Sprite;
import graphics.Texture;
import org.joml.Vector2f;

public class Projectile<Killabe> extends Sprite implements Updateable {
    public static final Texture turretFire = new Texture("res/tex/turretFire.png", 1);
    Killable target;
    float damage;
    final float SPEED = 4;

    public Projectile(float x, float y, Texture texture, Killable _target, float _damage) {
        super(x, y, Geometry.quad(), texture);
        damage = _damage;
        target = _target;
        setDepthLayer(6);
    }

    public void update(float elapsedTime, Game game, ILeagueGameState gs) {
        if(target.isDead()) {
            gs.addRemove(this);
            return;
        }
        Vector2f tPos = new Vector2f();
        target.getPosition().get(tPos);
        Vector2f targetDif = new Vector2f(tPos.x - position.x, tPos.y - position.y);
        float dist = Math.min((float) (elapsedTime * SPEED), targetDif.length());
        //System.out.println(position);
        Vector2f offset = new Vector2f();
        targetDif.normalize(offset);
        offset.mul(dist);
        Vector2f nextPos = new Vector2f();
        position.add(offset);
        //System.out.println(tPos.sub(position));
        if (dist == targetDif.length()) {
            target.damage(damage, DamageType.PHYSICAL);
            gs.addRemove(this);
        }
    }
}
