/**
 * 
 */
package client;

import java.util.ArrayList;
import java.util.Iterator;

// ===================
// TODO 9/3/19 11h22
// ===================


/**
 * @author chenqun
 *
 */
public class CapteurGroupe implements CapteurComposant {
	
	ArrayList<CapteurComposant> capteurComposants = new ArrayList<CapteurComposant>();
	
	String groupName;
	
	
	public CapteurGroupe(String newGroupName){
		
		groupName = newGroupName;
		
	}
	
	public String getGroupName() {
		
		return groupName; 
		
	}
	
	public void add(CapteurComposant newSongComponent) {
		
		capteurComposants.add(newSongComponent);
		
	}
	
	public void remove(CapteurComposant newSongComponent) {
		
		capteurComposants.remove(newSongComponent);
		
	}
	
	public CapteurComposant getComposant(int componentIndex) {
		
		return (CapteurComposant)capteurComposants.get(componentIndex);
		
	}
	
	public void displayCapteurInfo(){
		
		System.out.println(getGroupName() + "\n");
		
		Iterator<CapteurComposant> capteurIterator = capteurComposants.iterator();
		
		while(capteurIterator.hasNext()) { 
			
			CapteurComposant capteurInfo = capteurIterator.next();
			
		    capteurInfo.displayCapteurInfo();
			
		}
		
	}

	public String getCapteurLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<CapteurComposant> getcapteurComposants() {
		
		return capteurComposants;
	}

	@Override
	public String getCapteurValueString() {
		// TODO Auto-generated method stub
		return null;
	}

}
