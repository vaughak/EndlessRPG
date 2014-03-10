package kyle.endless_rpg.component;

import kyle.endless_rpg.UI.ProgressBar;
import kyle.endless_rpg.UI.TextProgressBar;
import kyle.endless_rpg.core.Component;

public class QuestComponent implements Component {
	public int experienceRewarded;
	public int questID;
	public float questStartTime;
	public float questEndTime;
	public float questDuration;
	public float questTime;
	public boolean questInProgress;
	public float questProgress;
	
	public TextProgressBar progressBar;
	
	
}
