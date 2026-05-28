package highlighting;

import static org.junit.jupiter.api.Assertions.*;

import highlighting.core.HighlightRegion;
import highlighting.regex.RegexHighlighter;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RegexHighlighterTest {

    @Test
    void shouldHighlightKeyword() {
        RegexHighlighter highlighter = new RegexHighlighter();

        List<HighlightRegion> regions =
            highlighter.computeRegions("public class Test");

        assertFalse(regions.isEmpty());
    }

    @Test
    void shouldResolveOverlappingRegions() {
        RegexHighlighter highlighter = new RegexHighlighter();

        List<HighlightRegion> regions =
            highlighter.computeRegions("// public class");

        assertEquals(1, regions.size());
    }

    @Test
    void shouldHandleEmptyText() {
        RegexHighlighter highlighter = new RegexHighlighter();

        List<HighlightRegion> regions =
            highlighter.computeRegions("");

        assertTrue(regions.isEmpty());
    }
}
