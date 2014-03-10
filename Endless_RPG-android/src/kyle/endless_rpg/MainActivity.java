package kyle.endless_rpg;

import kyle.endless_rpg.GameOrientation.orientationEnum;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        Texture.setEnforcePotImages(false);

    	Assets ass = new Assets();
        ass.manager.load(Assets.class);
        
        
        initialize(new Endless_RPG(new AndroidGameOrientation()), cfg);
    }
    
    public class AndroidGameOrientation implements GameOrientation{
    	private final OrientationServiceApi service;
    	
    	public AndroidGameOrientation(){
    		service = new OrientationServiceApi();
    	}
    	
		public void changeOrientation(orientationEnum orientation) {
			service.changeOrientation(orientation);
		}
		
		public int getOrientation(){
			return service.getOrientation();
		}

    	
    }
    
    public class OrientationServiceApi{
    	public void changeOrientation(orientationEnum orientation){
    		
    		switch(orientation){
    		case PORTRAIT:
    			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    			break;
    		case LANDSCAPE:
    			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    			break;
    			
    		}
    	}
    	
    	public int getOrientation(){
    		return getResources().getConfiguration().orientation;

    	}
    }
}