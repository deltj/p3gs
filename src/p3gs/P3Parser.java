<<<<<<< HEAD
/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to parse packets from the Phantom 3 servers.
 */
public class P3Parser {

	/**
	 * Attempt to find P3Packets in the specified ByteBuffer
	 * @param buf The buffer to parse
	 * @returns A list of P3Packets fouond
	 */
	public static List<P3Packet> parse(ByteBuffer buf) {
		List<P3Packet> retval = new ArrayList<P3Packet>();
		
		//  The header is 4 bytes
		while (buf.remaining() >= 4) {
			//System.out.println("buf.position = " + buf.position());
			//System.out.println("buf.remaining = "+ buf.remaining());
			byte[] header = new byte[4];
			
			//  Peek the first 4 bytes
			buf.duplicate().get(header, 0, 4);
			
			//  Validate magic byte
			if (header[0] != P3Packet.DJI_PREAMBLE) {
				//  Invalid magic, discard this byte
				System.out.println("Warning: discarding byte");
				buf.get();
				continue;
			}
			
			//  2nd byte should be packet length
			final int length = header[1];
			//System.out.println("Found packet with length " + length);
			
			//  Not sure about meaning of 3rd byte...
			
			//  4th byte seems to be packet type
			final int type = header[3] & 0xff;
			//System.out.println("TYPE: " + type);
			
			//  Do we have enough data to read the entire packet?
			if (buf.remaining() >= length) {
				//  Remove length bytes from the ByteBuffer
				byte[] packetbuffer = new byte[length];
				buf.get(packetbuffer, 0, length);
				
				//  Instantiate the correct kind of P3Packet based on the
				//  specified type
				P3Packet packet = null;
				switch (type) {
				//case P3Packet.P3PACKET_TYPE_CONTROLLER:
					//packet = new P3ControllerPacket(packetbuffer);
					//break;
				
				default:
					packet = new P3Packet(packetbuffer);
					break;
				}
				
				if (packet != null) {
					//  Dump the packet description to stdout
					//System.out.println(packet.toString());
					
					//  Add the packet to the return list
					retval.add(packet);
				}
			} else {
				System.out.println("Fragd");
				buf.compact();
				break;
			}
		}
		//System.out.println("buf.remaining(): " + buf.remaining());
		
		if (buf.remaining() == 0) {
			//  Flip buffer to write mode
			buf.flip();
		}
		
		return retval;
	}
}
=======
/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 * See license.txt or https://opensource.org/licenses/MIT for terms.
 */
package p3gs;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Class to parse packets from the Phantom 3 servers.
 */
public class P3Parser extends Observable {
	//  Magic byte that appears at the beginning of all messages
	private static final char magic = 0x55;
	
	//  A queue to be filled with received messages
	Deque<P3Packet> packetQueue;

	/**
	 * Constructor
	 */
	public P3Parser() {
		packetQueue = new LinkedList<P3Packet>();
	}
	
	/**
	 * Attempt to find P3Packets in the specified ByteBuffer
	 * @param buf The buffer to parse
	 */
	public void parse(ByteBuffer buf) {
		/*
		byte[] dumb = new byte[buf.remaining()];
		buf.duplicate().get(dumb);
		String dumbStr = "";
		for (int i=0; i<buf.remaining(); ++i) {
			dumbStr += String.format("%02X ", dumb[i]);
		}
		System.out.println("asdf; " + dumbStr);
		*/
		
		while (buf.remaining() >= 4) {
			//System.out.println("buf.position = " + buf.position());
			//System.out.println("buf.remaining = "+ buf.remaining());
			byte[] header = new byte[4];
			
			//  Peek the first 4 bytes
			buf.duplicate().get(header, 0, 4);
			
			//  Validate magic byte
			if (header[0] != magic) {
				//  Invalid magic, discard this byte
				System.out.println("Warning: discarding byte");
				buf.get();
				continue;
			}
			
			//  2nd byte should be packet length
			final int length = header[1];
			//System.out.println("Found packet with length " + length);
			
			//  Not sure about meaning of 3rd byte...
			
			//  4th byte seems to be packet type
			final int type = header[3] & 0xff;
			//System.out.println("TYPE: " + type);
			
			//  Do we have enough data to read the entire packet?
			if (buf.remaining() >= length) {
				//  Remove length bytes from the ByteBuffer
				byte[] packetbuffer = new byte[length];
				buf.get(packetbuffer, 0, length);
				
				//  Instantiate the right kind of P3Packet based on the
				//  specified type
				P3Packet packet = null;
				switch (type) {
				case P3Packet.P3PACKET_TYPE_CONTROLLER:
					packet = new P3ControllerPacket(packetbuffer);
					break;
				default:
					packet = new P3Packet(packetbuffer);
					break;
				}
				
				if (packet != null) {
					//  Dump the packet description to stdout
					//System.out.println(packet.toString());
					
					//  Add the packet to the packet queue
					packetQueue.push(packet);
					setChanged();
					notifyObservers();
				}
			} else {
				System.out.println("Fragd");
				buf.compact();
				break;
			}
		}
		//System.out.println("buf.remaining(): " + buf.remaining());
		
		if (buf.remaining() == 0) {
			//  Flip buffer to write mode
			buf.flip();
		}
	}
	
	/**
	 * Find out if the parser has any packets
	 * @return true if the parser has packets, false otherwise
	 */
	public boolean hasPackets() {
		return packetQueue.size() > 0;
	}
	
	/**
	 * Retrieve the next packet in the queue
	 * @return The next packet in the queue, or null if there are no packets
	 * to retrieve
	 */
	public P3Packet getNextPacket() {
		return packetQueue.pollLast();
	}
}
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
