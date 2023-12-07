import java.util.Scanner;

class Solution {
  public static void main(String[] args) {
    long result = 0;

    Scanner sc = new Scanner(System.in);
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

    System.out.println(result);
  }
}
