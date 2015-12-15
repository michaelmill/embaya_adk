/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.someexamples.generated;

import java.io.IOException;
import java.io.InputStream;
import org.openmuc.jasn1.axdr.*;
import org.openmuc.jasn1.axdr.types.*;

public class My_Sequence implements AxdrType {

	public byte[] code = null;
	public AxdrVisibleString implVisibleString = null;

	public My_Sequence() {
	}

	public My_Sequence(byte[] code) {
		this.code = code;
	}

	public My_Sequence(AxdrVisibleString implVisibleString) {
		this.implVisibleString = implVisibleString;
	}

	public int encode(AxdrByteArrayOutputStream axdrOStream) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				axdrOStream.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			codeLength += implVisibleString.encode(axdrOStream);
			
		}

		return codeLength;

	}

	public int decode(InputStream iStream) throws IOException {
		int codeLength = 0;

		implVisibleString = new AxdrVisibleString();
		codeLength += implVisibleString.decode(iStream);

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		AxdrByteArrayOutputStream axdrOStream = new AxdrByteArrayOutputStream(encodingSizeGuess);
		encode(axdrOStream);
		code = axdrOStream.getArray();
	}
}

