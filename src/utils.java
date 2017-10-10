public class utils {
    public static String arrayToString(double[][] a) {
        String result = "";
        for (int i =0; i< a.length; i++) {
            for (int j =0;j<a[i].length; j++) {
                result += a[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}
