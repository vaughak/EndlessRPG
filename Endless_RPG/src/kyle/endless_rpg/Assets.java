package kyle.endless_rpg;

import kyle.endless_rpg.AnnotationAssetManager.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets 
{
	//public static final AnnotationAssetManager manager = new AnnotationAssetManager();
	static public AnnotationAssetManager manager = null;
	
	public Assets(){
		manager = new AnnotationAssetManager();
	}
	
	@Asset(type = TextureAtlas.class)
	public static final String uiskinAtlas = "ui/uiskin.atlas";
	
	@Asset(type = TextureAtlas.class)
	public static final String playMenuButtons = "ui/playMenuButtons.pack";
	
	@Asset(type = TextureAtlas.class)
	public static final String playMenuUI = "ui/playMenu.pack";
	
	@Asset(type = Skin.class)
	public static final String uiskinJSON = "ui/uiskin.json";
	
	@Asset(type = Texture.class)
	public static final String mainBackgroundImg = "backgrounds/temp_background.png";
	
	@Asset(type = Texture.class)
	public static final String experienceFullImg = "ui/bars/experienceBarFull.png";
	
	@Asset(type = Texture.class)
	public static final String experienceEmptyImg = "ui/bars/experienceBarEmpty.png";
	
	@Asset(type = Texture.class)
	public static final String manaFullImg = "ui/bars/fullManaBar.png";
	
	@Asset(type = Texture.class)
	public static final String manaEmptyImg = "ui/bars/emptyManaBar.png";
	
	@Asset(type = Texture.class)
	public static final String healthFullImg = "ui/bars/testHealthBarFull.png";
	
	@Asset(type = Texture.class)
	public static final String healthEmptyImg = "ui/bars/emptyHealthBar.png";
	
	@Asset(type = Texture.class)
	public static final String combatLog = "ui/combatLog.png";
	
	@Asset(type = Texture.class)
	public static final String progressBarEmpty = "ui/bars/progressBarEmpty.png";
	
	@Asset(type = Texture.class)
	public static final String progressBarFull = "ui/bars/progressBarFull.png";
	
	@Asset(type = Texture.class)
	public static final String testProgressBarFull = "ui/bars/testProgressBarFull.png";
	
	

	
	public static void dispose(){
		manager.dispose();
		/*		manager = null;
		uiskinAtlas = null;
		playMenuButtons = null;
		playMenuUI = null;
		uiskinJSON = null;
		mainBackgroundImg = null;
		experienceFullImg = null;
		experienceEmptyImg = null;
		manaFullImg = null;
		manaEmptyImg = null;
		healthFullImg = null;
		healthEmptyImg = null;
		combatLog = null;
		progressBarEmpty = null;
		progressBarFull = null;*/
	}
	
}
