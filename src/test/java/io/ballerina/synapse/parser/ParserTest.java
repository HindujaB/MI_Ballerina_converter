package io.ballerina.synapse.parser;

import io.ballerina.compiler.syntax.tree.SyntaxTree;
import io.ballerina.syntax.tree.generator.SyntaxTreeGenerator;
import io.ballerina.source.writer.BalSourceWriter;
import org.apache.synapse.config.SynapseConfiguration;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertNotNull;

public class ParserTest {

    @Test
    public void testParseSynapseConfig() {
        String xmlFilePath = "src/test/resources/sampleProject";

        // Call the method to parse the XML configuration
        SynapseConfiguration config = SynapseConfigParser.parseSynapseConfig(xmlFilePath);
        // Validate that the configuration is not null
        assertNotNull(config, "SynapseConfiguration should not be null");
        SyntaxTreeGenerator syntaxTreeGenerator = new SyntaxTreeGenerator();
        SyntaxTree syntaxTree = syntaxTreeGenerator.generateSyntaxtree(config);
        Path outpath = Paths.get("src/test/resources", "output");
        BalSourceWriter.writeSingleBalSource(outpath, syntaxTree);
    }

    @Test(expectedExceptions = Exception.class, enabled = false)
    public void testParseSynapseConfigWithInvalidPath() throws Exception {
        String invalidPath = "invalid/path/to/synapse.xml";

        // Expecting the parsing to throw an exception when the file path is invalid
        SynapseConfiguration configuration = SynapseConfigParser.parseSynapseConfig(invalidPath);
        SyntaxTreeGenerator syntaxTreeGenerator = new SyntaxTreeGenerator();
        syntaxTreeGenerator.generateSyntaxtree(configuration);
    }
}
