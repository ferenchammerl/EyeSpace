package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.util.UIFactory;

public class LooseScreen extends AbstractScreen {

	private Texture textureBackground;
	private Texture textureOkay;
	private BitmapFont bitmapFont;
	private Integer score;
	
	public LooseScreen(Integer score) {
		super();
		this.textureBackground = new Texture(Gdx.files.internal("img/loose_bg.png"));
		this.textureOkay = new Texture(Gdx.files.internal("img/ok_btn.png"));
		bitmapFont = new BitmapFont(Gdx.files.internal("font/default.ttf"));
		this.score = score;
	}
	
	@Override
	public void buildStage() {
		Image bg = new Image(textureBackground);
		addActor(bg);
		
		bitmapFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		LabelStyle labelStyle = new LabelStyle(bitmapFont, Color.BLUE);
		Label label = new Label("Final score: " + score.toString(), labelStyle);
		label.setPosition(getWidth() / 2, getHeight() / 3);
		addActor(label);
		
		ImageButton btnOkay = UIFactory.createButton(textureOkay);
		btnOkay.setSize(180f,180f);
		btnOkay.setPosition(getWidth() / 2, getHeight() / 4, Align.center);
		addActor(btnOkay);
		
		btnOkay.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));
		
	}

	@Override
	public void dispose() {
		super.dispose();
		textureBackground.dispose();
		textureOkay.dispose();
	}
}
