import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

class P2 {
  public static int solve(InputStream in) {
    int result = 0;

    Scanner sc = new Scanner(in);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();

      int r = 0;
      int g = 0;
      int b = 0;

      // Forward scan without allocation
      int index = line.indexOf(':') + 2;
      while (index < line.length()) {
        int next = line.indexOf(' ', index + 1);

        int count = Integer.parseInt(line.substring(index, next++));
        char color = line.charAt(next++);

        if (color == 'r')
          r = Math.max(r, count);
        else if (color == 'g')
          g = Math.max(g, count);
        else
          b = Math.max(b, count);

        index = line.indexOf(' ', next);
        if (index == -1)
          break;
        else
          ++index;
      }

      int power = r * g * b;
      result += power;
    }

    sc.close();
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
