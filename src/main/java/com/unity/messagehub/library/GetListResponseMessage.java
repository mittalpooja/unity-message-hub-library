package com.unity.messagehub.library;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import com.unity.messagehub.library.Message;
import com.unity.messagehub.library.MessageHubProtocol;

public class GetListResponseMessage implements Message {
	
	List<Long> connectedClientIds;
	
	public byte getMessageType() {
		return MessageHubProtocol.GET_LIST_RESPONSE;
	}

	public GetListResponseMessage(List<Long> connectedClientIds) {
		this.connectedClientIds = connectedClientIds;
	}

	public void send(DataOutputStream out) throws IOException {
		out.writeByte(this.getMessageType());
		out.writeByte(this.connectedClientIds.size());
		for (long id: connectedClientIds) {
			out.writeLong(id);
		}
		out.flush();
	}	

	@Override
	public String toString() {
		return Byte.toString(this.getMessageType())+":"+connectedClientIds.toString();
	}

}
