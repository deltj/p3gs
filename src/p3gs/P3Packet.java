/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
<<<<<<< HEAD
=======
 * See license.txt or https://opensource.org/licenses/MIT for terms.
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
 */
package p3gs;

/**
<<<<<<< HEAD
 * This is the base class for Phantom 3 packets.
 */
public class P3Packet {
	
	public static final int DJI_PREAMBLE = 0x55;
	//public static final int P3PACKET_TYPE_CONTROLLER = 0xb1;
	
	/**
	 * Byte array containing all of the bytes in this packet
	 */
	protected byte[] buffer;
	
	/**
	 * This constructor copies the specified buffer to this packet
	 * @param packetbuffer
	 */
	public P3Packet(byte[] packetbuffer) {
		//  Make a local copy of the packet's buffer
		buffer = packetbuffer.clone();
	}
	
	/**
	 * @return The total length of this packet, including the preamble and checksum
	 */
	public int getLength() {
		if (buffer.length < 3) {
			return 0;
		}
		
		int length = buffer[1];
		
		return length;
	}
	
	/**
	 * @return This packet's CRC
	 */
	public short getCRC() {
		short crc = buffer[buffer.length - 1];
		crc <<= 8;
		crc |= buffer[buffer.length - 2] & 0xff;
		
		return crc;
	}
	
	/**
	 * @return This packet's main source type
	 */
	public int getSourceTypeMain() {
		if (buffer.length >= 7) {
			return buffer[4] >> 3;
		} else {
			return 0;
		}
	}
	
	public int getSourceTypeSub() {
		if (buffer.length >= 7) {
			return buffer[4] & 0x07;
		} else {
			return 0;
		}
	}
	
	/**
	 * @return True if the packet is valid
	 */
	public boolean validate() {
		//  validate preamble
		if (buffer[0] != DJI_PREAMBLE) {
			return false;
		}
		
		//  validate length
		if (getLength() != buffer.length) {
			return false;
		}
		
		//  validate crc
		if (getCRC() != P3CRC.calculateCRC16(buffer, buffer.length - 2)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * @return A copy of the buffer
	 */
=======
 * Base class for Phantom 3 packets.
 */
public class P3Packet {
	protected byte[] buffer;
	protected int length;
	protected int type;
	
	public int getType() {
		return type;
	}
	
	public int getLength() {
		return length;
	}
	
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	public byte[] getData() {
		return buffer.clone();
	}
	
<<<<<<< HEAD
	/**
	 * @return A human-readable representation of this packet
	 */
	public String toString() {
		return "P3Packet { type: " + getSourceTypeMain() + ", subtype: " + getSourceTypeSub() + "}";
	}
	
	/**
	 * @return A hex representation of all the bytes in this packet
	 */
	public String getByteString() {
		String retval = "";
		for (byte b : buffer) {
			retval += String.format("%02X ", b);
		}
		return retval;
	}
	
	/**
	 * @return A hex representation of just the payload byte
	 */
	public String getPayloadByteString() {
		String retval = "";
		for (int i=4; i<buffer.length - 3; i++) {
=======
	protected short checksum;

	public static final int P3PACKET_TYPE_CONTROLLER = 177;
	
	public P3Packet(byte[] packetbuffer) {
		//  Make a local copy of the packet's buffer
		buffer = packetbuffer.clone();
		length = buffer[1] & 0xff;
		type = buffer[3] & 0xff;
		checksum = (short) (buffer[length - 1] << 8 | buffer[length - 2]);
	}

	public String getByteString() {
		String retval = "";
		for (int i=0; i<length; ++i) {
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
			retval += String.format("%02X ", buffer[i]);
		}
		return retval;
	}
<<<<<<< HEAD
=======
	public String toString() {
		return "P3Packet: \n" +
		       "  length: " + length + "\n" +
		       "  type: " + type + "\n" +
		       "  checksum: " + checksum + "\n" +
		       "  raw:" + getByteString() + "\n";
	}
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
}
