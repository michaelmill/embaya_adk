package org.openmuc.axdr.compiler.someexamples;

import java.io.File;

import org.junit.Test;
import org.openmuc.axdr.compiler.Compiler;

public class SomeExamplesCompilingTest {

	@Test
	public void compiling() throws Exception {

		System.out.println(new File(".").getAbsolutePath());

		String[] args = new String[] { "-f", "src/test/resources/someExamples.asn", "-o",
				"src/test/java/org/openmuc/axdr/compiler/someexamples/generated", "-ns",
				"org.openmuc.axdr.compiler.someexamples.generated", "-il" };
		Compiler.main(args);

	}

}
