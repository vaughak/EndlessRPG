package kyle.endless_rpg.UI;

import java.util.List;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.SavePlayer;
import kyle.endless_rpg.screens.CreateScreen;
import kyle.endless_rpg.screens.PlayScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoadCharacterMenuBuilder 
{
	private static Skin skin;
	private static TextureAtlas atlas;
	public static Table table;
	
	public static Table BuildMenu(){
		atlas = Assets.manager.get(Assets.uiskinAtlas);
		skin = Assets.manager.get(Assets.uiskinJSON);
		InitButtons();
		return table;
	}
	
	private static void InitButtons(){
		SavePlayer save = new SavePlayer();
		List<String> fileNames = save.GetSaveGamesList();


		if(fileNames == null){
			((Game) Gdx.app.getApplicationListener()).setScreen(new CreateScreen());
			table = new Table();
			return;
		}else{
	
			table = new Table(skin);
			table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			
			for(String s : fileNames){
				final String name = s;
				TextButton button = new TextButton(s, skin);
				
				table.add(button);
				table.getCell(button).spaceBottom(15);
				table.getCell(button).size(Gdx.graphics.getWidth()/2, 200);
				table.row();
				
				button.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y){
						//dispose();
						((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(name));	
						return;
					}
				});
			}
		}
		
		

	}
}
