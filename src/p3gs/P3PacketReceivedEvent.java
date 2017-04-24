/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 * See license.txt or https://opensource.org/licenses/MIT for terms.
 */
package p3gs;

import java.util.EventObject;

/**
 * This event will be fired when a new packet is received
 */
public class P3PacketReceivedEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	//  The received packet
	public P3Packet packet;
	
	/**
	 * Constructor with source specification
	 */
	public P3PacketReceivedEvent(Object source) {
		super(source);
	}

}
