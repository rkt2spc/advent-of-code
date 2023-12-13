import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class P2 {
  private static final String[] nums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

  private static int getDigit(String s, int index, boolean reverse) {
    if (Character.isDigit(s.charAt(index))) {
      return s.charAt(index) - '0';
    }

    for (int i = 0; i < nums.length; ++i) {
      String num = nums[i];

      if (s.regionMatches(reverse ? index - num.length() + 1 : index, num, 0, num.length()))
        return i + 1;
    }

    return 0;
  }

  public static long solve(InputStream in) {
    long result = 0;

    Scanner sc = new Scanner(in);
    while (sc.hasNext()) {
      String line = sc.nextLine();

      int lo = 0;
      while (lo < line.length()) {
        int digit = getDigit(line, lo, false);
        if (digit != 0) {
          result += digit * 10;
          break;
        }
        ++lo;
      }

      int hi = line.length() - 1;
      while (hi >= lo) {
        int digit = getDigit(line, hi, true);
        if (digit != 0) {
          result += digit;
          break;
        }
        --hi;
      }
    }

    sc.close();
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
