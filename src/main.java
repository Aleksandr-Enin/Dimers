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
}
