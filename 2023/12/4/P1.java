import java.util.Scanner;
import java.util.Set;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;

public class P1 {
  public static int solve(InputStream in) {
    int result = 0;

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

      if (matches > 0)
        result += 1 << (matches - 1);
    }

    sc.close();
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
