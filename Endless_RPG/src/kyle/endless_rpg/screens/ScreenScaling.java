package kyle.endless_rpg.screens;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ScreenScaling {
	public static final int VIRTUAL_WIDTH = 720;
	public static final int VIRTUAL_HEIGHT = 1280;
	public static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
	public static Rectangle viewport;
	
	public static void InitViewport(int width, int height){
		float aspectRatio = (float)width/(float)height;//NEW
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);
		
		if(aspectRatio > ASPECT_RATIO){
			scale = (float)height/(float)VIRTUAL_HEIGHT;
			crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
		}else if(aspectRatio < ASPECT_RATIO){
			scale = (float)width/(float)VIRTUAL_WIDTH;
			crop.x = (height - VIRTUAL_HEIGHT*scale)/2f;
		}else{
			scale = (float)width/(float)VIRTUAL_WIDTH;
		}
		
		float w = (float)VIRTUAL_WIDTH*scale;
		float h = (float)VIRTUAL_HEIGHT*scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);	
	}

}
