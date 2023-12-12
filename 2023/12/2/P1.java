import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

class P1 {
  public static int solve(InputStream in) {
    int result = 0;

    final int MAX_R = 12;
    final int MAX_G = 13;
    final int MAX_B = 14;

    int gameID = 1;

    Scanner sc = new Scanner(in);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();

      boolean isPossible = true;

      // Forward scan without allocation
      int index = line.indexOf(':') + 2;
      while (index < line.length()) {
        int next = line.indexOf(' ', index + 1);

        int count = Integer.parseInt(line.substring(index, next++));
        char color = line.charAt(next++);

        if (color == 'r' && count > MAX_R)
          isPossible = false;
        else if (color == 'g' && count > MAX_G)
          isPossible = false;
        else if (count > MAX_B)
          isPossible = false;

        if (!isPossible)
          break;

        index = line.indexOf(' ', next);
        if (index == -1)
          break;
        else
          ++index;
      }

      if (isPossible)
        result += gameID;

      ++gameID;
    }

    sc.close();
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
