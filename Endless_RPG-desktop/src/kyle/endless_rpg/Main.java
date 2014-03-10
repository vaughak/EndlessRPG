package kyle.endless_rpg;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Endless_RPG.TITLE + " v" + Endless_RPG.VERSION;
		cfg.vSyncEnabled = true;
		cfg.useGL20 = true;
	
		cfg.width = 480;
		cfg.height = 800;
		
/*		cfg.width = 480;
		cfg.height = 800;*/
    	Assets ass = new Assets();
        ass.manager.load(Assets.class);
		new LwjglApplication(new Endless_RPG(null), cfg);
	}
	
	public class DesktopGameOrientation implements GameOrientation{
		
		public void changeOrientation(orientationEnum orientation){

		}
		
		public int getOrientation(){
			return 1;
		}
	}
}
