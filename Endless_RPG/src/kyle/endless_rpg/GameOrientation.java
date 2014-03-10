package kyle.endless_rpg;

public interface GameOrientation 
{
	public void changeOrientation(orientationEnum orientation);
	
	public int getOrientation();
	
	public enum orientationEnum{PORTRAIT, LANDSCAPE};
}
