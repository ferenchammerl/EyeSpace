package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.ScreenEnum;
import com.mygdx.game.util.ScreenManager;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
	}


}
