/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs;

/**
 * Main class for the Phantom 3 Ground Station
 */
public class P3GS {
	
	public static String defaultControllerHostname = "192.168.1.1";
	public static int defaultControllerPort = 2345;
	
	public P3GS() {
		ControllerClient cc = new ControllerClient();
		cc.setServer(defaultControllerHostname, defaultControllerPort);
		cc.connect();
	}

	public static void main(String[] args) {
		new P3GS();
	}

}
