/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 * See license.txt or https://opensource.org/licenses/MIT for terms.
 */
package p3gs;

/**
 * Main class for the Phantom 3 Ground Station
 */
public class P3GS {
	
	public P3GS() {
		ControllerClient cc = ControllerClient.getInstance();
		cc.setServer("192.168.1.1", 2345);

		AircraftClient ac = AircraftClient.getInstance();
		ac.setServer("192.168.1.2", 5678);
		
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

	public static void main(String[] args) {
		new P3GS();
	}

}
