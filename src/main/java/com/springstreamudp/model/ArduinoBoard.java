package com.springstreamudp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.springstreamudp.model.ClientFacade;
import com.springstreamudp.model.ServerData;
import com.springstreamudp.model.ServerData.ServerDataType;

public class ArduinoBoard implements ClientFacade {
	
	public static final int NUM_ANALOG_PINS = 6;
	public static final int NUM_DIGITAL_PINS = 10;
	
	private List<Pin<Integer>> pins = new ArrayList<>();
	private boolean isConnected;

	public ArduinoBoard(){

		isConnected = false; 

		for(int i = 0; i < NUM_ANALOG_PINS; i++){
			pins.add( new Pin<Integer>(i,ServerDataType.ANALOG_PIN, 0));
		}
		
		for(int i = 0; i < NUM_DIGITAL_PINS; i++){
			pins.add(new Pin<Integer>(i, ServerDataType.DIGITAL_PIN, 0));
		}
	}

	public boolean getConnectionState(){
		return isConnected;
	}
	
	public void setConnectionState(boolean state){
		isConnected = state;
	}
	
	public void setData(int dataId, ServerDataType type, int value){
		
		int pinIndex = Collections.binarySearch(pins, 
												new Pin<Integer>(dataId, type, 0), 
												new ArduinoBoardPinComparator());
		
		pins.get(pinIndex).setValue(value);
		
	}
	
	public List<? extends ServerData<Integer>> getData(){
		
		Collections.sort(pins, new ArduinoBoardPinComparator());
		return pins;
	}
	
	@Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("ArduinoBoard {");
    	
    	for (Pin<Integer> p : pins){
    		sb.append(p.toString());
    	}
    	
    	sb.append('}');
    	
    	return sb.toString();
    }
	
	class ArduinoBoardPinComparator implements Comparator<Pin<Integer>>{

		public int compare(Pin<Integer> one, Pin<Integer> two) {
			
			int compareByType = one.getType().compareTo(two.getType());
			if(compareByType != 0){
				return compareByType;
			}
			
			return one.getId().compareTo(two.getId());
		}
	}
}
