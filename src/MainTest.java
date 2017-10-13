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
        tiling.changeTemperature(20);
        tiling.metropolis(10000000);
        for (int i =0; i<tiling.n;i++) {
//            for (int j =0; j<tiling.n;j++) {
                System.out.println(Math.sqrt((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[i][i]);
                //System.out.println(Math.sqrt((i - tiling.n/2)*(i-tiling.n/2) + (i - tiling.n/2)*(i - tiling.n/2))+ " " + tiling.getCorrelations()[(tiling.n-1)-i][i]);
            }
        LozengePlot.saveImage(tiling.getAverageConfiguration(), "lowTemperature");
    }
}