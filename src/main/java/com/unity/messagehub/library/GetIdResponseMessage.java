package com.unity.messagehub.library;

import java.io.DataOutputStream;
import java.io.IOException;

import com.unity.messagehub.library.Message;
import com.unity.messagehub.library.MessageHubProtocol;

public class GetIdResponseMessage implements Message {

	long id; 
	
	public byte getMessageType() {
		return MessageHubProtocol.GET_ID_RESPONSE;
	}

	public GetIdResponseMessage(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Byte.toString(this.getMessageType())+":"+String.valueOf(id);
	}

	public void send(DataOutputStream out) throws IOException {
		out.writeByte(this.getMessageType());
		out.writeLong(this.id);
		out.flush();
	}	
}
