import org.junit.Before;

/**
 * Created by Tector on 17.09.2017.
 */
public class LozengeTilingTest {
    LozengeTiling test;

    @Before
    public void initialize()
    {
        test = new LozengeTiling(1,30,30);
    }

    @org.junit.Test
    public void isCorrect() throws Exception {

    }

    @org.junit.Test
    public void energy() throws Exception {

    }

    @org.junit.Test
    public void metropolis() throws Exception {
        for (int t = 0; t < 10; t++) {
            test.changeTemperature(5*t + 1);
            test.metropolis(10);
            System.out.println(test.averageHeight);
            System.out.println(test.volumeSquared);
            System.out.println(test.dispersion);
            System.out.println();
            System.out.println(test);
        }
    }

}