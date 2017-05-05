<<<<<<< HEAD
/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs;

public class P3ControllerPacket extends P3Packet {
	
	/**
	 * This constructor copies the specified buffer to this packet
	 * @param packetbuffer
	 */
	public P3ControllerPacket(byte[] packetbuffer) {
		super(packetbuffer);
	}

	/**
	 * @return The value of the left stick's horizontal axis
	 */
	public short getLeftStickHorizontal() {
		return (short)((buffer[18] & 0xff) << 8 | (buffer[17] & 0xff));
	}
	
	/**
	 * @return The value of the left stick's vertical axis
	 */
	public short getLeftStickVertical() {
		return (short)((buffer[16] & 0xff) << 8 | (buffer[15] & 0xff));
	}
	
	/**
	 * @return The value of the right stick's horizontal axis
	 */
	public short getRightStickHorizontal() {
		return (short)((buffer[12] & 0xff) << 8 | (buffer[11] & 0xff));
	}
	
	/**
	 * @return The value of the right stick's vertical axis
	 */
	public short getRightStickVertical() {
		return (short)((buffer[14] & 0xff) << 8 | (buffer[13] & 0xff));
=======
package p3gs;

public class P3ControllerPacket extends P3Packet {
	protected int rightStickLeftRight;
	protected int rightStickUpDown;
	protected int leftStickLeftRight;
	protected int leftStickUpDown;

	public P3ControllerPacket(byte[] packetbuffer) {
		super(packetbuffer);
		rightStickLeftRight = (buffer[12] & 0xff) << 8 | (buffer[11] & 0xff);
		rightStickUpDown = (buffer[14] & 0xff) << 8 | (buffer[13] & 0xff);
		leftStickLeftRight = (buffer[18] & 0xff) << 8 | (buffer[17] & 0xff);
		leftStickUpDown = (buffer[16] & 0xff) << 8 | (buffer[15] & 0xff);
	}

	@Override
	public String toString() {
		return super.toString() +
				"  Right Stick Left/Right: " + rightStickLeftRight + "\n" +
				"  Right Stick Up/Down: " + rightStickUpDown + "\n" +
				"  Left Stick Left/Right: " + leftStickLeftRight + "\n" +
				"  Left Stick Up/Down: " + leftStickUpDown + "\n";
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	}
}
