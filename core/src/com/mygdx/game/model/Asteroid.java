package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ferenc_S on 11/14/2016.
 */
public class Asteroid extends EyeSpaceFaller {
    public Asteroid(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (this.y < 0 ) System.exit(0);
    }
}
