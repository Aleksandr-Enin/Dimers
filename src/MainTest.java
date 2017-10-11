import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void scalingLimit() throws Exception {
        Main.scalingLimit(1000);
    }

    @Test
    public void correlators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 50, 50);
        tiling.changeTemperature(1000);
        tiling.metropolis(1000000);
        for (int i =0; i<50;i++) {
            for (int j =0; j<50;j++) {
                System.out.print(tiling.getCorrelations()[i][j] + " ");
            }
            System.out.println();
        }
        LozengePlot.saveImage(tiling.getAverageConfiguration(), "lowTemperature");
    }
}