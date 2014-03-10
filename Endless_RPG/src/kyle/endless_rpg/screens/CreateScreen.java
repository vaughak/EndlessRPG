package kyle.endless_rpg.screens;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.UI.CharacterCreationMenuBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CreateScreen implements Screen {
	
	private Stage stage;
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private Camera camera;
	
	public CreateScreen() {

	}

	@Override
	public void render(float delta) {
		
		switch(Gdx.app.getType()){
		case Android:
			camera.update();
			camera.apply(Gdx.gl10);
			Gdx.gl.glViewport((int)ScreenScaling.viewport.x, (int)ScreenScaling.viewport.y, (int)ScreenScaling.viewport.width, (int)ScreenScaling.viewport.height);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			break;
		case Desktop:
			GL20 glDesk = Gdx.graphics.getGL20();
			glDesk.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			glDesk.glViewport((int)ScreenScaling.viewport.x, (int)ScreenScaling.viewport.y, (int)ScreenScaling.viewport.width, (int)ScreenScaling.viewport.height);	
			camera.update();
			break;
		}
		
		CharacterCreationMenuBuilder.inputTween.update(delta);

		batch.begin();
			backgroundSprite.draw(batch);
			CharacterCreationMenuBuilder.sprite.draw(batch);
		batch.end();
		
		Table.drawDebug(stage);
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
	
		ScreenScaling.InitViewport(width,  height);
	}

	@Override
	public void show() {
		
		batch = new SpriteBatch();
		stage = new Stage();
		camera = new OrthographicCamera(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT);
		camera.position.set(ScreenScaling.VIRTUAL_WIDTH/2, ScreenScaling.VIRTUAL_HEIGHT/2, 0);

		backgroundSprite = new Sprite(Assets.manager.get(Assets.mainBackgroundImg, Texture.class));
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		KeyboardProcessor inputProcessor = new KeyboardProcessor();
		InputMultiplexer multiplexer = new InputMultiplexer();
		
		multiplexer.addProcessor(inputProcessor);
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);

		stage.setCamera(camera);
		stage.addActor(CharacterCreationMenuBuilder.BuildStatsTable());
		stage.addActor(CharacterCreationMenuBuilder.BuildNameInputTable());
	}


	@Override
	public void hide() {
	
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	}

	public class KeyboardProcessor implements InputProcessor {
		   @Override
		   public boolean keyDown (int keycode) {
			   
			   if(keycode == Keys.ENTER)
				   CharacterCreationMenuBuilder.CloseKeyboardTween();
		      return false;
		   }

		   @Override
		   public boolean keyUp (int keycode) {
		      return false;
		   }

		   @Override
		   public boolean keyTyped (char character) {
		      return false;
		   }

		   @Override
		   public boolean touchDown (int x, int y, int pointer, int button) {
		      return false;
		   }

		   @Override
		   public boolean touchUp (int x, int y, int pointer, int button) {
		      return false;
		   }

		   @Override
		   public boolean touchDragged (int x, int y, int pointer) {
		      return false;
		   }

		   @Override
		   public boolean scrolled (int amount) {
		      return false;
		   }

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}
		}	
}
