package LeagueBits;

import game.Game;
import game.Killable;
import graphics.Mesh;
import graphics.Texture;
import org.joml.Vector2d;
import org.joml.Vector2f;
import physics.Collider;

import java.util.ArrayList;

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
        System.out.println("duck health: " + HP);
        Vector2f tPos = new Vector2f();
        if (targetMob != null) {
            targetMob.getPosition().get(tPos);
        }
        else if (targetPos != null){
            targetPos.get(tPos);
        }
        else {return;}
        if (tPos.equals(position)) {
            targetPos = null;
            return;
        }
        Vector2f targetDif = new Vector2f(tPos.x - position.x, tPos.y - position.y);
        float dist = Math.min((float) (elapsedTime * getSpeed()), targetDif.length());
        //System.out.println(position);
        Vector2f offset = new Vector2f();
        targetDif.normalize(offset);
        offset.mul(dist);
        Vector2f nextPos = new Vector2f();
        position.add(offset, nextPos);
        assertMove(nextPos, gs.getColliders());
        //System.out.println(tPos.sub(position));
        if (dist == targetDif.length()) {
            targetPos = null;
        }
    }

    private void assertMove(Vector2f nPos, ArrayList<Collider> colliders){
        float x = nPos.x;
        float y = nPos.y;

        Collider pCollider = new Collider(nPos.x+0.25f, nPos.y-0.2f, nPos.x+0.8f, nPos.y-1.0f);
        boolean canMove = true;
        for (Collider c : colliders) {
            if (c.intersects(pCollider)) {
                canMove = false;
                break;
            }
        }
        if (canMove) {
            position = nPos;
            return;
        }

        nPos.x = position.x;
        pCollider = new Collider(nPos.x+0.25f, nPos.y-0.2f, nPos.x+0.8f, nPos.y-1.0f);
        canMove = true;
        for(Collider c : colliders) {
            if (c.intersects(pCollider)) {
                canMove = false;
                break;
            }
        }
        if(canMove) {
            position = nPos;
            return;
        }

        nPos.x = x;
        nPos.y = position.y;
        pCollider = new Collider(nPos.x+0.25f, nPos.y-0.2f, nPos.x+0.8f, nPos.y-1.0f);
        canMove = true;
        for(Collider c : colliders) {
            if (c.intersects(pCollider)) {
                canMove = false;
                break;
            }
        }
        if(canMove) {
            position = nPos;
            return;
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
