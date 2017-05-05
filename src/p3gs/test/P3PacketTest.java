/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p3gs.P3Packet;

/**
 * Unit tests for P3Packet
 */
public class P3PacketTest {

	byte[] validMsgBuffer = {
			(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
			(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
			(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00,
			(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
			(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
			(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0xd7, (byte)0x5b };
	
	P3Packet validPkt;
	
	@Before
	public void setup() {
		validPkt = new P3Packet(validMsgBuffer);
	}
	
	/**
	 * Verify that the parse function works with a valid buffer
	 */
	@Test
	public void testParse() {
		System.out.println(validPkt.toString());
		System.out.println(validPkt.getPayloadByteString());
		assertTrue(validPkt.validate());
	}
	
	/**
	 * Verify that the getLength function works
	 */
	@Test
	public void testGetLength() {
		assertEquals(26, validPkt.getLength());
	}
	
	/**
	 * Verify that the getCRC function works
	 */
	@Test
	public void testGetCRC() {
		assertEquals((short)0x5bd7, validPkt.getCRC());
	}
	
	/**
	 * Verify that the getSourceTypeMain function works
	 */
	@Test
	public void testGetSourceTypeMain() {
		assertEquals(1, validPkt.getSourceTypeMain());
	}
	
	/**
	 * Verify that the getSourceTypeSub function works
	 */
	@Test
	public void testGetSourceTypeSub() {
		assertEquals(6, validPkt.getSourceTypeSub());
	}
	
	/**
	 * Verify that the parse function fails for an invalid preamble
	 */
	 @Test
	 public void testParseBadPreamble() {
		 byte[] buffer = {
					0x54, 0x1a, 0x04, (byte) 0xb1, 0x0e, 0x02, 0x4c, 0x2d,
					0x00, 0x06, 0x05, 0x00, 0x04, 0x00, 0x04, 0x00,
					0x04, 0x00, 0x04, 0x00, 0x04, 0x00, 0x00, 0x00,
					(byte) 0xd7, 0x5b };
			
		P3Packet pkt = new P3Packet(buffer);
			
		assertFalse(pkt.validate());
	 }
	 
	 /**
	 * Verify that the parse function fails for an invalid length
	 */
	 @Test
	 public void testParseBadLength() {
		 byte[] buffer = {
					0x55, 0x1b, 0x04, (byte) 0xb1, 0x0e, 0x02, 0x4c, 0x2d,
					0x00, 0x06, 0x05, 0x00, 0x04, 0x00, 0x04, 0x00,
					0x04, 0x00, 0x04, 0x00, 0x04, 0x00, 0x00, 0x00,
					(byte) 0xd7, 0x5b };
			
		P3Packet pkt = new P3Packet(buffer);
			
		assertFalse(pkt.validate());
	 }
	 
	/**
	 * Verify that the parse function fails for an invalid checksum
	 */
	 @Test
	 public void testParseBadChecksum() {
		 byte[] buffer = {
					0x55, 0x1a, 0x04, (byte) 0xb1, 0x0e, 0x02, 0x4c, 0x2d,
					0x00, 0x06, 0x05, 0x00, 0x04, 0x00, 0x04, 0x00,
					0x04, 0x00, 0x04, 0x00, 0x04, 0x00, 0x00, 0x00,
					(byte) 0xd7, 0x00 };
			
		P3Packet pkt = new P3Packet(buffer);
			
		assertFalse(pkt.validate());
	 }

}
