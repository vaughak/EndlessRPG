package kyle.endless_rpg.UI;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.screens.ScreenScaling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class PlayMenuBuilder 
{
	private static Skin skin, tableSkin, combatSkin;
	private static TextureAtlas tableAtlas, combatAtlas;
	public static Table menuTable, combatLogTable, logContainer;
	private static Button logBtn, inventoryBtn, armorBtn, skillsBtn;
	private static ScrollPane combatLogPane;
	private static boolean logOpen;
	public static float progressBarHeight;
	public static float mainTableHeight;

	public static Array<String> logInput;

	
	public static Table BuildMenu(){
		logOpen = true;
		skin = Assets.manager.get(Assets.uiskinJSON);
		
		tableAtlas = Assets.manager.get(Assets.playMenuButtons);
		tableSkin = new Skin(tableAtlas);
		
		logInput = new Array<String>();
		
		BitmapFont black = new BitmapFont(Gdx.files.internal("font/black16.fnt"));
		
		menuTable = new Table(skin);
		//menuTable.setBounds(0,ScreenScaling.VIRTUAL_WIDTH/3f + 10, ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_WIDTH/5f);
		menuTable.size(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_WIDTH/5f);
		
		
		//Skills menu button
		TextButtonStyle menuStyle = new TextButtonStyle();
		menuStyle.up = tableSkin.getDrawable("P_Red01");
		menuStyle.down = tableSkin.getDrawable("P_White01");
		menuStyle.font = black;
		menuStyle.pressedOffsetX = 1;
		menuStyle.pressedOffsetY = -1;
		
		//Inventory menu button
		TextButtonStyle invStyle = new TextButtonStyle();
		invStyle.up = tableSkin.getDrawable("I_Chest01");
		invStyle.down = tableSkin.getDrawable("I_Chest02");
		invStyle.font = black;
		invStyle.pressedOffsetX = 1;
		invStyle.pressedOffsetY = -1;
		
		//Combat log menu button
		TextButtonStyle logStyle = new TextButtonStyle();
		logStyle.up = tableSkin.getDrawable("I_Scroll02");
		logStyle.down = tableSkin.getDrawable("I_Scroll");
		logStyle.font = black;
		logStyle.pressedOffsetX = 1;
		logStyle.pressedOffsetY = -1;
		
		//Character sheet menu button
		TextButtonStyle armorStyle = new TextButtonStyle();
		armorStyle.up = tableSkin.getDrawable("S_Buff10");
		armorStyle.down = tableSkin.getDrawable("S_Buff12");
		armorStyle.font = black;
		armorStyle.pressedOffsetX = 1;
		armorStyle.pressedOffsetY = -1;
		
	
		skillsBtn = new Button(menuStyle);
		inventoryBtn = new Button(invStyle);
		armorBtn = new Button(armorStyle);
		logBtn = new Button(logStyle);
		

		skillsBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Gdx.app.exit();
			}
		});
		
 
		inventoryBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Gdx.app.exit();
			}
		});
		

		armorBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Gdx.app.exit();
			}
		});
		

		logBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(logOpen){
					logContainer.setVisible(false);
					menuTable.setPosition(0,progressBarHeight);
				}
				else {
					logContainer.setVisible(true);
					PlayMenuBuilder.menuTable.setPosition(0, mainTableHeight);
				}
				
				logOpen = !logOpen;
			}
		});

		
		menuTable.add(skillsBtn);
		menuTable.getCell(skillsBtn).size(75,75).expandX();
		
		menuTable.add(inventoryBtn);
		menuTable.getCell(inventoryBtn).size(75,75).expandX();
		
		menuTable.add(armorBtn);
		menuTable.getCell(armorBtn).size(75,75).expandX();
		
		menuTable.add(logBtn);
		menuTable.getCell(logBtn).size(75,75).expandX();

		
		//menuTable.debug();

		return menuTable;
	}

	public static Table CreateCombatLog(){
		combatAtlas = Assets.manager.get(Assets.playMenuUI);
		combatSkin = new Skin(combatAtlas);
		
	
		combatLogTable = new Table(skin);
		combatLogPane = new ScrollPane(combatLogTable);
		combatLogPane.setScrollingDisabled(true, false);
		combatLogPane.setOverscroll(false, false);

		combatLogPane.validate();
		combatLogPane.validate();
		combatLogPane.setScrollPercentY(100f);

		
		logContainer = new Table(combatSkin);
		logContainer.setBackground(combatSkin.getDrawable("combatLog"));

		logContainer.size(ScreenScaling.VIRTUAL_WIDTH, ScreenScaling.VIRTUAL_HEIGHT/2f);
		logContainer.setFillParent(true);
		logContainer.bottom();
		logContainer.add(combatLogPane);
		logContainer.getCell(combatLogPane).bottom().padBottom(20).padTop(25);

		//logContainer.debug();
		return logContainer;

	}
	
	public static Table testTable(){
		Table t = new Table();
		t.debug();
		t.setBounds(0,40, Gdx.graphics.getWidth(), 500);
		return t;
	}
	
	public static void updateCombatLog(){
		if(logInput.size > 0){
		
			for(int i = 0; i < logInput.size; i++){
				Label l = new Label(" " + logInput.get(i), skin, "white-label");
				l.setWrap(true);
				l.setWidth(Gdx.graphics.getWidth());
				combatLogTable.add(l).width(ScreenScaling.VIRTUAL_WIDTH).padLeft(20);
				combatLogTable.row();
			}
			
			logInput = new Array<String>();
			combatLogPane.validate();
			combatLogPane.validate();
			combatLogPane.setScrollPercentY(100f);
		}

	}
}
