package kyle.endless_rpg.system;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.Rarity;
import kyle.endless_rpg.UI.PlayMenuBuilder;
import kyle.endless_rpg.UI.ProgressBar;
import kyle.endless_rpg.UI.TextProgressBar;
import kyle.endless_rpg.component.QuestComponent;
import kyle.endless_rpg.component.StatsComponent;
import kyle.endless_rpg.core.Entity;
import kyle.endless_rpg.screens.ScreenScaling;


public class QuestSystem extends EntitySystem {
	QuestComponent quest;
	StatsComponent stats;

	
	public QuestSystem(Entity entity){
		quest = entity.getComponent(QuestComponent.class);
		stats = entity.getComponent(StatsComponent.class);
		
		ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(Assets.manager.get(Assets.progressBarEmpty, Texture.class),Assets.manager.get(Assets.progressBarFull, Texture.class), 40);
		quest.progressBar = new TextProgressBar("Progress", 0, 100, .001f, false, barStyle, null, ScreenScaling.VIRTUAL_WIDTH, 40);

	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		
		quest.questTime += deltaTime;
		quest.questProgress = quest.questTime /quest.questDuration;
		quest.progressBar.setValue(quest.questTime);
		
		if(!quest.questInProgress)
		{
			createQuest(entity);
			quest.questInProgress = true;
			PlayMenuBuilder.logInput.add("You start a new quest! It'll take " + quest.questDuration + " seconds to complete.");
			quest.progressBar.setRange(0, quest.questDuration);
			
			//System.out.print("Quest Component Info " + quest.questDuration+ "\n");
			//PlayMenuBuilder.updateCombatLog();
		}
		
		if(quest.questTime >= quest.questDuration)
		{
			quest.questTime -= quest.questDuration;
			quest.questInProgress = false;
			stats.experience += quest.experienceRewarded;
			PlayMenuBuilder.logInput.add("You earn " + quest.experienceRewarded + " experience!");
			
			quest.questProgress = 0;
			quest.questTime = 0;
			quest.questDuration = 0;
			//PlayMenuBuilder.newStr = "You earn " + quest.experienceRewarded + " experience!";
			//PlayMenuBuilder.updateCombatLog();
		}
		
	}

	
	private void createQuest(Entity entity){
		double min, max;
		
		min = Math.sqrt(stats.level);
		max = min + stats.level;
		min = min * 30;
		max = max * 30;
		
		Random r = new Random();
		quest.questDuration = r.nextInt((int)max - (int)min +1) + (int)min;
		//10 * (lvl ^ 1.5) = amount of common mobs per level
		double mobKillsToLevel = (Math.pow(stats.level, 1.5f));
		double rarity = Rarity.rarity();

		int xp = (int)((stats.experienceToLevel / mobKillsToLevel) + (stats.experienceToLevel / mobKillsToLevel) * rarity);
		quest.experienceRewarded = (int)((stats.experienceToLevel / mobKillsToLevel) + (stats.experienceToLevel / mobKillsToLevel) * rarity);

	}

}
