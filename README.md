# unity-message-hub-library
This is the library for Message Hub

	// GET_ID Request type
	// HEADER (1 byte)
	public static final byte GET_ID_REQUEST=0x1; 
	
	// GET_LIST Request type
	// HEADER (1 byte)
	public static final byte GET_LIST_REQUEST=0x2;
	
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
	public static final byte RELAY_REQUEST=0x3;	
	
	// GET_ID_RESPONSE Message will 
	// 1. Reserve 1 byte for the message type
	// 
	// PAYLOAD
	// 2. will have Payload of 8 bytes (id = 64 bits) 
	// FIXED MESSAGE SIZE = 9 bytes
	public static final byte GET_ID_RESPONSE=0x4; 
	
	// GET_LIST_RESPONSE Message will 
	// HEADER (2 bytes)
	// 1. Reserve 1 byte for the message type
	// (NOTE: Assuming 255 maximum connections)
	// 2. Reserve 1 byte for number of clients connected to the hub (n)
	//
	// PAYLOAD
	// 3. Payload = n * 8 bytes 
	public static final byte GET_LIST_RESPONSE=0x5; 
	
	// RELAY_RESPONSE message type
	// HEADER
	// 1. Reserve 1 byte for the message type
	// 2. Reserve 4 bytes for the message length (k)  
	//     (NOTE: can be optimized to 20 bits as Maximum Message Length = 2^20 bytes)
	// 
	// PAYLOAD
	// 3. k bytes for the message
	public static final byte RELAY_RESPONSE=0x6;
