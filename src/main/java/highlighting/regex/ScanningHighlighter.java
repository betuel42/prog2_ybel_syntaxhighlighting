package highlighting.regex;

import highlighting.core.HighlightRegion;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.MiniJavaTokens;
import java.util.ArrayList;
import java.util.List;

public class ScanningHighlighter extends SyntaxHighlighter {

  @Override
  public List<HighlightRegion> collectMatches(String text) {
    List<HighlightRegion> regions = new ArrayList<>();
    List<Token> tokens = MiniJavaTokens.defaultTokens();

    int index = 0;

    while (index < text.length()) {
      HighlightRegion bestMatch = null;

      for (Token token : tokens) {
        for (HighlightRegion region : token.test(text.substring(index))) {
          if (region.start() == 0) {
            HighlightRegion shifted =
                new HighlightRegion(index + region.start(), index + region.end(), region.colour());

            if (isBetterMatch(shifted, bestMatch)) {
              bestMatch = shifted;
            }
          }
        }
      }

      if (bestMatch == null) {
        index++;
      } else {
        regions.add(bestMatch);
        index = bestMatch.end();
      }
    }

    return regions;
  }

  @Override
  public List<HighlightRegion> normalize(List<HighlightRegion> candidates) {
    return candidates;
  }

  private boolean isBetterMatch(HighlightRegion candidate, HighlightRegion currentBest) {
    if (currentBest == null) {
      return true;
    }

    return candidate.end() - candidate.start() > currentBest.end() - currentBest.start();
  }
}
