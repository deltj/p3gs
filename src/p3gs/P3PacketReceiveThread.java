/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * A thread to receive P3Packets from a SocketChannel
 */
class P3PacketReceiveThread extends Thread {
	
	//  SocketChannel for that this thread will read data from
	private SocketChannel sc = null;
	
	//  ByteBuffer for receiving data from the SocketChannel
	private ByteBuffer bb = ByteBuffer.allocate(10000);
	
	//  Flag to terminate the thread
	private boolean die = false;
	
	//  List of listeners
	private ArrayList<P3PacketReceivedEventListener> packetListeners = null;
	
	/**
	 * Specify the SocketChannel instance
	 * @param sockchan
	 */
	public void setSocketChanel(SocketChannel sockchan) {
		sc = sockchan;
	}
	
	/**
	 * Tell the thread to terminate
	 */
	public void kill() {
		die = true;
	}
	
	/**
	 * The function that does all the work
	 */
	public void run() {
		int bytesReceived = 0;
		try {
			while (!die) {
				while ((bytesReceived = sc.read(bb)) > 0) {
					 System.out.println("Receive thread received " + bytesReceived + " bytes");

					 //  Flip the buffer to read mode
					 bb.flip();
					 
					 //  Parse the buffer
					 List<P3Packet> pkts = P3Parser.parse(bb);
					 
					 //  We've received zero or more P3Packets
					 for (P3Packet pkt : pkts) {
						 if (pkt.validate()) {
							 //  Packet is valid, notify listeners
							 fireP3PacketReceivedEvent(pkt);
						 } else {
							 System.err.println("Invalid packet");
						 }
					 }
				 }
			}
			System.out.println("Shutting down ReceiveThread");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Add the specified listener
	 * @param l The listener to add
	 */
	public void addP3PacketReceivedEventListener(P3PacketReceivedEventListener l) {
		this.packetListeners.add(l);
	}
	
	/**
	 * Remove the specified listener
	 * @param l The listener to remove
	 */
	public void removeP3PacketReceivedEventListener(P3PacketReceivedEventListener l) {
		this.packetListeners.remove(l);
	}
	
	/** 
	 * Notify all listeners that a new P3Packet has been received
	 * @param pkt The P3Packet which caused this event
	 */
	protected void fireP3PacketReceivedEvent(P3Packet pkt) {
		P3PacketReceivedEvent pre = new P3PacketReceivedEvent(this);
		pre.packet = pkt;
		
		for (P3PacketReceivedEventListener l : packetListeners) {
			l.p3PacketStatEventReceived(pre);
		}
	}
}
