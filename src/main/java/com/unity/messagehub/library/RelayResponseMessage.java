package com.unity.messagehub.library;

import java.io.DataOutputStream;
import java.io.IOException;

import com.unity.messagehub.library.MessageHubProtocol;

// RELAY_RESPONSE message type
// HEADER
// 1. Reserve 1 byte for the message type
// 2. Reserve 4 bytes for the message length (k)  
//     (NOTE: can be optimized to 20 bits as Maximum Message Length = 2^20 bytes)
// 
// PAYLOAD
// 3. k bytes for the message
public class RelayResponseMessage implements Message{

	int messageSize; 
	byte[] message;
	private long[] receivers;
	
	public byte getMessageType() {
		return MessageHubProtocol.RELAY_RESPONSE;
	}

	public RelayResponseMessage(int messageSize, byte[] message, long[] receivers) {
		this.messageSize = messageSize;
		this.message = message;
		this.setReceivers(receivers);
	}

	//@Override
	//public String toString() {
	//	return Byte.toString(this.getMessageType())+":"+String.valueOf(id);
	//}

	public void send(DataOutputStream out) throws IOException {
		out.writeByte(this.getMessageType());
		out.writeInt(this.messageSize);
		out.write(message);
		out.flush();
	}

	public long[] getReceivers() {
		return receivers;
	}

	public void setReceivers(long[] receivers) {
		this.receivers = receivers;
	}
}
