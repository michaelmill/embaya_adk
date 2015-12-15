package org.openmuc.jasn1.compiler.axdrexample;

import java.io.File;

import org.junit.Test;
import org.openmuc.axdr.compiler.Compiler;

public class X690AxdrExampleCompilingTest {

	@Test
	public void compiling() throws Exception {

		System.out.println(new File(".").getAbsolutePath());

		String[] args = new String[] { "-f", "src/test/resources/x690AxdrExample.asn", "-o",
				"src/test/java/org/openmuc/jasn1/compiler/axdrexample/generated", "-ns",
				"org.openmuc.jasn1.compiler.axdrexample.generated" };
		Compiler.main(args);

	}

}
