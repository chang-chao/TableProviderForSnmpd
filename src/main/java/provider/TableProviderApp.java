package provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

public class TableProviderApp {
	private static SensorStatus[] sensors = new SensorStatus[] {
			new SensorStatus(1, "2", "OK"), new SensorStatus(2, "*", "NG") };
	private static String TALBE_OID = ".1.3.6.1.4.1.8888.255";
	private static String ENTRY_OID = TALBE_OID + ".1";

	public static void main(String[] args) throws IOException {

		try (InputStreamReader converter = new InputStreamReader(System.in);
				BufferedReader in = new BufferedReader(converter);) {

			for (String line = in.readLine(); line != null; line = in
					.readLine()) {
				if (StringUtils.equals(line, "PING")) {
					System.out.println("PONG");
					continue;
				}

				// the shutdown protocol
				// http://www.net-snmp.org/wiki/index.php/Tut:Extending_snmpd_using_shell_scripts
				if (StringUtils.isEmpty(line)) {
					return;
				}

				// command
				String command = line;
				// read next line for oid
				String oid = in.readLine();

				SnmpValForSnmpd val = null;
				if (StringUtils.equals(command, "get")) {
					// get
					val = getVal(oid);

				} else if (StringUtils.equals(command, "getNext")) {
					// getnext
					String nextOid = getNextOid(oid);
					if (nextOid != null) {
						val = getVal(nextOid);
					}
				}

				if (val != null) {
					output(val);
				} else {
					// If the command cannot return an appropriate varbind,
					// it should print print "NONE\n" to stdout (but continue
					// running).
					System.out.println("NONE");
				}

			}

		}

	}

	private static void output(SnmpValForSnmpd snmpVal) {
		System.out.println(snmpVal.getOid());
		System.out.println(snmpVal.getValType());
		System.out.println(snmpVal.getVal());
	}

	private static String getNextOid(String oid) {
		if (StringUtils.equals(oid, TALBE_OID)
				|| StringUtils.equals(oid, ENTRY_OID)) {
			return ENTRY_OID + ".1." + sensors[0].getPort();
		}

		int columnId = getColumnIdFromOid(oid);
		int port = getPortIdFromOid(oid);

		if (port < sensors.length) {
			// next row of the same column
			return ENTRY_OID + "." + columnId + "." + (port + 1);
		}

		// reached the the last row
		int numOfColomns = 2;
		if (columnId < numOfColomns) {
			// the first row of next column
			return ENTRY_OID + "." + (columnId + 1) + ".1";
		} else {
			// the last column and the last row
			return null;
		}
	}

	private static int getPortIdFromOid(String oid) {
		int port = Integer.valueOf(StringUtils.split(oid, '.')[StringUtils
				.split(oid, '.').length - 1]);
		return port;
	}

	private static int getColumnIdFromOid(String oid) {
		int columnId = Integer.valueOf(StringUtils.split(oid, '.')[StringUtils
				.split(oid, '.').length - 2]);
		return columnId;
	}

	private static SnmpValForSnmpd getVal(String oid) {
		int columnId = getColumnIdFromOid(oid);
		int port = getPortIdFromOid(oid);

		if (columnId == 1) {
			String measureVal = sensors[port - 1].getValue();

			return new SnmpValForSnmpd(oid, "string", measureVal);
		}
		String statusString = sensors[port - 1].getStatus();

		return new SnmpValForSnmpd(oid, "string", statusString);
	}

}
