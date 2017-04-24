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
	}
}
