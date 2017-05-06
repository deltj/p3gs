/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/*
public class AircraftClient extends Connection implements Observer {
	
	//  Singleton instance
	private static AircraftClient instance;
	
	//  SocketChannel for communicating with the server
	private SocketChannel sc = null;
	
	//  Worker thread that will receive and parse data from the server
	private P3PacketReceiveThread rt = new P3PacketReceiveThread();
	
	//  Phantom 3 packet parser
	private P3Parser parser = new P3Parser();
	
	//  ArrayList of event listeners
	private ArrayList<P3PacketReceivedEventListener> packetListeners = null;

	public static AircraftClient getInstance() {
		if (instance == null) {
			instance = new AircraftClient();
		}
		return instance;
	}
	
	private AircraftClient() {
		packetListeners = new ArrayList<P3PacketReceivedEventListener>();
		
		//  Tell the P3Parser that we want to be notified about new packets
		//  being received
		parser.addObserver(this);
	}

	public void addP3PacketReceivedEventListener(P3PacketReceivedEventListener l) {
		this.packetListeners.add(l);
	}
	
	public void removeP3PacketReceivedEventListener(P3PacketReceivedEventListener l) {
		this.packetListeners.remove(l);
	}

	protected void fireP3PacketReceivedEvent(P3Packet pkt) {
		P3PacketReceivedEvent pre = new P3PacketReceivedEvent(this);
		pre.packet = pkt;
		
		for (P3PacketReceivedEventListener l : packetListeners) {
			l.p3PacketStatEventReceived(pre);
		}
	}

	@Override
	public void connect() {
		try {
			sc = SocketChannel.open();
			sc.connect(isa);
			sc.configureBlocking(false);
			rt.setSocketChanel(sc);
			rt.setParser(parser);
			rt.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		rt.kill();
	}

	@Override
	public ConnectionStatus getConnectionStatus() {
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO: use invokeLater or something to make sure this is happening on
		//the event dispatch thread
		P3Parser parser = (P3Parser)o;
		while (parser.hasPackets()) {
			fireP3PacketReceivedEvent(parser.getNextPacket());
		}
	}
}
*/