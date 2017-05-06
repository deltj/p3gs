/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs;

import java.nio.channels.SocketChannel;

/**
 * This class manages the TCP/IP connection to the Phantom 3 controller.
 */
public class ControllerClient extends Connection implements P3PacketReceivedEventListener {
	
	//  SocketChannel for communicating with the server
	private SocketChannel sc = null;
	
	//  Worker thread that will receive and parse data from the server
	private P3PacketReceiveThread rt = new P3PacketReceiveThread();

	@Override
	public void connect() {
		try {
			sc = SocketChannel.open();
			sc.connect(isa);
			sc.configureBlocking(false);
			rt.setSocketChanel(sc);
			
			rt.addP3PacketReceivedEventListener(this);
			
			rt.start();
		} catch (Exception e) {
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
	public void p3PacketStatEventReceived(P3PacketReceivedEvent evt) {
		System.out.println(evt.packet.toString());
		System.out.println("Payload: " + evt.packet.getPayloadByteString());		
	}
}
