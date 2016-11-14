package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ferenc_S on 11/14/2016.
 */
public class Ship extends EyeSpaceFaller {

    public Ship(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public boolean hitbox(double x, double y) throws GameOverException {
        if (super.hitbox(x, y)) throw new GameOverException("You blasted a ship out of existence :O");
        return false;
    }
}
