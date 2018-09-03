//import java.awt.geom.Line2D;
//import java.awt.geom.Point2D;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Solution {
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) throws IOException {
//
//        int lines = Integer.parseInt(scanner.nextLine().trim());
//        ArrayList<Point2D> cities = new ArrayList<>();
//        for (int i = 0; i < lines; i++) {
//            String[] split = scanner.nextLine().trim().split(",");
//            Point2D point = new Point2D.Double(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
//            cities.add(point);
//        }
//        scanner.close();
//        ArrayList<Point2D> node = new ArrayList<>();
//        ArrayList<Line2D> paths = new ArrayList<>();
//        printResult(calculatePaths(cities, node, paths));
//    }
//
//    public static ArrayList<Line2D> calculatePaths(ArrayList<Point2D> cities, ArrayList<Point2D> nodes, ArrayList<Line2D> paths) {
//      int length = cities.size();
////        System.out.println("cities " + length);
//      if(length < 1) {
////          System.out.println(paths.size() + " finish " + length);
//          return paths;
//      } else {
//          int nodeCount = nodes.size();
//          Point2D point = cities.remove(0);
//          if(nodeCount > 0) {
//              nodes.forEach((node) -> {
//                  Line2D line = new Line2D.Double(point,node);
//                  ArrayList<Line2D> temp = new ArrayList<>();
//                  paths.forEach((path) -> {
//                      if(point.equals(path.getP1()) || point.equals(path.getP2()) || node.equals(path.getP1()) || node.equals(path.getP2())) {
//                          if((point.equals(path.getP1()) || point.equals(path.getP2())) && (node.equals(path.getP1()) || node.equals(path.getP2()))) {
//                              temp.add(path);
//                          } else {
//                              if(point.equals(path.getP1())) {
//                                  if(checkM(point, node, path.getP1(), path.getP2())) {
//                                      temp.add(path);
//                                  }
//                              } else if(point.equals(path.getP2())) {
//                                  if(checkM(point, node, path.getP2(), path.getP1())) {
//                                      temp.add(path);
//                                  }
//                              } else if(node.equals(path.getP1())) {
//                                  if(checkM(node, point, path.getP1(), path.getP2())) {
//                                      temp.add(path);
//                                  }
//                              } else if(node.equals(path.getP1())) {
//                                  if(checkM(node, point, path.getP2(), path.getP1())) {
//                                      temp.add(path);
//                                  }
//                              }
//                          }
//                      } else if (path.intersectsLine(line)) {
//                          temp.add(path);
//                      }
//                  });
//                  boolean add = true;
//                  for (int i = 0; i < temp.size(); i++) {
//                      if (calculateLength(temp.get(i)).compareTo(calculateLength(line)) <= 0) {
//                          add = false;
//                          break;
//                      }
//                  }
//                  if(add) {
//                      paths.removeAll(temp);
//                      paths.add(line);
//                  }
//              });
//          }
//          nodes.add(point);
////          System.out.println(paths.size() + " finish " + length);
//          return calculatePaths(cities, nodes, paths);
//      }
//    }
//
//    public static void printResult(ArrayList<Line2D> result) {
//        int length = result.size();
//        System.out.println(length);
//        BigDecimal distance = BigDecimal.ZERO;
//        for (int i = 0; i < length; i++) {
//            distance = distance.add(calculateLength(result.get(i)));
//        }
//        System.out.println(distance);
//    }
//
//    public static BigDecimal calculateLength(Line2D line) {
//        return new BigDecimal(String.valueOf(line.getP1().distance(line.getP2())));
//    }
//
//    public static boolean checkM(Point2D p1, Point2D p2, Point2D l1, Point2D l2) {
//        BigDecimal yp = new BigDecimal(String.valueOf(p1.getY())).subtract(new BigDecimal(String.valueOf(p2.getY())));
//        BigDecimal yl = new BigDecimal(String.valueOf(l1.getY())).subtract(new BigDecimal(String.valueOf(l2.getY())));
//        BigDecimal xp = new BigDecimal(String.valueOf(p1.getX())).subtract(new BigDecimal(String.valueOf(p2.getX())));
//        BigDecimal xl = new BigDecimal(String.valueOf(l1.getX())).subtract(new BigDecimal(String.valueOf(l2.getX())));
//        if(yp.compareTo(BigDecimal.ZERO) == 0 && yl.compareTo(BigDecimal.ZERO) == 0) {
//            if (xp.multiply(xl).compareTo(BigDecimal.ZERO) >= 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } else if(yp.compareTo(BigDecimal.ZERO) == 0 || yl.compareTo(BigDecimal.ZERO) == 0) {
//            return false;
//        } else {
//            if (xp.divide(yp,6,BigDecimal.ROUND_HALF_UP).compareTo(xl.divide(yl,6,BigDecimal.ROUND_HALF_UP)) == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//}
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        boolean[][] size = new boolean[aLength +1][bLength +1];
        size[0][0] = true;
        boolean upper = true;
        for (int i = 1; i < aLength + 1; i++) {
            upper = upper && !Character.isUpperCase(a.charAt(i-1));
            size[i][0] = upper;
        }
        for (int i = 1; i < bLength + 1; i++) {
            size[0][i] = false;
        }
        for(int i = 1; i < aLength + 1; i++) {
            char cha = a.charAt(i - 1);
            for (int j = 1; j < bLength + 1; j++) {
                char chb = b.charAt(j-1);
                if (Character.isUpperCase(cha)) {
                    if(cha == chb) {
                        size[i][j] = size[i-1][j-1];
                    } else {
                        size[i][j] = false;
                    }
                } else {
                    if(Character.toUpperCase(cha) == chb) {
                        size[i][j] = size[i -1][j] || size[i -1][j - 1];
                    } else {
                        size[i][j] = size[i -1][j];
                    }
                }
            }
        }
        return size[aLength +1][bLength +1] ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            System.out.println(result);
        }


        scanner.close();
    }
}
