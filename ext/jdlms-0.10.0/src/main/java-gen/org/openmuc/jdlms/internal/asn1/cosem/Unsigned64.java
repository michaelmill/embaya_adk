/**
 * This class file was automatically generated by the AXDR compiler that is part of jDLMS (http://www.openmuc.org)
 */

package org.openmuc.jdlms.internal.asn1.cosem;

import org.openmuc.jdlms.internal.asn1.axdr.types.AxdrInteger;

public class Unsigned64 extends AxdrInteger {

	public Unsigned64() {
		super(0, 9223372036854775807L, 0);
	}

	public Unsigned64(byte[] code) {
		super(0, 9223372036854775807L, 0);
		this.code = code;
	}

	public Unsigned64(long val) {
		super(0, 9223372036854775807L, val);
	}

}
