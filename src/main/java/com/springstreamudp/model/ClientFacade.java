package com.springstreamudp.model;

import java.util.List;

import com.springstreamudp.model.ServerData.ServerDataType;

public interface ClientFacade {

	public List<? extends ServerData<?>> getData();
	public void setData(int dataId, ServerDataType type, int value);
	public boolean getConnectionState();
	public void setConnectionState(boolean state);
}
