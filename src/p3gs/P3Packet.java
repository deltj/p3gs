/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 * See license.txt or https://opensource.org/licenses/MIT for terms.
 */
package p3gs;

/**
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
	
	public byte[] getData() {
		return buffer.clone();
	}
	
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
			retval += String.format("%02X ", buffer[i]);
		}
		return retval;
	}
	public String toString() {
		return "P3Packet: \n" +
		       "  length: " + length + "\n" +
		       "  type: " + type + "\n" +
		       "  checksum: " + checksum + "\n" +
		       "  raw:" + getByteString() + "\n";
	}
}
