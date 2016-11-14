package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.util.UIFactory;

public class MainMenuScreen extends AbstractScreen {

	private Texture txtrBg;
	private Texture txtrPlay;
	private Texture txtrExit;
	
	public MainMenuScreen() {
		super();
		txtrBg   = new Texture( Gdx.files.internal("img/eyespace_main_menu_bg.jpg") );
		txtrPlay = new Texture( Gdx.files.internal("img/btn_play.png") );
		txtrExit = new Texture( Gdx.files.internal("img/btn_exit.png") );
	}

	@Override
	public void buildStage() {
		
		// Adding actors
		Image bg = new Image(txtrBg);
		addActor(bg);
		
		ImageButton btnPlay = UIFactory.createButton(txtrPlay);
		btnPlay.setSize(180f,180f);
		btnPlay.setPosition(getWidth() / 4, getHeight() / 2, Align.right);
		addActor(btnPlay);
		
		ImageButton btnExit = UIFactory.createButton(txtrExit);
		btnExit.setSize(180f,180f);
		btnExit.setPosition(getWidth() / 4, getHeight() / 5, Align.right);
		addActor(btnExit);
		
		// Setting listeners
		btnPlay.addListener( UIFactory.createListener(ScreenEnum.GAME) );
		
		btnExit.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Gdx.app.exit();
						return false;
					}
				});
	}

	@Override
	public void dispose() {
		super.dispose();
		txtrBg.dispose();
		txtrPlay.dispose();
		txtrExit.dispose();
	}

}
