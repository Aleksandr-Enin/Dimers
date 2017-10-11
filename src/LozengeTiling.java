import java.util.Random;

/**
 * Created by Tector on 17.09.2017.
 */
public class LozengeTiling {
    double weight;
    int n, m;
    int [][] lattice;
    Random random;
    double T = 30;
    double averageHeight = 0;
    double volumeSquared = 0;
    double dispersion = 0;
    double [][] averageConfiguration;
    double [][] correlators;

    public int[][] getAverageConfiguration() {
        return arrayToInt(averageConfiguration);
    }

    public double[][] getCorrelations() {
        return correlators;
    }

    private int[][] arrayToInt(double [][] array) {
        int [][] t = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            t[i] = new int[array[i].length];
            for (int j = 0; j < t.length; j++) {
                t[i][j] = (int) array[i][j];
            }
        }
        return t;

    }

    public LozengeTiling(double weight, int n, int m) {
        this.weight = weight;
        this.n = n;
        this.m = m;
        this.lattice = new int[n][m];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                lattice[i][j] = (i+j)/2;
            }
        }
 /*       for (int i = 0; i< n; i++) {
            lattice[i][n-1] = i+1;
            lattice[n-1][i] = i+1;
        }*/
        lattice[n-1][n-1] = n;
        random = new Random();
        averageHeight = 0;
    }

    boolean isCorrect() {
        if (lattice[0][0] != 0 || lattice[n - 1][n - 1] != n) return false;
        if (lattice[n-1][0] == 0 || lattice[0][n-1]==0) return false;
        if (lattice[n-1][0] == n || lattice[0][n-1] == n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((j < m - 1 && lattice[i][j] > lattice[i][j + 1]) || (i < n - 1 && lattice[i][j] > lattice[i + 1][j]) || lattice[i][j] < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public double energy()
    {
        double energy = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                energy = energy + lattice[i][j];
            }
        return energy;
    }

    void changeConfiguration()
    {
        int heightDifference = 0;
        int i = 0;
        int j = 0;
        do {
            heightDifference = (random.nextBoolean() ? 1 : -1);
            i = random.nextInt(n);
            j = random.nextInt(m);
        } while (!isCorrectChange(i,j,heightDifference));
        if (random.nextDouble() < Math.exp(heightDifference/T)) {
            lattice[i][j]+= heightDifference;
        }
    }

    private boolean isCorrectChange(int i, int j, int heightDifference) {
        if ((i==0 && j==0) || (i ==n-1 && j==n-1)) return false;
        if ((i == n-1 && j==0) || (i==0 && j==n-1)) return false;
        if ((j < n - 1 && lattice[i][j] + heightDifference > lattice[i][j + 1]) || (i < n - 1 && lattice[i][j] + heightDifference > lattice[i + 1][j]) || lattice[i][j] + heightDifference< 0) return false;
        if ((j > 0 && lattice[i][j] + heightDifference < lattice[i][j - 1]) || (i > 0 && lattice[i][j] + heightDifference < lattice[i - 1][j]) || lattice[i][j] +heightDifference < 0) return false;
        return true;
    }

    void sample()
    {
        int height = 0;
        int heightSquared = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
        //        System.out.print(lattice[i][j] + " ");
                height += lattice[i][j];
                heightSquared += lattice[i][j]*lattice[i][j];
                averageConfiguration[i][j] += lattice[i][j];
                correlators[i][j] += lattice[n/2][n/2]*lattice[i][j];
            }
        //    System.out.println();
        }
        //System.out.println("");
        averageHeight += ((double) height)/(m*n);
        volumeSquared += heightSquared/(m*n);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i=0;i<n;i++) {
            for (int j =0; j<m;j++) {
                result += lattice[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void metropolis(int iterations)
    {
        int i, j;
        initializeSample();
        for (int t =0; t < n*n*n*100; t++) {
            changeConfiguration();
        }
        System.out.println("thermalization done") ;
        for (int k = 0; k < iterations; k++)
        {
            for (int t =0; t < 100; t++) {
                changeConfiguration();
            }
            sample();
        }
        finalizeSample(iterations);
    }

    private void initializeSample() {
        averageHeight = 0;
        volumeSquared = 0;
        dispersion = 0;
        averageConfiguration = new double[n][n];
        correlators = new double[n][n];
    }

    public void changeTemperature(double T) {
        this.T = T;
    }

    private void finalizeSample(int iterations) {
        averageHeight = averageHeight/iterations;
        volumeSquared = volumeSquared/iterations;
        dispersion = volumeSquared - averageHeight*averageHeight;
        for (int i = 0; i < n ;i++) {
            for (int j = 0; j < n; j++) {
                averageConfiguration[i][j] /= iterations;
            }
        }

        for (int i =0; i< n; i++) {
            for (int j =0; j< n; j++) {
                correlators[i][j] /= iterations;
                correlators[i][j] -= averageConfiguration[i][j]*averageConfiguration[n/2][n/2];
            }
        }
    }
}
