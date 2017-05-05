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
 * Interface for being notified about P3PacketReceivedEvents
 */
public interface P3PacketReceivedEventListener {
	
	/**
	 * This method will be called when P3Packets are received
	 */
	public void p3PacketStatEventReceived(P3PacketReceivedEvent evt);
}
