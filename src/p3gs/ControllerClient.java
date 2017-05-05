/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
<<<<<<< HEAD
 */
package p3gs;

import java.nio.channels.SocketChannel;

/**
 * This class manages the TCP/IP connection to the Phantom 3 controller.
 */
public class ControllerClient extends Connection implements P3PacketReceivedEventListener {
=======
 * See license.txt or https://opensource.org/licenses/MIT for terms.
 */
package p3gs;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * TCP/IP client for communicating with the DJI Phantom 3 controller.
 */
public class ControllerClient extends Connection implements Observer {
	
	//  Singleton instance
	private static ControllerClient instance = null;
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	
	//  SocketChannel for communicating with the server
	private SocketChannel sc = null;
	
	//  Worker thread that will receive and parse data from the server
	private P3PacketReceiveThread rt = new P3PacketReceiveThread();
<<<<<<< HEAD

=======
	
	//  Phantom 3 packet parser
	private P3Parser parser = new P3Parser();
	
	//  ArrayList of event listeners
	private ArrayList<P3PacketReceivedEventListener> packetListeners = null;
	
	/**
	 * Singleton get instance function
	 * @return The instance
	 */
	public static ControllerClient getInstance() {
		if (instance == null) {
			instance = new ControllerClient();
		}
		return instance;
	}
	
	/**
	 * Constructor
	 */
	private ControllerClient() {
		packetListeners = new ArrayList<P3PacketReceivedEventListener>();
		
		//  Tell the P3Parser that we want to be notified about new packets
		//  being received
		parser.addObserver(this);
	}
	
	/**
	 * Adds the specified event listener to the list of listeners
	 */
	public void addP3PacketReceivedEventListener(P3PacketReceivedEventListener l) {
		this.packetListeners.add(l);
	}
	
	/**
	 * Removes the specified event listener from the list of listeners
	 */
	public void removeP3PacketReceivedEventListener(P3PacketReceivedEventListener l) {
		this.packetListeners.remove(l);
	}
	
	/**
	 * Notifies all P3PacketReceivedEventListeners that a new packet has been received
	 */
	protected void fireP3PacketReceivedEvent(P3Packet pkt) {
		P3PacketReceivedEvent pre = new P3PacketReceivedEvent(this);
		pre.packet = pkt;
		
		for (P3PacketReceivedEventListener l : packetListeners) {
			l.p3PacketStatEventReceived(pre);
		}
	}

	/**
	 * override of Connection.connect
	 */
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	@Override
	public void connect() {
		try {
			sc = SocketChannel.open();
			sc.connect(isa);
			sc.configureBlocking(false);
			rt.setSocketChanel(sc);
<<<<<<< HEAD
			
			rt.addP3PacketReceivedEventListener(this);
			
			rt.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

=======
			rt.setParser(parser);
			rt.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * override of Connection.disconnect
	 */
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	@Override
	public void disconnect() {
		rt.kill();
	}

<<<<<<< HEAD
=======
	/**
	 * override of Connection.getConnectionStatus
	 */
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	@Override
	public ConnectionStatus getConnectionStatus() {
		return null;
	}

<<<<<<< HEAD
	@Override
	public void p3PacketStatEventReceived(P3PacketReceivedEvent evt) {
		System.out.println(evt.packet.toString());
		System.out.println("Payload: " + evt.packet.getPayloadByteString());		
=======
	/**
	 * override of Observable.update
	 */
	@Override
	public void update(Observable o, Object arg) {
		//TODO: use invokeLater or something to make sure this is happening on
		//the event dispatch thread
		P3Parser parser = (P3Parser)o;
		while (parser.hasPackets()) {
			fireP3PacketReceivedEvent(parser.getNextPacket());
		}
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	}
}
