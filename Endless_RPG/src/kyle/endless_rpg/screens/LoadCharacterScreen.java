package kyle.endless_rpg.screens;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.UI.LoadCharacterMenuBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LoadCharacterScreen implements Screen {
	private Stage stage;
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private Camera camera;
	
	public LoadCharacterScreen(){
		//this.manager = manager;
		//this.gameOrientation = gameOrientation;
		//Gdx.app.log("LoadCharacterScreen", "LoadCharacterScreen()");
		//Gdx.app.log("LoadCharacterScreen", "getWidth: " + Gdx.graphics.getWidth() + " getHeight: " + Gdx.graphics.getHeight());
	}
	
	@Override
	public void render(float delta) {
		switch(Gdx.app.getType()){
		case Android:
			GL10 gl = Gdx.graphics.getGL10();
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			gl.glViewport((int)ScreenScaling.viewport.x, (int)ScreenScaling.viewport.y, (int)ScreenScaling.viewport.width, (int)ScreenScaling.viewport.height);
			
			camera.update();
			camera.apply(gl);
			
			break;
		case Desktop:
			GL20 glDesk = Gdx.graphics.getGL20();
			glDesk.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			glDesk.glViewport((int)ScreenScaling.viewport.x, (int)ScreenScaling.viewport.y, (int)ScreenScaling.viewport.width, (int)ScreenScaling.viewport.height);	
			camera.update();
			break;
		}

		batch.begin();
			backgroundSprite.draw(batch);
		batch.end();
		
		
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		LoadCharacterMenuBuilder.table.setClip(true);
		LoadCharacterMenuBuilder.table.setSize(width, height);
		ScreenScaling.InitViewport(width,  height);
	}

	@Override
	public void show() {
		camera = new OrthographicCamera(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT);
		stage = new Stage();
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
		
		backgroundSprite = new Sprite(Assets.manager.get(Assets.mainBackgroundImg, Texture.class));
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		

		stage.addActor(LoadCharacterMenuBuilder.BuildMenu());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
