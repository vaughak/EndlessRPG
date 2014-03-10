package kyle.endless_rpg.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import kyle.endless_rpg.core.Engine;
import kyle.endless_rpg.core.Entity;


public abstract class EntitySystem {
	public int priority;
	
	private Array<Entity> entities;
	
	public EntitySystem()
	{
		this(0);
	}
	
	public EntitySystem(int priority)
	{
		this.priority = priority;
	}
	
	public void addedToEngine(Engine engine){
		
	}
	
	public void removedFromEngine(Engine engine){
		
	}
	
	public void update(float deltaTime){
		for(int i = 0; i < entities.size; i++){
			processEntity(entities.get(i), deltaTime);
		}
	}
	
	public abstract void processEntity(Entity entity, float deltaTime);

}
