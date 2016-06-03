package com.springstreamudp.model;

public class Pin<T> extends ServerData<T> {
	


	Pin(int id, ServerDataType type, T defaultValue){
		dataId = id;
		dataType = type;
		dataValue = defaultValue;
	}
	
	@Override
	public T getValue(){
		return dataValue;
	}
	
	@Override
	public void setValue(T value){
		dataValue = value;
	}
	
	@Override
	public Integer getId(){
		return dataId;
	}
	
	@Override
	public ServerDataType getType(){
		return dataType;
	}
}
