# unity-message-hub-library
This is the library for Message Hub

There are 6 types of messages that are supported by this library. We are reserving 1 Byte for the message type that can accomodate upto 256 message types. 

	// TYPE 1: GET_ID Request type
	// HEADER (1 byte)
	// NOTE: Future Improvements : Message type can be optimized to 3 bits
	public static final byte GET_ID_REQUEST=0x1; 	
	
	// TYPE 2: GET_LIST Request type
	// HEADER (1 byte)
	public static final byte GET_LIST_REQUEST=0x2;
	
	// TYPE 3: RELAY request message
	// HEADER
	// 1. Reserve 1 byte for the message type
	// 2. Reserve 1 byte for the number of receivers (n) : (Assumption - maximum 255 receivers)
	// 3. Reserve 4 bytes for the message length (k)  
	//     (NOTE: can be optimized to 20 bits)
	// 
	// PAYLOAD
	// 4. n*8 bytes for the receivers
	// 5. k bytes for the message
	public static final byte RELAY_REQUEST=0x3;	
	
	// TYPE 4: GET_ID_RESPONSE Message will 
	// 1. Reserve 1 byte for the message type
	// 
	// PAYLOAD
	// 2. will have Payload of 8 bytes (id = 64 bits) 
	// FIXED MESSAGE SIZE = 9 bytes
	public static final byte GET_ID_RESPONSE=0x4; 
	
	// TYPE 5: GET_LIST_RESPONSE Message will 
	// HEADER (2 bytes)
	// 1. Reserve 1 byte for the message type
	//
	// 2. Reserve 1 byte for number of clients connected to the hub (n) - (NOTE: Assuming 255 maximum connections)
	//
	// PAYLOAD
	// 3. Payload = n * 8 bytes 
	public static final byte GET_LIST_RESPONSE=0x5; 
	
	// TYPE 6: RELAY_RESPONSE message type
	// HEADER
	// 1. Reserve 1 byte for the message type
	// 2. Reserve 4 bytes for the message length (k)  
	//     (NOTE: can be optimized to 20 bits as Maximum Message Length = 2^20 bytes)
	// 
	// PAYLOAD
	// 3. k bytes for the message
	public static final byte RELAY_RESPONSE=0x6;
