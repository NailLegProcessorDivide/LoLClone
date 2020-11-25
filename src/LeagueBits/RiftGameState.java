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
    private ArrayList<Turret> turrets = new ArrayList<Turret>();
    private ArrayList<Champion> champions = new ArrayList<Champion>();
    private ArrayList<Updateable> updateables = new ArrayList<Updateable>();
    private ArrayList<Updateable> newUpdateable = new ArrayList<Updateable>();
    private ArrayList<Killable> killable = new ArrayList<Killable>();

    private ArrayList<Object> deletable = new ArrayList<Object>();

    private Game game;

    public void initState(Game game) {
        this.game=game;
        scene = new Scene("res/map/twistedTreeline.mp");
        colliders = scene.getColliders();

        camera = new Camera();
        camera.setPosition(75, -75);
        camera.setScale(40);
        camera.setMinBright(0.25f);
        camera.setMaxBright(1);

        Texture playerTex = new Texture("res/tex/duckman.png", 4);
        Champion champ = new Duck();
        champ.setPosition(17, -75);
        player = new Player(champ);
        sprites.add(champ);
        champions.add(champ);

        updateables.add(player);
        updateables.add(champ);
        System.out.println("num turrets: " + scene.getActionGroups()[1].size());
        for (Sprite turret : scene.getActionGroups()[1]) {
            Turret t = new Turret(turret);
            turrets.add(t);
            updateables.add(t);
            killable.add(t);
        }
    }

    public void loadState() {

    }

    public void playState(float elapsedTime, Game game) {
        time+=elapsedTime;
        float light = (float) (Math.cos(time/5)*0.45+0.5f);
        camera.setMinBright(1);
        newUpdateable = new ArrayList<Updateable>();
        for (Updateable u : updateables) {
            u.update(elapsedTime, game, this);
        }
        updateables.addAll(newUpdateable);

        camera.follow(player.getChamp(), 0.05f);
        //camera.lockPosition(0, 0, scene.getWidth(), scene.getHeight(), game.getWindow());
        if (game.isTab()) {
            scene = new Scene("res/map/twistedTreeline.mp");
            colliders = scene.getColliders();
        }
        for (Object rm : deletable) {
            sprites.remove(rm);
            colliders.remove(rm);
            turrets.remove(rm);
            updateables.remove(rm);
            killable.remove(rm);
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
    public ArrayList<Killable> getKillable() {
        return killable;
    }

    @Override
    public ArrayList<Champion> getChampions() {
        return champions;
    }

    @Override
    public void addRemove(Object obj) {
        deletable.add(obj);
    }

    @Override
    public void addUpdateable(Updateable updateable) {
        newUpdateable.add(updateable);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    @Override
    public Camera getCam() {
        return camera;
    }
}
