package LeagueBits;

import engine.DamageType;
import game.Game;
import game.Killable;
import graphics.Geometry;
import graphics.Mesh;
import graphics.Sprite;
import graphics.Texture;
import org.joml.Vector2f;

public class Turret extends Killable {
    private double armour;
    private Sprite sp;
    private final float ATTACK_RANGE = 5;
    private float attackTimer;

    public Turret(Sprite sprite) {
        super(Geometry.quad(), sprite.getTexture());
        team = Team.RED;
        sp = sprite;
        position = sprite.getPosition();
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
        attackTimer -= elapsedTime;
        if (attackTimer < 0 ) {
            for (Champion champ : gs.getChampions()) {
                if (champ.getTeam() != team && position.distance(champ.getPosition()) < ATTACK_RANGE) {
                    Projectile proj = new Projectile(position.x, position.y, Projectile.turretFire, champ, 10);
                    gs.addSprite(proj);
                    gs.addUpdateable(proj);
                    attackTimer = 2;
                    System.out.println("attack turret move out");
                }
            }

        }
    }
}
