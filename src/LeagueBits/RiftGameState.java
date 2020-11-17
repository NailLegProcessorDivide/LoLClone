package LeagueBits;

import LeagueBits.champion.Duck;
import game.*;
import graphics.Camera;
import graphics.Mesh;
import graphics.Sprite;
import graphics.Texture;
import physics.Collider;

import java.util.ArrayList;

public class RiftGameState implements ILeagueGameState {

    private Scene scene;
    private Camera camera;
    private double time;
    private Player player;

    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private ArrayList<Collider> colliders = new ArrayList<Collider>();

    private Game game;

    public void initState(Game game) {
        this.game=game;
        scene = new Scene("res/map/rift.mp");
        colliders = scene.getColliders();

        camera = new Camera();
        camera.setPosition(75, -75);
        camera.setScale(16);
        camera.setMinBright(0.25f);
        camera.setMaxBright(1);

        Texture playerTex = new Texture("res/tex/duckman.png", 4);
        Champion champ = new Duck();
        champ.setPosition(0, 0);
        player = new Player(champ);
        sprites.add(champ);
    }

    public void loadState() {

    }

    public void playState(float elapsedTime, Game game) {
        time+=elapsedTime;
        float light = (float) (Math.cos(time/5)*0.45+0.5f);
        camera.setMinBright(1);

        player.update(elapsedTime, game, this);
        player.getChamp().update(elapsedTime, game, this);

        camera.follow(player.getChamp(), 0.05f);
        //camera.lockPosition(0, 0, scene.getWidth(), scene.getHeight(), game.getWindow());
        if (game.isTab()) {
            scene = new Scene("res/map/rift.mp");
            colliders = scene.getColliders();
        }

    }

    public void render(Renderer renderer) {
        renderer.render(camera, sprites, true);
        renderer.render(camera, scene.getBackground(), false);
        renderer.render(camera, scene.getForeground(), false);
    }

    public ArrayList<Collider> getColliders() {
        return colliders;
    }

    @Override
    public Camera getCam() {
        return camera;
    }
}
