package LeagueBits;

import game.Game;
import game.Killable;
import graphics.Mesh;
import graphics.Texture;
import org.joml.Vector2d;
import org.joml.Vector2f;

public abstract class Champion extends Killable {
    protected double MP;
    protected Killable targetMob;
    protected Vector2f targetPos;

    protected double level;
    protected double baseHP;
    protected double baseMP;
    protected double baseArmour;
    protected double armourPerLevel;
    protected double baseMagicResist;
    protected double baseMoveSpeed;

    public Champion(Mesh mesh, Texture texture) {
        super(mesh, texture);
        setDepthLayer(5);
    }

    public double getArmour() {
        return baseArmour + armourPerLevel * (level-1);
    }

    public void update(float elapsedTime, Game game, ILeagueGameState gs) {
        Vector2f tPos = new Vector2f();
        System.out.println("updata " + targetPos);
        if (targetMob != null) {
            targetMob.getPosition().get(tPos);
            System.out.println("update " + targetMob.getPosition());
        }
        else if (targetPos != null){
            targetPos.get(tPos);
            System.out.println("update " + targetPos);
        }
        else {return;}
        if (tPos.equals(position)) {
            targetPos = null;
            return;
        }
        Vector2f targetDif = new Vector2f(tPos.x - position.x, tPos.y - position.y);
        float dist = Math.min((float) (elapsedTime * getSpeed()), targetDif.length());
        System.out.println(position);
        Vector2f offset = new Vector2f();
        targetDif.normalize(offset);
        offset.mul(dist);
        position.add(offset);
        //System.out.println(tPos.sub(position));
        if (dist == targetDif.length()) {
            targetPos = null;
        }
    }

    public double getSpeed() {
        return baseMoveSpeed;
    }

    public void setTarget(Vector2f targetPos) {
        this.targetMob = null;
        this.targetPos = targetPos;
    }

    public void setTarget(Killable targetMob) {
        this.targetMob = targetMob;
    }
}
