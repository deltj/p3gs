/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 * See license.txt or https://opensource.org/licenses/MIT for terms.
 */
package p3gs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * A thread to receive P3Packets from a SocketChannel
 */
class P3PacketReceiveThread extends Thread {
	
	//  SocketChannel for that this thread will read data from
	private SocketChannel sc = null;
	
	//  ByteBuffer for receiving data from the SocketChannel
	private ByteBuffer bb = ByteBuffer.allocate(1000);
	
	//  The parser
	private P3Parser parser = null;
	
	//  Flag to terminate the thread
	private boolean die = false;
	
	//  Specify the P3Parser instance
	public void setParser(P3Parser p3p) {
		parser = p3p;
	}
	
	//  Specify the SocketChannel instance
	public void setSocketChanel(SocketChannel sockchan) {
		sc = sockchan;
	}
	
	//  Constructor with SocketChannel specification
	public P3PacketReceiveThread() {
	}
	
	//  Kill the thread
	public void kill() {
		die = true;
	}
	
	//  The function that does all the work
	public void run() {
		int bytesReceived;
		try {
			while (!die) {
				while ((bytesReceived = sc.read(bb)) > 0) {
					 System.out.println("Receive thread received " + bytesReceived + " bytes");

					 //  Flip the buffer to read mode
					 bb.flip();
					 
					 //  Parse the buffer
					 parser.parse(bb);
				 }
			}
			System.out.println("Shutting down ReceiveThread");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
