package kyle.endless_rpg.UI;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.Endless_RPG;
import kyle.endless_rpg.screens.CreateScreen;
import kyle.endless_rpg.screens.LoadCharacterScreen;
import kyle.endless_rpg.screens.MainMenu;
import kyle.endless_rpg.tween.ActorAccessor;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuBuilder {
	
	public static Skin skin;
	public static Table table;
	public static TextButton buttonPlay, buttonCreate, buttonExit;
	public static Label heading;
	
	public static Table BuildMenu() 
	{
		skin = Assets.manager.get(Assets.uiskinJSON);

		table = new Table(skin);
		heading = new Label(Endless_RPG.TITLE, skin);
		heading.setFontScale(3);
		
		buttonPlay = new TextButton("PLAY", skin);
		buttonPlay.pad(15);
		
		buttonCreate = new TextButton("NEW", skin);
		buttonCreate.pad(15);	
		
		buttonExit = new TextButton("EXIT", skin);
		buttonExit.pad(15);

		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setPosition(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2);
		table.add(heading);
		table.getCell(heading).spaceBottom(150);
		table.row();
		table.add(buttonPlay);
		table.getCell(buttonPlay).spaceBottom(15);
		table.getCell(buttonPlay).size(Gdx.graphics.getWidth()/4, 100);
		table.row();
		table.add(buttonCreate);
		table.getCell(buttonCreate).spaceBottom(15);
		table.getCell(buttonCreate).size(Gdx.graphics.getWidth()/4, 100);
		table.row();
		table.add(buttonExit);
		table.getCell(buttonExit).size(Gdx.graphics.getWidth()/4, 100);
		//table.debug();
		return table;
	} 
	
	public static TweenManager BuildTween(){
		TweenManager tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		// heading and button fade-in
		Timeline.createSequence().beginSequence()
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonCreate, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
			.push(Tween.from(heading, ActorAccessor.ALPHA, .5f).target(0))
			.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(buttonCreate, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(buttonExit,  ActorAccessor.ALPHA, .5f).target(1))
			.end().start(tweenManager);
		
		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .5f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .5f).target(Gdx.graphics.getHeight()/8).start(tweenManager);	
		return tweenManager;
	}
	

}


