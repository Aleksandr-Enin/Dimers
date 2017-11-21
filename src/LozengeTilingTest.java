import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tector on 17.09.2017.
 */
public class LozengeTilingTest {

    LozengeTiling test;

    @Test
    public void initialConfiguration(){
        LozengePlot.saveImage(test.lattice,"initial");
        test.changeTemperature(1000);
        test.metropolis(100000);
        LozengePlot.saveImage(test.getAverageConfiguration(), "final");
    }

    @Test
    public void giagonalCorrelators() throws Exception {

    }

    @Test
    public void getAverageConfiguration() throws Exception {
    }

    @Test
    public void getCorrelations() throws Exception {
    }

    @Test
    public void changeConfiguration() throws Exception {
    }

    @Test
    public void sample() throws Exception {
    }

    @Test
    public void changeTemperature() throws Exception {
    }

    @Before
    public void initialize()
    {
        test = new LozengeTiling(1,50,50);
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

    public double partition(int n, double T) {
        double z = 1;
        for (int i =0 ; i< n; i++) {
            for (int j =0; j<n;j++) {
                for (int k = 0; k < n; k++) {
                    z = z * (1 - Math.exp(-(i+j+k+2)/T))/(1 - Math.exp(-(i+j+k+1)/T));
                }
            }
        }
        return z;
    }

    public double averageEnergy(int n, double T) {
        double E = 0;
        for (int i =0; i<n ;i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    E = E + (Math.exp((i+j+k+2)/T)*(i+j+k+2))/(1 - Math.exp((i+j+k+2)/T)) - (Math.exp((i+j+k+1)/T)*(i+j+k+1))/(1 - Math.exp((i+j+k+1)/T)) ;
                }
            }
        }
        return -E/(n*n);
    }

    @org.junit.Test
    public void anotherTest() throws Exception {
        //test.changeTemperature(2);
        //test.metropolis(10);
        //System.out.println(test.averageHeight);
        //System.out.println(partition(30, 50));
        for (int i =0; i<100; i++)
            System.out.println(0.2 + i/10 + " " + averageEnergy(30, 0.2+i/10));
        //System.out.println(averageEnergy(30,2)/900);
    }

    @org.junit.Test
    public void asdasd() {
        double t = 0.5;
        for (int i =0; i<10 ;i++) {
            t = 10.5 + i*10;
            test.changeTemperature(t);
            test.metropolis(50);
            System.out.println(t  + " " + test.averageHeight + " " + averageEnergy(30, t));
        }
    }
}