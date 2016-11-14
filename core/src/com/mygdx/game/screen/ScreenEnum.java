package com.mygdx.game.screen;

public enum ScreenEnum {
	 
    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },
    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new EyeSpaceGameScreen();
        }
    },
    LOOSE {
        public AbstractScreen getScreen(Object... params) {
            return new LooseScreen();
        }
    };
 
    public abstract AbstractScreen getScreen(Object... params);
}