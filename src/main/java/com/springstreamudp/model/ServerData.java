package com.springstreamudp.model;

public abstract class ServerData<T> {

	public static enum ServerDataType {
	ANALOG_PIN,
	DIGITAL_PIN
	}
	
	protected Integer dataId;
	protected T dataValue;
	protected ServerDataType dataType;
	
	
	public abstract T getValue();
	
	public abstract void setValue(T value);
	
	public abstract Integer getId();
	
	public abstract ServerDataType getType();
	
	@Override
    public String toString() {
        return "ServerData{" +
                "dataId=" + dataId +
                ", dataType='" + dataType + '\'' +
                ", dataValue='" + dataValue + '\'' +
                '}';
    }
}
