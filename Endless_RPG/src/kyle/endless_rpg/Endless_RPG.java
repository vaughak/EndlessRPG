package kyle.endless_rpg;

import kyle.endless_rpg.screens.MainMenu;
import kyle.endless_rpg.screens.Splash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Endless_RPG extends Game {
	
	public static final String TITLE = "Endless RPG", VERSION = "0.0.0.0";
	
	public GameOrientation gameOrientation;
	
	public Endless_RPG(GameOrientation gameOrientation){
		this.gameOrientation = gameOrientation;
	}
	
	@Override
	public void create() {		
		//Gdx.app.log(TITLE, "create()");
		setScreen(new Splash());
		//setScreen(new MainMenu());

	}

	@Override
	public void dispose() {
		//Gdx.app.log(TITLE, "dispose()");
		super.dispose();
	}

	@Override
	public void render() {		
		//Gdx.app.log(TITLE, "render()");
		super.render();

	}

	@Override
	public void resize(int width, int height) {
		//Gdx.app.log(TITLE, "resize()");
		super.resize(width, height);
	}

	@Override
	public void pause() {
		//Gdx.app.log(TITLE, "pause()");
		super.pause();
	}

	@Override
	public void resume() {
		//Gdx.app.log(TITLE, "resume()");
		super.resume();
	}
}
