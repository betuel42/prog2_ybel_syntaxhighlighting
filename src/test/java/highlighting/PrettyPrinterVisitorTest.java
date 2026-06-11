package highlighting;

import static org.junit.jupiter.api.Assertions.assertTrue;

import highlighting.antlr.MiniJavaLexer;
import highlighting.antlr.MiniJavaParser;
import highlighting.antlr.PrettyPrinterVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

public class PrettyPrinterVisitorTest {

  @Test
  void shouldPrettyPrintSimpleClass() {
    String input = "class Test{public String name;public String getName(){return name;}}";

    String output = prettyPrint(input, 2);

    assertTrue(output.contains("class Test"));
    assertTrue(output.contains("  public String name;"));
    assertTrue(output.contains("  public String getName()"));
    assertTrue(output.contains("    return name;"));
  }

  private String prettyPrint(String input, int indentWidth) {
    MiniJavaLexer lexer = new MiniJavaLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MiniJavaParser parser = new MiniJavaParser(tokens);
    MiniJavaParser.CompilationUnitContext tree = parser.compilationUnit();

    PrettyPrinterVisitor visitor = new PrettyPrinterVisitor(indentWidth);
    visitor.visit(tree);

    System.out.println(visitor.result());

    return visitor.result();
  }
}
