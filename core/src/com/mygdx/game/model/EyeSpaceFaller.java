package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ferenc_S on 11/14/2016.
 */
public class EyeSpaceFaller extends EyeSpaceDrawable {
    float speed = 1.0f;
    boolean toBeDestroyed = false;

    public EyeSpaceFaller(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void update(float delta) {
        y -= speed;
    }

    public boolean hitbox(double x, double y) throws GameOverException {
        boolean b = x > this.x && x < this.x + width && y > this.y && y < this.y + width;
        return b;
    }

    public boolean isToBeDestroyed() {
        return toBeDestroyed;
    }

    public void setToBeDestroyed(boolean toBeDestroyed) {
        this.toBeDestroyed = toBeDestroyed;
    }

    public boolean outOfBounds() {
        return x < 0 || y < 0;
    }
}
