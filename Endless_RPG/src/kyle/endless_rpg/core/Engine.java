package kyle.endless_rpg.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Engine 
{
	private Array<Entity> entities;

	
	public Engine(){
		entities = new Array<Entity>();
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity){
		entities.removeValue(entity, true);
	}
	
	public Entity getPlayerEntity(Class<? extends Component> componentType){
		for(int i = 0; i < entities.size; i++){
			if(entities.get(i).hasComponent(componentType)){
				return entities.get(i);
			}
		}
		
		return null;
	}
	
	public void update(float deltaTime){
		
		for(int i = 0; i < entities.size; i++){
			entities.get(i).update(deltaTime);
		}
	}
	
	
}
