import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void scalingLimit() throws Exception {
        Main.scalingLimit(1000);
    }

    @Test
    public void correlators() throws Exception {
        LozengeTiling tiling = new LozengeTiling(1, 30, 30);
        tiling.metropolis(50);
        System.out.println(utils.arrayToString(tiling.getCorrelations()));
    }
}