import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class P1 {
  public static long solve(InputStream in) {
    long result = 0;

    Scanner sc = new Scanner(in);
    while (sc.hasNext()) {
      String line = sc.nextLine();

      int lo = 0;
      while (lo < line.length() && !Character.isDigit(line.charAt(lo)))
        ++lo;

      int hi = line.length() - 1;
      while (hi > lo && !Character.isDigit(line.charAt(hi)))
        --hi;

      result += 10 * (line.charAt(lo) - '0') + (line.charAt(hi) - '0');
    }

    sc.close();
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
