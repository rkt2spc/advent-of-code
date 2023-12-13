import java.util.Scanner;
import java.util.Set;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class P2 {
  public static int solve(InputStream in) {
    Map<Integer, Integer> cardMatches = new HashMap<>();

    int cardID = 1;
    Scanner sc = new Scanner(in);
    while (sc.hasNextLine()) {
      String card = sc.nextLine();

      int sep = card.indexOf('|');

      String winNumsStr = card.substring(card.indexOf(':') + 1, sep);
      String ownNumsStr = card.substring(sep + 1);

      Set<String> winNums = new HashSet<>();
      for (String num : winNumsStr.trim().split(" +"))
        winNums.add(num);

      int matches = 0;
      for (String num : ownNumsStr.trim().split(" +"))
        if (winNums.contains(num))
          ++matches;

      cardMatches.put(cardID++, matches);
    }

    Map<Integer, Integer> cardCounts = new HashMap<>();
    for (int card : cardMatches.keySet())
      cardCounts.put(card, 1);

    for (int card = 1; card <= cardMatches.size(); ++card) {
      int matches = cardMatches.get(card);
      int count = cardCounts.get(card);

      for (int i = 1; i <= matches; ++i) {
        int ncard = card + i;

        if (ncard > cardMatches.size())
          break;

        cardCounts.put(ncard, cardCounts.get(ncard) + count);
      }
    }

    int result = 0;
    for (int count : cardCounts.values())
      result += count;

    sc.close();
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
