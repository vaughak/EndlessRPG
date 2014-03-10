package kyle.endless_rpg.screens;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.SavePlayer;
import kyle.endless_rpg.UI.PlayMenuBuilder;
import kyle.endless_rpg.component.PlayerComponent;
import kyle.endless_rpg.component.QuestComponent;
import kyle.endless_rpg.component.StatsComponent;
import kyle.endless_rpg.core.Engine;
import kyle.endless_rpg.core.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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


public class PlayScreen implements Screen{

	
	private Stage stage;
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private Table menuTable, playerBarsTable, enemyBarsTable;
	private Camera camera;


	private SavePlayer playerData;
	private Engine engine;
	private Entity player;
	private String playerName;


	public PlayScreen(String playerName){
		this.playerName = playerName;
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
		
		
		engine.update(delta);
		PlayMenuBuilder.updateCombatLog();
		
		batch.begin();
			backgroundSprite.draw(batch);
		batch.end();

		Table.drawDebug(stage);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		ScreenScaling.InitViewport(width,  height);
		
		playerBarsTable.setBounds(0, ScreenScaling.VIRTUAL_HEIGHT/2, ScreenScaling.VIRTUAL_WIDTH/2, ScreenScaling.VIRTUAL_HEIGHT/2);
		menuTable.setBounds(0,0, ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT/4.5f);
		PlayMenuBuilder.menuTable.setPosition(0, PlayMenuBuilder.mainTableHeight);
	}

	@Override
	public void show() {

		engine = new Engine();
		playerData = new SavePlayer();
		engine.addEntity(playerData.Load(playerName));
		
		batch = new SpriteBatch();
		stage = new Stage();
		
		camera = new OrthographicCamera(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT);
		camera.position.set(ScreenScaling.VIRTUAL_WIDTH/2, ScreenScaling.VIRTUAL_HEIGHT/2, 0);
		camera.update();
		
		backgroundSprite = new Sprite(Assets.manager.get(Assets.mainBackgroundImg, Texture.class));
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
		
		playerBarsTable = new Table();
		playerBarsTable.top().left();
		playerBarsTable.add(engine.getPlayerEntity(PlayerComponent.class).getComponent(StatsComponent.class).healthBar).padBottom(10).row();
		playerBarsTable.add(engine.getPlayerEntity(PlayerComponent.class).getComponent(StatsComponent.class).manaBar).padBottom(10).row();
		playerBarsTable.add(engine.getPlayerEntity(PlayerComponent.class).getComponent(StatsComponent.class).experienceBar).padBottom(10).row();

		PlayMenuBuilder.mainTableHeight = ScreenScaling.VIRTUAL_HEIGHT/4.5f + 10;
		PlayMenuBuilder.progressBarHeight = engine.getPlayerEntity(PlayerComponent.class).getComponent(QuestComponent.class).progressBar.getHeight();
		
		menuTable = new Table();
		menuTable.bottom();
		menuTable.add(PlayMenuBuilder.CreateCombatLog()).row();
		menuTable.add(engine.getPlayerEntity(PlayerComponent.class).getComponent(QuestComponent.class).progressBar);

		stage.setCamera(camera);
		stage.addActor(PlayMenuBuilder.BuildMenu());
		stage.addActor(menuTable);
		stage.addActor(playerBarsTable);


		
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
		batch.dispose();
		stage.dispose();
	}

}
