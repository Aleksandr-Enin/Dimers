public class Main {
    public static void main(String... args) {
        final int size = 30;
        LozengeTiling lattice = new LozengeTiling(1, size, size);
        double t = 0.5;
        for (int i =0; i<10 ;i++) {
            t = 10.5 + i*10;
            lattice.changeTemperature(t);
            lattice.metropolis(50);
            //LozengePlot.plot(lattice.getAverageConfiguration(), "T" + t);
            System.out.println(t  + " " + lattice.averageHeight);
            LozengePlot.saveImage(lattice.getAverageConfiguration(), "T" + t);
        }
    }

    public static void scalingLimit(int iterations){
        for (int n = 10; n <= 50; n+=10) {
            LozengeTiling tiling = new LozengeTiling(1,n,n);
            tiling.changeTemperature(80);
            tiling.metropolis(iterations);
            System.out.println(n);
            LozengePlot.saveImage(tiling.getAverageConfiguration(), "n="+n);
        }
    }
}
