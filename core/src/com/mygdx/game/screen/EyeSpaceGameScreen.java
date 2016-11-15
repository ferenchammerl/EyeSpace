package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.data.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mygdx.game.Constants.*;


public class EyeSpaceGameScreen extends AbstractScreen {
    // Controllers
    private OrthographicCamera camera;
    private SpriteBatch batch;

    // Assets
    private Texture background;
    private Texture[] asteroidImages;
    private Texture shipImage;
    private Texture crosshair;
    private Sound dropSound;
    private Music rainMusic;

    // Objects
    private List<EyeSpaceFaller> eyeSpaceFallers = new ArrayList<EyeSpaceFaller>();
    private EyeSpaceCrosshair eyeSpaceCrosshair;

    // Game variables
    private int difficulty = 5;

    private Point2D target = new Point2D(50, 50);
    final GazeManager gm = GazeManager.getInstance();

    public EyeSpaceGameScreen() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        background = new Texture(Gdx.files.internal("img/background.jpg"));
        asteroidImages = new Texture[]{
                new Texture(Gdx.files.internal("img/asteroid_1.png")),
                new Texture(Gdx.files.internal("img/asteroid_2.png")),
                new Texture(Gdx.files.internal("img/asteroid_3.png")),
                new Texture(Gdx.files.internal("img/asteroid_4.png"))};
        shipImage = new Texture(Gdx.files.internal("img/ship.png"));
        crosshair = new Texture((Gdx.files.internal("img/crosshair.png")));

        // load the drop sound effect and the rain background "music"

        // controllers
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        batch = new SpriteBatch();
        gm.activate();
        GazeListener gazeListener = new GazeListener(this);
        gm.addGazeListener(gazeListener);

        // gameobjects
        eyeSpaceCrosshair = new EyeSpaceCrosshair(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, OBJECT_SIZE, OBJECT_SIZE, crosshair);

        // start the playback of the background music immediately
//        rainMusic.setLooping(true);
//        rainMusic.play();
    }

    public synchronized void setTarget(Point2D point) {
        this.target = point;
        this.target.y = WORLD_HEIGHT - this.target.y;
    }

    private void spawnShip() {
        Random r = new Random();
        if (r.nextInt(1000) > difficulty) return;
        eyeSpaceFallers.add(new Ship(r.nextInt(WORLD_WIDTH - OBJECT_SIZE), WORLD_HEIGHT - OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE, shipImage));
    }

    private void spawnAsteroid() {
        Random r = new Random();
        if (r.nextInt(1000) > difficulty) return;
        eyeSpaceFallers.add(new Asteroid(r.nextInt(WORLD_WIDTH - OBJECT_SIZE), WORLD_HEIGHT - OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE, asteroidImages[r.nextInt(4)]));
    }

    private void spawn() {
        spawnShip();
        spawnAsteroid();
    }

    private void update() throws GameOverException {
        spawn();
        boolean fire = Gdx.input.isButtonPressed(Input.Buttons.LEFT);

        for (EyeSpaceFaller eyeSpaceFaller : eyeSpaceFallers) {
            eyeSpaceFaller.update(0);
            if (fire && eyeSpaceFaller.hitbox(target.x, target.y) || eyeSpaceFaller.outOfBounds()) {
                eyeSpaceFaller.setToBeDestroyed(true);
            }
        }

        for (int i = 0; i < eyeSpaceFallers.size(); i++) {
            if (eyeSpaceFallers.get(i).isToBeDestroyed()) {
                eyeSpaceFallers.remove(i);
                break;
            }
        }


        eyeSpaceCrosshair.setPosition(target.x, target.y);
    }

    @Override
    public void buildStage() {

    }

    public void render(float delta) {
        try {
            update();
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            batch.setProjectionMatrix(camera.combined);

            batch.begin();
            batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
            batch.end();

            for (EyeSpaceFaller drawable : eyeSpaceFallers) {
                drawable.draw(batch, camera);
            }

            batch.begin();
            batch.draw(crosshair, eyeSpaceCrosshair.getX() - OBJECT_SIZE / 2, eyeSpaceCrosshair.getY() - OBJECT_SIZE / 2, eyeSpaceCrosshair.getWidth(), eyeSpaceCrosshair.getHeight());
            batch.end();
        } catch (GameOverException e) {
            this.dispose();
        }
    }
}

