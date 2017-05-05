/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import p3gs.P3CRC;

/**
 * Unit tests for P3CRC
 */
public class P3CRCTest {

	@Test
	public void testCalcCRC1() {
		final byte[] testBytes = { (byte)0x55, (byte)0xaa, (byte)0x55, (byte)0xaa };
		
		final short crc = P3CRC.calculateCRC16(testBytes, testBytes.length);
		//System.out.println(String.format("crc: %04X", crc));
		
		assertEquals((short)0xe73d, crc);
	}

	@Test
	public void testCalcCRC2() {
		final byte[] testBytes = { (byte)0x00, (byte)0x11, (byte)0x22, (byte)0x33 };
		
		final short crc = P3CRC.calculateCRC16(testBytes, testBytes.length);
		//System.out.println(String.format("crc: %04X", crc));
		
		assertEquals((short)0x4b1c, crc);
	}
}
