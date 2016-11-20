package com.mygdx.game.model;

import com.mygdx.game.screen.EyeSpaceGameScreen;
import com.theeyetribe.clientsdk.IGazeListener;
import com.theeyetribe.clientsdk.data.GazeData;

public class GazeListener implements IGazeListener {
    private final EyeSpaceGameScreen game;

    public GazeListener(EyeSpaceGameScreen eyeSpaceGame) {
        this.game = eyeSpaceGame;
    }

    public void onGazeUpdate(GazeData gazeData) {
        this.game.setTarget(gazeData.smoothedCoordinates);
    }
}