package kyle.endless_rpg.core;

import java.util.HashMap;
import java.util.Iterator;

import kyle.endless_rpg.system.EntitySystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class Entity {
	private static int nextIndex;
	
	private int index;
	HashMap<Class<? extends Component>, Component> components;
	private ObjectMap<Class<?>, EntitySystem> systemsByClass;
	Array<EntitySystem> systems;
	Array<Component> componentArray;

	
	public Entity(){
		components = new HashMap<Class<? extends Component>, Component>();
		index = nextIndex++;
		systems = new Array<EntitySystem>();
		componentArray = new Array<Component>();
		systemsByClass = new ObjectMap<Class<?>, EntitySystem>();
	}
	
	/**Adds a given component to the entity. */
	public Entity add(Component component){
		components.put(component.getClass(), component);
		componentArray.add(component);
		return this;
	}
	
	/** Removes the component of the given type. Returns the removed component or null */
	public Component remove(Class<? extends Component> componentType){
		Component removeComponent = components.get(componentType);
		
		if(removeComponent != null){
			components.remove(componentType);
			components.remove(componentType);
			componentArray.removeValue(removeComponent, false);
		}
		
		return removeComponent;
	}
	
	public Array<Component> getAllComponents(){
		return componentArray;
	}
	
	/**Removes all the components the entity has */
	public void removeAll(){
		Iterator it = components.entrySet().iterator();
		while(it.hasNext()){
			remove((Class<? extends Component>) it.next());
		}
		componentArray.removeAll(componentArray, false);

	}
	
	/**Gets the component from the type */
	public <T extends Component> T getComponent(Class<T> componentType){
		return componentType.cast(components.get(componentType));

	}
	
	/**Returns if the entity has the component */
	public boolean hasComponent(Class<? extends Component> componentType){
		return components.containsKey(componentType);
	}
	
	public void addSystem(EntitySystem system){
		Class<? extends EntitySystem> systemType = system.getClass();
		if(!systemsByClass.containsKey(systemType)){
			systems.add(system);
			systemsByClass.put(systemType,  system);
		}
	}
	
	public void removeSystem(EntitySystem system){
		systems.removeValue(system, true);
	}
	
	public <T extends EntitySystem> T getSystem(Class<T> systemType){
		return systemType.cast(systemsByClass.get(systemType));
	}
	
	public void update(float deltaTime){
		for(int i = 0; i < systems.size; i++){
			systems.get(i).processEntity(this, deltaTime);
		}
	}
	
	public int getID(){
		return index;
	}
	
	
	
	
	
	
	
	
	
	
	

}
