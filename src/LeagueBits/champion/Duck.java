package LeagueBits.champion;

import LeagueBits.Champion;
import game.Game;
import graphics.Geometry;
import graphics.Mesh;
import graphics.Texture;
import org.joml.Vector2f;

public class Duck extends Champion {

    public Duck() {
        super(Geometry.quad, new Texture("res/tex/duckman.png", 4));
        System.out.println("duck");
        setScale(2);
        baseMoveSpeed = 15;
    }

    public boolean isDead() {
        return HP > 0;
    }

    public Vector2f getPossition() {
        return position;
    }
}
