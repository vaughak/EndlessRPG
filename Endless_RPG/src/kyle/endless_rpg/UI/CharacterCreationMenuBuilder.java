package kyle.endless_rpg.UI;

import java.util.HashMap;

import kyle.endless_rpg.Assets;
//import kyle.endless_rpg.Entity;
import kyle.endless_rpg.SavePlayer;
import kyle.endless_rpg.StatsRoll;
import kyle.endless_rpg.component.PlayerComponent;
import kyle.endless_rpg.component.QuestComponent;
import kyle.endless_rpg.component.StatsComponent;
import kyle.endless_rpg.component.classes.ClassInterface;
import kyle.endless_rpg.component.classes.ClericComponent;
import kyle.endless_rpg.component.classes.RangerComponent;
import kyle.endless_rpg.component.classes.RogueComponent;
import kyle.endless_rpg.component.classes.WarriorComponent;
import kyle.endless_rpg.component.classes.WizardComponent;
import kyle.endless_rpg.component.races.DwarfComponent;
import kyle.endless_rpg.component.races.ElfComponent;
import kyle.endless_rpg.component.races.GnomeComponent;
import kyle.endless_rpg.component.races.HumanComponent;
import kyle.endless_rpg.component.races.OgreComponent;
import kyle.endless_rpg.component.races.RaceInterface;
import kyle.endless_rpg.core.Entity;
import kyle.endless_rpg.screens.MainMenu;
import kyle.endless_rpg.screens.PlayScreen;
import kyle.endless_rpg.screens.ScreenScaling;
import kyle.endless_rpg.tween.ActorAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CharacterCreationMenuBuilder 
{
	private static Skin skin;

	public static Table statsTable;
	public static Table nameInputTable;
	
	public static Label classLabel, raceLabel, nameLabel, strLabel, dexLabel, conLabel, intLabel,wisLabel, chaLabel;
	public static SelectBox classDropDown, raceDropDown, raceComponentDropDown, classComponentDropDown;
	public static TextButton buttonRoll, buttonPlay, buttonBack;
	public static Sprite sprite;
	public static Texture raceSprite;
	private static TextField characterNameInput;
	
	private static StatsRoll statsRoll = new StatsRoll();
	private static final String[] classOptions = {"Warrior", "Cleric", "Wizard", "Rogue", "Ranger" };
	private static final String[] raceOptions = {"Human", "Dwarf", "Elf", "Gnome", "Ogre" };
	
	private static HashMap<String, ClassInterface> classComponents;
	private static HashMap<String, RaceInterface> raceComponents;
	private static StatsComponent statsComponent;
	
	public static TweenManager inputTween;

	
	private static ClassInterface getClassComponent(String className){
		return classComponents.get(className);
	}
	
	private static RaceInterface getRaceComponent(String raceName){
		return raceComponents.get(raceName);
	}
	
	private static void InitializeComponents(){
		classComponents = new HashMap<String, ClassInterface>();
		raceComponents = new HashMap<String, RaceInterface>();

		statsComponent = new StatsComponent();

		classComponents.put(ClericComponent.className, new ClericComponent());
		classComponents.put(RangerComponent.className, new RangerComponent());
		classComponents.put(RogueComponent.className, new RogueComponent());
		classComponents.put(WizardComponent.className, new WizardComponent());
		classComponents.put(WarriorComponent.className, new WarriorComponent());
		

		raceComponents.put(DwarfComponent.raceName, new DwarfComponent());
		raceComponents.put(ElfComponent.raceName, new ElfComponent());
		raceComponents.put(GnomeComponent.raceName, new GnomeComponent());
		raceComponents.put(OgreComponent.raceName, new OgreComponent());
		raceComponents.put(HumanComponent.raceName, new HumanComponent());
		
		statsRoll.setClassSelection(getClassComponent(classDropDown.getSelection()));
		statsRoll.setRaceSelection(getRaceComponent(raceDropDown.getSelection()));
		statsRoll.rollStats();
		UpdateLabels();
	}
	
	private static void UpdateLabels()
	{
		strLabel.setText("STR: " + statsRoll.getStrString());
		dexLabel.setText("DEX: " + statsRoll.getDexString());
		wisLabel.setText("WIS: " + statsRoll.getWisString());
		intLabel.setText("INT: " + statsRoll.getIntString());
		conLabel.setText("CON: " + statsRoll.getConString());
		chaLabel.setText("CHA: " + statsRoll.getChaString());
 	}
	
	private static void RaceChange()
	{

		raceSprite = new Texture(Gdx.files.internal(getRaceComponent(raceDropDown.getSelection()).getTexturePath()));	
		
		sprite = new Sprite(raceSprite);
		//sprite.scale(.5f);
		sprite.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		sprite.setPosition((Gdx.graphics.getWidth() *.75f - raceSprite.getWidth()/2), 150);
		sprite.setPosition(Gdx.graphics.getWidth()*.40f,Gdx.graphics.getHeight()*.40f);
	}
	
	public static Table BuildNameInputTable()
	{
		nameInputTable = new Table(skin);
		//nameInputTable.setFillParent(true);	
		nameInputTable.setPosition(0, 0);
		nameInputTable.setBounds(0, 0, ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT/8);
		nameInputTable.bottom();
		nameInputTable.add(nameLabel);
		nameInputTable.add(characterNameInput).padBottom(20);
		nameInputTable.getCell(characterNameInput).size(Gdx.graphics.getWidth()/2, 100).padBottom(20);		
		//nameInputTable.debug();
		return nameInputTable;
	}
	
	public static void CloseKeyboardTween()
	{
		Tween.to(nameInputTable, ActorAccessor.Y, .25f).target(0).start(inputTween);
	}
	
	public static Table BuildStatsTable(){
		inputTween = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		skin = Assets.manager.get(Assets.uiskinJSON);
		
		classLabel = new Label("Class: ", skin);
		raceLabel = new Label("Race: ", skin);
		nameLabel = new Label("Name: ", skin);
		strLabel = new Label("STR: ", skin);
		dexLabel = new Label("DEX: ", skin);
		conLabel = new Label("CON: ", skin);
		intLabel = new Label("INT: ", skin);
		wisLabel = new Label("WIS: ", skin);
		chaLabel = new Label("CHA: ", skin);
		
		CreateButtons();
		InitializeComponents();
		RaceChange();
		
		statsTable = new Table(skin);
		//statsTable.debug();
		statsTable.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		statsTable.setPosition(0, ScreenScaling.VIRTUAL_HEIGHT - Gdx.graphics.getHeight());
		
		//statsTable.setFillParent(true);
		statsTable.top().left();
		
		statsTable.add(raceLabel);
		statsTable.add(raceDropDown);
		statsTable.getCell(raceLabel).padBottom(10).minHeight(50).expandX().left();
		statsTable.getCell(raceDropDown).padBottom(10).minHeight(50).minWidth(300).expandX().right();
		statsTable.row();
		
		statsTable.add(classLabel);
		statsTable.add(classDropDown);
		statsTable.getCell(classLabel).minHeight(50).expandX().left();
		statsTable.getCell(classDropDown).padBottom(10).minHeight(50).minWidth(300).expandX().right();
		statsTable.row();
		
		statsTable.add(strLabel).padBottom(50).left().expandX();
		statsTable.row();
		statsTable.add(dexLabel).padBottom(50).left().expandX();
		statsTable.row();		
		statsTable.add(conLabel).padBottom(50).left().expandX();
		statsTable.row();
		statsTable.add(intLabel).padBottom(50).left().expandX();
		statsTable.row();
		statsTable.add(wisLabel).padBottom(50).left().expandX();
		statsTable.row();
		statsTable.add(chaLabel).padBottom(50).left().expandX();
		statsTable.row();
		statsTable.add(buttonRoll).padBottom(20);
		statsTable.getCell(buttonRoll).size(Gdx.graphics.getWidth()/4, 75).padBottom(10);
		statsTable.row();
		statsTable.add(buttonBack);
		statsTable.getCell(buttonBack).size(Gdx.graphics.getWidth()/4, 75).padBottom(10);
		statsTable.row();
		statsTable.add(buttonPlay);
		statsTable.getCell(buttonPlay).size(Gdx.graphics.getWidth()/4, 75).padBottom(10);	
		
		return statsTable;
	}
	
	private static void CreateButtons()
	{
		buttonRoll = new TextButton("Roll!", skin);
		buttonRoll.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				statsRoll.rollStats();
				UpdateLabels();
			}		
		});
		
		buttonPlay = new TextButton("Play!", skin);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {

				statsComponent.strength = statsRoll.getStr();
				statsComponent.dexterity = statsRoll.getDex();
				statsComponent.wisdom = statsRoll.getWis();
				statsComponent.constitution = statsRoll.getCon();
				statsComponent.charisma = statsRoll.getCha();
				statsComponent.intelligence = statsRoll.getInt();
				
				
				PlayerComponent playerComponent = new PlayerComponent();
				playerComponent.name = characterNameInput.getText();
				playerComponent.raceTextureFile = getRaceComponent(raceDropDown.getSelection()).getTexturePath();
				
				Entity entity = new Entity();
				entity.add(playerComponent);
				entity.add(statsComponent);
				entity.add(new QuestComponent());
				SavePlayer save = new SavePlayer();
				save.Save(entity);

				((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(playerComponent.name));				
				return;
			}			
		});
		
		buttonBack = new TextButton("Back", skin);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.print("BACK\n");
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}		
		});

		classDropDown = new SelectBox(classOptions, skin);
		classDropDown.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				statsRoll.setClassSelection(getClassComponent(classDropDown.getSelection()));
				UpdateLabels();
			}			
		});
		
		raceDropDown = new SelectBox(raceOptions, skin);
		raceDropDown.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				statsRoll.setRaceSelection(getRaceComponent(raceDropDown.getSelection()));
				UpdateLabels();
				RaceChange();

			}		
		});


		characterNameInput = new TextField("", skin);
		characterNameInput.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(Gdx.input.isPeripheralAvailable(Input.Peripheral.OnscreenKeyboard)){
					Tween.to(nameInputTable, ActorAccessor.Y, .4f).target(Gdx.graphics.getHeight()/3).start(inputTween);
				}
			}		
		});	
	}

}
