package kyle.endless_rpg.screens;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.GameOrientation;
import kyle.endless_rpg.tween.SpriteAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Splash implements Screen {

	private SpriteBatch batch;
	private Stage stage;
	private Sprite splash;
	private TweenManager tweenManager;
	private boolean tweenBool;
	private Camera camera;
	
	public Splash()
	{
	}
	
	public void SplashDisplay(){
		//Tween.set(splash, SpriteAccessor.ALPHA).target(1).start(tweenManager);
		Tween.to(splash,  SpriteAccessor.ALPHA,  2).target(0).setCallback(new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source){
				System.out.println("Loading into Main menu");
				dispose();
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		}).start(tweenManager);	
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
		if(Assets.manager.update()){
			if(!tweenBool){
				Gdx.app.log("Splash", "Doing the Tween");
				tweenBool = true;
				SplashDisplay();
			}
		}
		
		tweenManager.update(delta);
		
		batch.begin();
			splash.draw(batch);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		ScreenScaling.InitViewport(width,  height);
	}

	@Override
	public void show() {
		tweenBool = false;
		stage = new Stage();
		
		//Assets.manager.load(Assets.class);

		camera = new OrthographicCamera(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT);
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		Texture splashTexture = new Texture("backgrounds/splash.png");
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.setCamera(camera);

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
		
	}

}
