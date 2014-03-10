package kyle.endless_rpg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kyle.endless_rpg.component.PlayerComponent;
import kyle.endless_rpg.core.Component;
import kyle.endless_rpg.core.Entity;
import kyle.endless_rpg.system.QuestSystem;
import kyle.endless_rpg.system.RenderSystem;
import kyle.endless_rpg.system.StatsSystem;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class SavePlayer 
{
	public void Save(Entity entity){
		Array<Component> components = new Array<Component>();
		components = entity.getAllComponents();
		Json json = new Json();
		String name = entity.getComponent(PlayerComponent.class).name;
		FileHandle file = Gdx.files.local("Saves/" + name + ".json");
		file.writeString(json.prettyPrint(components), false);
	}


	public Entity Load(String name){
		Json json = new Json();
		FileHandle file = Gdx.files.local("Saves/" + name + ".json");
		Array<Component> components = new Array<Component>();
		components = json.fromJson(null, file.readString());
		Entity entity = new Entity();
		
		for(int i = 0; i < components.size; i++){
			entity.add(components.get(i));
		}
		
		entity.addSystem(new StatsSystem(entity));
		entity.addSystem(new QuestSystem(entity));
		entity.addSystem(new RenderSystem());
		
		return entity;
	}

	
	// TODO Can't get this working on Android, so this needs to be redon
	public List<String> GetSaveGamesList(){
		ArrayList<String> inFiles = new ArrayList<String>();
		boolean saveGameBool = false;
		
		//Android
		if(Gdx.app.getType() == ApplicationType.Android){
			FileHandle[] dir = Gdx.files.local("Saves/").list();

			for(FileHandle file: dir){
				if(file.name().toLowerCase().endsWith(".json")){
					saveGameBool = true;
					inFiles.add(file.name().substring(0, file.name().lastIndexOf('.')));
				}
			}
			
		}else{ //Desktop
			File parentDir = new File("Saves/");
			String[] fileNames = parentDir.list();
			
			for(String fileName : fileNames){
				if(fileName.toLowerCase().endsWith(".json")){
					saveGameBool = true;
					inFiles.add(fileName.substring(0, fileName.lastIndexOf('.')));
				}
			}
			
		}
		
		return saveGameBool ? inFiles : null;
	}
	
}
