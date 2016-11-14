package com.mygdx.game.screen;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Asteroid;
import com.mygdx.game.model.EyeSpaceCrosshair;
import com.mygdx.game.model.EyeSpaceFaller;
import com.mygdx.game.model.GameOverException;
import com.mygdx.game.model.GazeListener;
import com.mygdx.game.model.Ship;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.data.Point2D;


public class EyeSpaceGameScreen extends AbstractScreen {
    // Controllers
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private int screenWidth = 1920;
    private int screenHeight = 1080;
    int width = 128;

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
    	camera.setToOrtho(false, screenWidth, screenHeight);
    	batch = new SpriteBatch();
    	gm.activate();
    	GazeListener gazeListener = new GazeListener(this);
    	gm.addGazeListener(gazeListener);
    	
    	// gameobjects
    	eyeSpaceCrosshair = new EyeSpaceCrosshair(screenWidth / 2, screenHeight / 2, width, width, crosshair);
    	
    	// start the playback of the background music immediately
//        rainMusic.setLooping(true);
//        rainMusic.play();
    }

    public synchronized void setTarget(Point2D point) {
        this.target = point;
        this.target.y = screenHeight - this.target.y;
    }

    private Rectangle randomFaller(Random r) {
        Rectangle rectangle = new Rectangle();
        rectangle.x = r.nextInt(screenWidth);
        int width = 128;
        rectangle.y = screenHeight - width;
        rectangle.width = width;
        rectangle.height = width;
        return rectangle;
    }

    private void spawnShip() {
        Random r = new Random();
        if (r.nextInt(1000) > difficulty) return;
        eyeSpaceFallers.add(new Ship(r.nextInt(screenWidth), screenHeight - width, width, width, shipImage));
    }

    private void spawnAsteroid() {
        Random r = new Random();
        if (r.nextInt(1000) > difficulty) return;
        eyeSpaceFallers.add(new Asteroid(r.nextInt(screenWidth), screenHeight - width, width, width, asteroidImages[r.nextInt(4)]));
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
            for (EyeSpaceFaller drawable : eyeSpaceFallers) {
                drawable.draw(batch, camera);
            }

            batch.begin();
            batch.draw(crosshair, eyeSpaceCrosshair.getX(), eyeSpaceCrosshair.getY(), eyeSpaceCrosshair.getWidth(), eyeSpaceCrosshair.getHeight());
            batch.end();
        } catch (GameOverException e) {
            this.dispose();
        }
	}
}

