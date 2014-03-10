package kyle.endless_rpg;

import java.util.Random;

import kyle.endless_rpg.component.classes.ClassInterface;
import kyle.endless_rpg.component.races.RaceInterface;

public class StatsRoll {
	private String classSelection;
	private String raceSelection;
	
	private int str = -1, dex = -1, intel = -1, wis = -1, cha = -1, con = -1;
	
	private int strClassMod = -1, dexClassMod = -1, intClassMod = -1, wisClassMod = -1, chaClassMod = -1, conClassMod = -1;
	private int strRaceMod = -1, dexRaceMod = -1, intRaceMod = -1, wisRaceMod = -1, chaRaceMod = -1, conRaceMod = -1;
	private int strRoll, dexRoll, intRoll, wisRoll, chaRoll, conRoll;

	
	private int roll(){
		Random ranRoll = new Random();
		int roll = (ranRoll.nextInt(7-1)+1) + (ranRoll.nextInt(7-1)+1) + (ranRoll.nextInt(7-1)+1);
		return roll;		
	}
	
    public String modifierString(int cMod, int rMod)
    {

    	String modStr = "";
    	if(cMod + rMod < 0)
    	{
    		modStr = String.valueOf(cMod + rMod);
    	}
    	else
    	{
    		modStr = "+" + String.valueOf(cMod + rMod);
    	}
    	
    	return modStr;
    }	
   
    
    public int getStr(){
    	return str;
    }
    public int getDex(){
    	return dex;
    }
    public int getInt(){
    	return intel;
    }
    public int getWis(){
    	return wis;
    }
    public int getCon(){
    	return con;
    }
    public int getCha(){
    	return cha;
    }
    
	public String getStrString(){
		int r = strRoll + strClassMod + strRaceMod;
		String s = r + "(" + modifierString(strClassMod, strRaceMod) + ")";
		return s;
	}
	
	public String getDexString(){
		int r = dexRoll + dexClassMod + dexRaceMod;
		String s = r + "(" + modifierString(dexClassMod, dexRaceMod) + ")";
		return s;
	}
	
	public String getIntString(){
		int r = intRoll + intClassMod + intRaceMod;
		String s = r + "(" + modifierString(intClassMod, intRaceMod) + ")";
		return s;
	}
	
	public String getWisString(){
		int r = wisRoll + wisClassMod + wisRaceMod;
		String s = r + "(" + modifierString(wisClassMod, wisRaceMod) + ")";
		return s;
	}
	
	public String getChaString(){
		int r = chaRoll + chaClassMod + chaRaceMod;
		String s = r + "(" + modifierString(chaClassMod, chaRaceMod) + ")";
		return s;
	}
	
	public String getConString(){
		int r = conRoll + conClassMod + conRaceMod;
		String s = r + "(" + modifierString(conClassMod, conRaceMod) + ")";
		return s;
	}
    
    public void rollStats()
    {
    	strRoll = roll();
    	dexRoll = roll();
    	intRoll = roll();
    	wisRoll = roll();
    	conRoll = roll();
    	chaRoll = roll();
    	
 	   	str = strRoll + strClassMod + strRaceMod;
    	dex = dexRoll + dexClassMod + dexRaceMod;
    	intel = intRoll + intClassMod + intRaceMod;
    	wis = wisRoll + wisClassMod + wisRaceMod;
    	cha = chaRoll + chaClassMod + chaRaceMod;
    	con = conRoll + conClassMod + conRaceMod;
    }

	
	public void setRaceSelection(RaceInterface race){
		raceSelection = race.getRaceName();
		strRaceMod = race.getStrength();
		dexRaceMod = race.getDexterity();
		conRaceMod = race.getConstitution();
		chaRaceMod = race.getCharisma();
		wisRaceMod = race.getWisdom();
		intRaceMod = race.getIntelligence();
	}
	
	public void setClassSelection(ClassInterface race){
		classSelection = race.getClassName();
		strClassMod = race.getStrength();
		dexClassMod = race.getDexterity();
		conClassMod = race.getConstitution();
		chaClassMod = race.getCharisma();
		wisClassMod = race.getWisdom();
		intClassMod = race.getIntelligence();
	}



}
