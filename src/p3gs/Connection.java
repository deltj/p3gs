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

import java.net.InetSocketAddress;

/**
 * Base class for connections to external systems (servers)
 */
public abstract class Connection {
	
	//  Socket Address for the server to connect to
	protected InetSocketAddress isa = null;
	
	/**
	 * enum to describe current connection status
	 */
	public enum ConnectionStatus {
		NotConnected,
		WaitingForData,
		Connected
	}
	
	/**
<<<<<<< HEAD
	 * Specify the server to connect to
=======
	 * Specify the server to connecto to
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	 * @param host A hostname or IP Address
	 * @param port A port number
	 */
	public void setServer(String host, int port) {
		if (host.isEmpty()) {
			throw new IllegalArgumentException(String.format("Invalid host: %s", host));
		}
		
		if (port < 1 || port > 65545) {
			throw new IllegalArgumentException(String.format("Invalid port: %d", port));
		}
		
		isa = new InetSocketAddress(host, port);
	}
	
	/**
	 * Instruct the connection to connect to its currently configured server
	 */
	public abstract void connect();
	
	/**
	 * Instruct the connection to disconnect
	 */
	public abstract void disconnect();
	
	/**
	 * Ask the connection for its current status
	 * @return The current ConnectionStatus for this connection
	 */
	public abstract ConnectionStatus getConnectionStatus();
	
}
