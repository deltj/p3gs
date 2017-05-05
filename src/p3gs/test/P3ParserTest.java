/**
 * Copyright 2017 Ted DeLoggio
 * 
 * Reuse permitted under the terms of the MIT open source license.
 */
package p3gs.test;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;
import java.util.List;

import org.junit.Test;

import p3gs.P3Packet;
import p3gs.P3Parser;

/**
 * Unit tests for P3Parser
 */
public class P3ParserTest {

	/**
	 * Verify that the parse function works for a valid and complete message
	 */
	@Test
	public void testParse() {
		byte[] buf = {
				(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
				(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
				(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0xd7, (byte)0x5b };
		
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.put(buf);
		bb.flip();
		
		List<P3Packet> pkts = P3Parser.parse(bb);
		
		assertEquals(1, pkts.size());
	}
	
	/**
	 * Verify that the parse function works for a fragmented message
	 */
	@Test
	public void testParseFrag() {
		byte[] buf1 = {
				(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
				(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
				(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00 };
		
		byte[] buf2 = {
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0xd7, (byte)0x5b };
		
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.put(buf1);
	
		bb.flip();		
		List<P3Packet> pkts = P3Parser.parse(bb);
		
		assertEquals(0, pkts.size());
		
		bb.put(buf2);
		bb.flip();
		pkts = P3Parser.parse(bb);
		
		assertEquals(1, pkts.size());
	}

	/**
	 * Verify that the parse function works for multiple messages
	 */
	@Test
	public void testParseMulti() {
		byte[] buf = {
				(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
				(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
				(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0xd7, (byte)0x5b };
		
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.put(buf);
		bb.put(buf);
		bb.put(buf);
	
		bb.flip();		
		List<P3Packet> pkts = P3Parser.parse(bb);
		
		assertEquals(3, pkts.size());
	}
	
	/**
	 * Verify that the parse function works with garbage data before a valid message
	 */
	@Test
	public void testParsePreGarbage() {
		byte[] buf = {
				(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
				(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
				(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0xd7, (byte)0x5b };
		
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.put(buf);
	
		bb.flip();		
		List<P3Packet> pkts = P3Parser.parse(bb);
		
		assertEquals(1, pkts.size());
	}
	
	/**
	 * Verify that the parse function works with garbage data in between valid messages
	 */
	@Test
	public void testParseMidGarbage() {
		byte[] buf = {
				(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
				(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
				(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0xd7, (byte)0x5b };
		
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.put(buf);
		bb.put(buf);
		bb.put(buf);
	
		bb.flip();		
		List<P3Packet> pkts = P3Parser.parse(bb);
		
		assertEquals(3, pkts.size());
	}
	
	/**
	 * Verify that the parse function works with garbage data after a valid message
	 */
	@Test
	public void testParsePostGarbage() {
		byte[] buf = {
				(byte)0x55, (byte)0x1a, (byte)0x04, (byte)0xb1,
				(byte)0x0e, (byte)0x02, (byte)0x4c, (byte)0x2d,
				(byte)0x00, (byte)0x06, (byte)0x05, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x04, (byte)0x00,
				(byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00,
				(byte)0xd7, (byte)0x5b, (byte)0x00, (byte)0x00, 
				(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, };
		
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.put(buf);
	
		bb.flip();		
		List<P3Packet> pkts = P3Parser.parse(bb);
		
		assertEquals(1, pkts.size());
	}
}
