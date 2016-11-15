package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

import static com.mygdx.game.Constants.WORLD_HEIGHT;
import static com.mygdx.game.Constants.WORLD_WIDTH;

/**
 * Created by Ferenc_S on 11/14/2016.
 */
public class EyeSpaceCrosshair extends EyeSpaceDrawable {

    float speed = 7;

    public EyeSpaceCrosshair(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void update(float delta) {

    }

    public void setPosition(float x, float y) {
        int oldX = this.x;
        sendXTo(x);
        System.out.println(String.format("Position X going from %s to %s towards %s", oldX, this.x, x));
        sendYTo(y);
    }


    void sendXTo(float x) {
        if (x < 0) x = 0;
        if (x > WORLD_WIDTH) x = WORLD_WIDTH;
        double actualSpeed = Math.sqrt(Math.abs(x - this.x)) * speed;
        if (actualSpeed < 3) return;
        this.x += x > this.x ? actualSpeed : -actualSpeed;
//        if (Math.abs(x - this.x) > 30)
//            this.x += (x - this.x) / 10;
    }

    void sendYTo(float y) {
        if (y < 0) y = 0;
        if (y > WORLD_HEIGHT) y = WORLD_HEIGHT;
        double actualSpeed = Math.sqrt(Math.abs(y - this.y)) * speed;
        if (actualSpeed < 3) return;
        this.y += y > this.y ? actualSpeed : -actualSpeed;
//        if (y < 0) y = 0;
//        if (y > 1080) y = 1080;
//        if (Math.abs(y - this.y) > 30)
//            this.y += (y - this.y) / 10;
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
