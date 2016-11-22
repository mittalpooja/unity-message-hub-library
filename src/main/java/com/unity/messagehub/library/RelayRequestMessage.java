package com.unity.messagehub.library;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.unity.messagehub.library.Message;
import com.unity.messagehub.library.MessageHubProtocol;

public class RelayRequestMessage implements Message {
	
	byte numReceivers;
	int messageSize;
	long[] receivers; 
	byte[] message;
		
	public byte getMessageType() {
		return MessageHubProtocol.RELAY_REQUEST;
	}

	public RelayRequestMessage(String message, long[] receivers) {
		this.receivers = receivers;
		this.message = message.getBytes();
		this.messageSize = this.message.length;
		this.numReceivers = (byte) this.receivers.length;
	}

	// RELAY request message
	// HEADER
	// 1. Reserve 1 byte for the message type
	// 2. Reserve 1 byte for the number of receivers (n)
	// 3. Reserve 4 bytes for the message length (k)  
	//     (NOTE: can be optimized to 20 bits)
	// 
	// PAYLOAD
	// 4. n*8 bytes for the receivers
	// 5. k bytes for the message
	public RelayRequestMessage(DataInputStream in){
		try {
			this.numReceivers = in.readByte();
			this.messageSize = in.readInt();
			
			this.receivers = new long[numReceivers];
			for (int i=0; i<numReceivers; i++) {
				receivers[i]=in.readLong();
			}
			this.message = new byte[messageSize];
			in.read(message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void send(DataOutputStream out) throws IOException {
		out.writeByte(this.numReceivers);
		out.writeInt(this.messageSize);
		for (int i=0; i<this.numReceivers; i++) {
			out.writeLong(this.receivers[i]);
		}
		out.write(this.message);
		out.flush();
	}
	
	public static RelayRequestMessage parseRelayMessage(String str){		
	    JSONParser parser = new JSONParser();
	    JSONObject jsonMap;
		try {
			jsonMap = (JSONObject) parser.parse(str);
	    
		    String[] list = ((String)jsonMap.get("receivers")).split(",");
		    String msgToRelay = (String) jsonMap.get("message");
		    
		    long[] receivers = new long[list.length];		    
		    for (int i=0; i<list.length; i++) {
		    	receivers[i] = Long.parseLong(list[i]);
		    }
			return new RelayRequestMessage(msgToRelay, receivers);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte getNumReceivers() {
		return numReceivers;
	}

	public void setNumReceivers(byte numReceivers) {
		this.numReceivers = numReceivers;
	}

	public int getMessageSize() {
		return messageSize;
	}

	public void setMessageSize(int messageSize) {
		this.messageSize = messageSize;
	}

	public long[] getReceivers() {
		return receivers;
	}

	public void setReceivers(long[] receivers) {
		this.receivers = receivers;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}
}
