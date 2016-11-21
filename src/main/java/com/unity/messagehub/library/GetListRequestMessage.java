package com.unity.messagehub.library;

import java.io.DataOutputStream;
import java.io.IOException;

import com.unity.messagehub.library.Message;

public class GetListRequestMessage implements Message {

	public byte getMessageType() {
		return MessageHubProtocol.GET_LIST_REQUEST;
	}

	public void send(DataOutputStream out) throws IOException {
		out.writeByte(this.getMessageType());
		out.flush();
	}
}
