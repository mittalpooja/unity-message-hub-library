package com.unity.messagehub.library;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Message {
	public byte getMessageType();
	
	public void send(DataOutputStream out) throws IOException;
}
