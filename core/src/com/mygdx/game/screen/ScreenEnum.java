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
    LOSE {
        public AbstractScreen getScreen(Object... params) {
            return new LoseScreen((Integer) params[0]);
        }
    };
 
    public abstract AbstractScreen getScreen(Object... params);
}