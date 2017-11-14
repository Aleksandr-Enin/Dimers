import org.junit.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void scalingLimit() throws Exception {
        Main.scalingLimit(1000);
    }

    @Test
    public void correlators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 50, 50);
        tiling.changeTemperature(20);
        tiling.metropolis(10000000);
        for (int i =0; i<tiling.n;i++) {
//            for (int j =0; j<tiling.n;j++) {
                System.out.println(Math.sqrt((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[i][i]);
                //System.out.println(Math.sqrt((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[(tiling.n-1)-i][i]);
            }
        LozengePlot.saveImage(tiling.getAverageConfiguration(), "lowTemperature");
    }

    @Test
    public void diagonalCorrelators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 100, 100);
        for (int t = 30; t< 400000; t*=10) {
            tiling.changeTemperature(t);
            tiling.metropolis(10000000);
            FileWriter fileWriter = new FileWriter(tiling.n + "_" + t + "_" + "Diagonal.dat");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < tiling.n;i++)
            {
                printWriter.println(((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[i][i]);
            }
            printWriter.close();
        }
    }

    @Test
    public void correlatorsLength() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1,50,50);
        for (int t = 1; t< 10; t++) {
            tiling.changeTemperature(1/(0.00001*(50*t)));
            tiling.metropolis(10000000);
            FileWriter fileWriter = new FileWriter(tiling.n + "_" + 1/(0.00001*(50*t)) + "_" + "Length.dat");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LozengePlot.saveImage(tiling.getAverageConfiguration(), tiling.n + "_" + 1/(0.00001*(50*t)) + "_" + "Length");
            for (int i = 0; i < tiling.n;i++)
            {
                printWriter.println(((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[i][i]);
            }
            printWriter.close();
        }

    }

    public void correlator(LozengeTiling tiling) {
        tiling.metropolis(10000000);
        LozengePlot.saveImage(tiling.getCorrelations(), "correlators " + tiling.T);
        LozengePlot.saveImage(tiling.getAverageConfiguration(), "averageConfiguration" + tiling.T);
    }

    @Test
    public void showCorrelators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 50, 50);
        List<LozengeTiling> range = IntStream.range(0, 10).mapToObj(t -> new LozengeTiling(1, 50,50,1 / (0.00001 * (50 * t)))).collect(Collectors.toList());
        range.parallelStream().forEach(this::correlator);
        /*for (int t = 1; t < 10; t++) {
            tiling.changeTemperature(1 / (0.00001 * (50 * t)));
            tiling.metropolis(10000000);
        }*/
    }
}