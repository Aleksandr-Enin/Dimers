import org.junit.Test;

import java.io.FileWriter;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void scalingLimit() throws Exception {
        Main.scalingLimit(1000);
    }

    @Test
    public void correlators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 50, 50);
        for (int t = 15; t< 25; t++) {
            tiling.changeTemperature(t);
            tiling.metropolis(10000000);
            FileWriter fileWriter = new FileWriter("correlators/" + tiling.n + "_" + t + "_" + "Corelators.dat");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LozengePlot.saveImage(tiling.getAverageConfiguration(), "correlators/" + tiling.n + "_" + t + "_" + "Corelators");
            for (int i = 0; i < tiling.n; i++) {
                for (int j = 0; j < tiling.n; j++) {
                    printWriter.print(tiling.getCorrelations()[i][j] + " ");
                }
                printWriter.println();
            }
            printWriter.close();
        }
    }

    @Test
    public void diagonalCorrelators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 50, 50);
        for (int t = 30; t< 400000; t*=10) {
            tiling.changeTemperature(t);
            tiling.metropolis(10000000);
            FileWriter fileWriter = new FileWriter("results/" + tiling.n + "_" + t + "_" + "Diagonal.dat");
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
        for (int t = 15; t< 25; t++) {
            tiling.changeTemperature(t);
            tiling.metropolis(1000000);
            FileWriter fileWriter = new FileWriter("results/" + tiling.n + "_" + t + "_" + "Length.dat");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LozengePlot.saveImage(tiling.getAverageConfiguration(), "results/" + tiling.n + "_" + t + "_" + "Length");
            for (int i = 0; i < tiling.n;i++)
            {
                printWriter.println(((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[i][i]);
            }
            printWriter.close();
        }

    }
}