import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Solution {
  public static void main(String[] args) {
    int result = 0;

    Scanner sc = new Scanner(System.in);
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

      if (matches > 0)
        result += 1 << (matches - 1);
    }

    System.out.println(result);
  }
}
