package kyle.endless_rpg.screens;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.UI.MainMenuBuilder;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen {
	
	public Stage stage;
	private TweenManager tweenManager;
	
	private SpriteBatch batch;
	private Sprite backgroundSprite;

    private Camera camera;

	
	
	public MainMenu() 
	{

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
			camera.update();
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glViewport((int)ScreenScaling.viewport.x, (int)ScreenScaling.viewport.y, (int)ScreenScaling.viewport.width, (int)ScreenScaling.viewport.height);	
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			break;
		}

		batch.begin();
			backgroundSprite.draw(batch);
		batch.end();
		
		tweenManager.update(delta);
		
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
		Assets.manager.load(Assets.class);
		stage = new Stage();	
		batch = new SpriteBatch();
		camera = new OrthographicCamera(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT);

		Gdx.input.setInputProcessor(stage);
		
		Texture backgroundSpriteT = Assets.manager.get(Assets.mainBackgroundImg, Texture.class);
		backgroundSprite = new Sprite(backgroundSpriteT);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		System.out.println("Setup the background");
		
		
		stage.setCamera(camera);
		stage.addActor(MainMenuBuilder.BuildMenu());
		tweenManager = MainMenuBuilder.BuildTween();
		
		MainMenuBuilder.buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				dispose();
				Gdx.app.exit();
			}		
		});
		
		MainMenuBuilder.buttonCreate.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.print("CREATE\n");
				((Game) Gdx.app.getApplicationListener()).setScreen(new CreateScreen());
				return;
			}		
		});
		
		MainMenuBuilder.buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.print("PLAY\n");
				((Game) Gdx.app.getApplicationListener()).setScreen(new LoadCharacterScreen());
				return;
			}		
		});

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
		stage.dispose();
		MainMenuBuilder.skin.dispose();
		Assets.dispose();

		
	}

}
