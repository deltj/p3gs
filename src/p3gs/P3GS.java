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
 * Main class for the Phantom 3 Ground Station
 */
public class P3GS {
	
<<<<<<< HEAD
	public static String defaultControllerHostname = "192.168.1.1";
	public static int defaultControllerPort = 2345;
	
	public P3GS() {
		ControllerClient cc = new ControllerClient();
		cc.setServer(defaultControllerHostname, defaultControllerPort);
		cc.connect();
=======
	public P3GS() {
		ControllerClient cc = ControllerClient.getInstance();
		cc.setServer("192.168.1.1", 2345);

		AircraftClient ac = AircraftClient.getInstance();
		ac.setServer("192.168.1.2", 5678);
		
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	}

	public static void main(String[] args) {
		new P3GS();
	}

}
