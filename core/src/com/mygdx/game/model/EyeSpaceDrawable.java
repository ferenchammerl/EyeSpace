package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Ferenc_S on 11/14/2016.
 */
public abstract class EyeSpaceDrawable {
    int x, y, width, height;
    Texture texture;

    public EyeSpaceDrawable(int x, int y, int width, int height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public void draw(SpriteBatch batch, Camera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture, x, y, width, height);
        batch.end();
    }

    public abstract void update(float delta);
}
