/*
 * Copyright 2012-15 Fraunhofer ISE
 *
 * This file is part of jDLMS.
 * For more information visit http://www.openmuc.org
 *
 * jDLMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jDLMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jDLMS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms.app;

import static java.lang.System.exit;

import java.io.IOException;
import java.net.InetAddress;

import org.openmuc.jdlms.HdlcConnectionBuilder;
import org.openmuc.jdlms.HexConverter;
import org.openmuc.jdlms.LnClientConnection;
import org.openmuc.jdlms.TcpConnectionBuilder;
import org.openmuc.jdlms.internal.ReferencingMethod;

class ConsoleLineParser {

	private static final int MIN_NUM_OF_ARGS = 2;

	private static class Options {
		static final String SHORT_NAME = "-sn";
		static final String SECURITY_LEVEL = "-sec";
		static final String MANUFACTURE_ID = "-mid";
		static final String DEVICE_ID = "-did";
		static final String ENCRYPTION = "-enc";
		static final String CHALLENGE_LENGTH = "-cl";

		static class Tcp {
			static final String PORT = "-p";
			static final String CLIENT_ACCESS_POINT = "-cap";
			static final String LOGICAL_DEVIC_ADDRESS = "-ld";
		}

		@SuppressWarnings("unused")
		static class Hdlc {
			static final String BAUDRATE = "-bd";
		}
	}

	private final ConnectionLayerType layerType;
	private final Arguments arguments;
	private ReferencingMethod referencingMethod;

	public ConsoleLineParser(String[] arguments) throws IllegalArgumentException {
		this.arguments = new Arguments(arguments);

		validateArgumentLength();

		String connectionType = this.arguments.nextArgument().toUpperCase();
		this.layerType = ConnectionLayerType.valueOf(connectionType);

		this.referencingMethod = ReferencingMethod.LOGICAL_NAME;
	}

	private void validateArgumentLength() {
		if (this.arguments.length() < MIN_NUM_OF_ARGS) {
			printUsage();
			System.err.println("You need to provide at least two arguments.");
			exit(0);
		}
	}

	public ConsoleApp createConsoleApp() throws Throwable {
		try {
			switch (layerType) {
			case HDLC:
				return createHdlcConsoleApp();
			default:
			case TCP:
				return createTcpConsoleApp();
			}

		} catch (Throwable e) {
			// printUsage(e.getMessage());
			throw e;
		}
	}

	private ConsoleApp createHdlcConsoleApp() throws IOException {
		// TODO: implement this method for future use!
		return new LnConsoleApp(createHdlcTestConnection());
	}

	private LnClientConnection createHdlcTestConnection() throws IOException {

		String serialPortName = "/dev/ttyUSB0";

		HdlcConnectionBuilder connectionBuilder = new HdlcConnectionBuilder(serialPortName, 18, 16)
				.enablePasswordAuthentication("12345".getBytes()).responseTimeout(3000);
		return connectionBuilder.buildLnConnection();

	}

	private ConsoleApp createTcpConsoleApp() throws IllegalOptionException, IOException {
		String remoteHost = arguments.nextArgument();
		TcpConnectionBuilder connectionBuilder = new TcpConnectionBuilder(InetAddress.getByName(remoteHost));

		while (arguments.hasNext()) {
			String option = arguments.nextArgument();

			if (option.equals(Options.Tcp.PORT)) {
				validateOptionArgumentExist();
				int port = arguments.nextArgumentAsInt();

				connectionBuilder.tcpPort(port);
			}
			else if (option.equals(Options.Tcp.LOGICAL_DEVIC_ADDRESS)) {
				validateOptionArgumentExist();
				connectionBuilder.logicalDeviceAddress(arguments.nextArgumentAsInt());
			}
			else if (option.equals(Options.Tcp.CLIENT_ACCESS_POINT)) {
				validateOptionArgumentExist();
				connectionBuilder.clientAccessPoint(arguments.nextArgumentAsInt());
			}
			else if (option.equals(Options.CHALLENGE_LENGTH)) {
				validateOptionArgumentExist();
				int challengeLength = arguments.nextArgumentAsInt();

				connectionBuilder.challengeLength(challengeLength);
			}

			else {
				parseCommonArguments(connectionBuilder, option);
			}
		}

		ConsoleApp app = connect(connectionBuilder);

		System.out.printf("** Successfully connected to host: \n");// TODO

		return app;
	}

	private ConsoleApp connect(TcpConnectionBuilder connectionBuilder) throws IOException {
		switch (this.referencingMethod) {
		default:
		case LOGICAL_NAME:
			return new LnConsoleApp(connectionBuilder.buildLnConnection());

		case SHORT_NAME:
			return new SnConsoleApp(connectionBuilder.buildSnConnection());
		}
	}

	private void parseCommonArguments(TcpConnectionBuilder connectionBuilder, String option)
			throws IllegalOptionException {

		if (option.equals(Options.SECURITY_LEVEL)) {
			parseSecurityLevel(connectionBuilder);
		}
		else if (option.equals(Options.ENCRYPTION)) {
			connectionBuilder.enableEncryption(HexConverter.fromShortHexString(arguments.nextArgument()));
		}
		else if (option.equals(Options.DEVICE_ID)) {
			validateOptionArgumentExist();
			long deviceID = arguments.nextArgumentAsLong();
			connectionBuilder.deviceId(deviceID);
		}
		else if (option.equals(Options.MANUFACTURE_ID)) {
			validateOptionArgumentExist();
			String manufactureId = option.toUpperCase();
			if (manufactureId.length() > 3) {
				throw new IllegalOptionException(1, "Manufacture id should have 3 signs.");
			}
			connectionBuilder.manufactureId(manufactureId);
		}
		else if (option.equals(Options.SHORT_NAME)) {
			this.referencingMethod = ReferencingMethod.SHORT_NAME;
		}
		else {
			throw new IllegalOptionException(1);
		}
	}

	private void parseSecurityLevel(TcpConnectionBuilder connectionBuilder) throws IllegalOptionException {
		validateOptionArgumentExist();

		int authenticationLevel = arguments.nextArgumentAsInt();
		byte[] encryptionKey;
		byte[] authenticationKey;
		switch (authenticationLevel) {
		case 0:
			break;
		case 1:
			// TODO:
			authenticationKey = HexConverter.fromShortHexString(arguments.nextArgument());
			break;
		case 2:
			// TODO:
			authenticationKey = HexConverter.fromShortHexString(arguments.nextArgument());
			break;
		case 3:
			authenticationKey = HexConverter.fromShortHexString(arguments.nextArgument());
			connectionBuilder.useMd5Authentication(authenticationKey);
			break;
		case 5:
			authenticationKey = HexConverter.fromShortHexString(arguments.nextArgument());
			encryptionKey = HexConverter.fromShortHexString(arguments.nextArgument());
			connectionBuilder.useGmacAuthentication(authenticationKey, encryptionKey);
			break;
		default:
			throw new IllegalOptionException(1, "Unknown authentication level.");
		}
	}

	private void validateOptionArgumentExist() throws IllegalOptionException {
		if (!arguments.hasNext()) {
			throw new IllegalOptionException(1);
		}
	}

	public void printUsage() {
		final String optionFormat0 = "\t%s\n\t    %s\n\n";
		final String optionFormat1 = "\t%s\n\t    %s\n\t    %s\n\n";

		System.out.println("SYNOPSIS");
		System.out.println(
				"\torg.openmuc.jdlms.app.ClientApp TCP <host> [-p <port>] [-ld <logical_device_address>] [-cap <client_access_point>] [-sn] [-sec <security_level> <password> [<encryption_key>]]");
		System.out.println(
				"\torg.openmuc.jdlms.app.ClientApp HDLC <serial-port> [-p <port>] [-ld <logical_device_address>] [-cap <client_access_point>] [-sn] [-sec <security_level> <password> [<encryption_key>]]");
		System.out.println("DESCRIPTION\n\tA client/master application to access DLMS/COSEM servers/slaves.");
		System.out.println("OPTIONS");

		System.out.printf(optionFormat0, "<host>", "The address of the device you want to access.");
		System.out.printf(optionFormat0, "-p <port>", "The port to connect to. The default port is 4059.");
		System.out.printf(optionFormat0, "-ld <logical_device_address>",
				"The address of the logical device inside the server to connect to. This address is also referred to as the server wPort. The default is 1.");
		System.out.printf(optionFormat0, "-cap <client_access_point>",
				"The client access point ID which identifies the client. This address is also referred to as the client wPort. The default is 16 (public user).");
		System.out.printf(optionFormat0, Options.SHORT_NAME,
				"Use short name referencing instead of long name referencing.");
		System.out.printf(optionFormat1, "-sec <security_level> <password/key> [<encryption_key>]",
				"Connect with credentials to the server.",
				"Security level: 0 = LOWEST; 1 = LOW; 2 = HIGH_MANUFACTORER; 3 = HIGH_MD5; 5 = HIGH_GMAC");
		System.out.printf(optionFormat0, Options.CHALLENGE_LENGTH + " <challenge_length>",
				"Set the length of the authentication challenge, from 8 to 64 byte. For example: kaifa meters maximal 16 bytes.");
	}

	public void printUsage(String errorMessage) {
		System.err.println(errorMessage);
		printUsage();
	}

	private enum ConnectionLayerType {
		TCP,
		HDLC
	}

	public static class IllegalOptionException extends Exception {

		private static final long serialVersionUID = -7312373926819634516L;
		private final int errorlevel;

		public IllegalOptionException(int errorlevel, Throwable cause) {
			super(cause);
			this.errorlevel = errorlevel;
		}

		public IllegalOptionException(int errorlevel) {
			this.errorlevel = errorlevel;
		}

		public IllegalOptionException(int errorlevel, String message) {
			super(message);
			this.errorlevel = errorlevel;
		}

		public int errorlevel() {
			return errorlevel;
		}
	}

}
