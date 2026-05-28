package highlighting;

import static org.junit.jupiter.api.Assertions.*;

import highlighting.presets.MiniJavaTokens;
import highlighting.regex.Token;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MiniJavaTokensTest {

    @Test
    void keywordShouldBeMatched() {
        List<Token> tokens = MiniJavaTokens.defaultTokens();

        boolean found = false;

        for (Token token : tokens) {
            if (!token.test("public class Test").isEmpty()) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void stringShouldBeMatched() {
        List<Token> tokens = MiniJavaTokens.defaultTokens();

        boolean found = false;

        for (Token token : tokens) {
            if (!token.test("\"hello\"").isEmpty()) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void commentShouldBeMatched() {
        List<Token> tokens = MiniJavaTokens.defaultTokens();

        boolean found = false;

        for (Token token : tokens) {
            if (!token.test("// comment").isEmpty()) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }
}
