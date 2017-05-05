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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * JPanel to display information about the Phantom 3 Aircraft
 */
<<<<<<< HEAD
/*
=======
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
public class AircraftConnectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	
<<<<<<< HEAD
=======
	/**
	 * A class to describe some summary data about the types of packets received
	 */
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	private class PacketSummary {
		public int type;
		public long count;
		public byte[] data;
	}
	
	private ArrayList<PacketSummary> packetSummaryTable = new ArrayList<PacketSummary>();
	
	private static String byteArrayToHexString(byte[] ba) {
		String hexString = "";
		for (int idx=0; idx<ba.length; idx++) {
			hexString += String.format("%02X ", ba[idx]);
		}
		return hexString;
	}
	
<<<<<<< HEAD

=======
	/**
	 * Table model for PacketSummary
	 */
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	private class PacketSummaryTableModel extends AbstractTableModel implements P3PacketReceivedEventListener {

		private static final long serialVersionUID = 1L;
		
		public PacketSummaryTableModel() {
			AircraftClient.getInstance().addP3PacketReceivedEventListener(this);
		}
		
		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "Type";
			case 1:
				return "Count";
			case 2:
				return "Data";
			default:
				return "";
			}
		}

		@Override
		public int getRowCount() {
			return packetSummaryTable.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			PacketSummary rowData = packetSummaryTable.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return rowData.type;
			case 1:
				return rowData.count;
			case 2:
				return byteArrayToHexString(rowData.data);
			default:
				return "";
			}
		}

		@Override
		public void p3PacketStatEventReceived(P3PacketReceivedEvent evt) {
			P3Packet pkt = evt.packet;
			boolean found = false;
			
			//  Have we already seen this packet type?
			for (PacketSummary ps : packetSummaryTable) {
<<<<<<< HEAD

=======
				if (ps.type == pkt.type) {
					//  Yes, update the table
					int row = packetSummaryTable.indexOf(ps);
					ps.count++;
					ps.data = pkt.getData().clone();
					fireTableRowsUpdated(row, row);
					found = true;
				}
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
			}
			
			if (!found) {
				//  No, add a new row to the table
				PacketSummary ps = new PacketSummary();
<<<<<<< HEAD
				//ps.type = pkt.type;
=======
				ps.type = pkt.type;
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
				ps.count = 1;
				ps.data = pkt.getData().clone();
				
				packetSummaryTable.add(ps);
				
				int row = packetSummaryTable.size() - 1;
				this.fireTableRowsInserted(row, row);
			}
		}
	}
	
<<<<<<< HEAD
=======
	/**
	 * Create the panel.
	 */
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
	public AircraftConnectionPanel() {
		//setBorder(new LineBorder(Color.BLACK, 1));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		//gridBagLayout.columnWidths = new int[]{0, 0, 0};
		//gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lbl = new JLabel("Received Packets:");
		add(lbl, gbc);
		
		table = new JTable();
		table.setModel(new PacketSummaryTableModel());
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		JScrollPane jsp = new JScrollPane(table);
		//jsp.setMinimumSize(new Dimension(300, 300));
		add(jsp, gbc);
		
		AircraftClient.getInstance().connect();
	}
}
<<<<<<< HEAD
*/
=======
>>>>>>> ff4dc30e813357e6a845a625175e8650db9b9a52
