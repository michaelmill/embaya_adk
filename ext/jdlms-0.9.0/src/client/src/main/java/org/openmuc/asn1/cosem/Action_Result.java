/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.asn1.cosem;

import org.openmuc.jasn1.axdr.types.AxdrEnum;

public class Action_Result extends AxdrEnum {

	public Action_Result() {
		super();
	}

	public Action_Result(byte[] code) {
		super(code);
	}

	public Action_Result(long val) {
		super(val);
	}

	public static final int SUCCESS = 0;
	public static final int HARDWARE_FAULT = 1;
	public static final int TEMPORARY_FAILURE = 2;
	public static final int READ_WRITE_DENIED = 3;
	public static final int OBJECT_UNDEFINED = 4;
	public static final int OBJECT_CLASS_INCONSISTENT = 9;
	public static final int OBJECT_UNAVAILABLE = 11;
	public static final int TYPE_UNMATCHED = 12;
	public static final int SCOPE_OF_ACCESS_VIOLATED = 13;
	public static final int DATA_BLOCK_UNAVAILABLE = 14;
	public static final int LONG_ACTION_ABORTED = 15;
	public static final int NO_LONG_ACTION_IN_PROGRESS = 16;
	public static final int OTHER_REASON = 250;
}
