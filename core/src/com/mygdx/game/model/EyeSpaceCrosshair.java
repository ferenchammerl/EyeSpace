package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ferenc_S on 11/14/2016.
 */
public class EyeSpaceCrosshair extends EyeSpaceDrawable {

    public EyeSpaceCrosshair(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void update(float delta) {

    }

    public void setPosition(float x, float y) {
        this.x = (int) x - 64;
        this.y = (int) y - 64;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
