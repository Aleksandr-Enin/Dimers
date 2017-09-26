import java.util.Random;

/**
 * Created by Tector on 17.09.2017.
 */
public class LozengeTiling {
    double weight;
    int n, m;
    int [][] lattice;
    Random random;
    double T = 10;
    double averageHeight = 0;
    double volumeSquared = 0;
    double dispersion = 0;

    public LozengeTiling(double weight, int n, int m) {
        this.weight = weight;
        this.n = n;
        this.m = m;
        this.lattice = new int[n][m];
        lattice[0][0] = 0;
        for (int i = 0; i < n ; i++)
            for (int j = 0; j < m; j++) {
                lattice[i][j] = 0;
            }
        lattice[n-1][n-1] = n;
        random = new Random();
        averageHeight = 0;
    }

    boolean isCorrect() {
        if (lattice[0][0] != 0 || lattice[n - 1][n - 1] != n) return false;
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
            lattice[i][j] -= heightDifference;
            heightDifference = (random.nextBoolean() ? 1 : -1);
            i = random.nextInt(n);
            j = random.nextInt(m);
            lattice[i][j] += heightDifference;
        } while (!isCorrect());
        if (random.nextDouble() <= Math.exp(heightDifference/T)) return;
        lattice[i][j]-= heightDifference;

    }

    void sample()
    {
        int height = 1;
        int heightSquared = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
        //        System.out.print(lattice[i][j] + " ");
                height += lattice[i][j];
                heightSquared += lattice[i][j]*lattice[i][j];
            }
        //    System.out.println();
        }
        //System.out.println("");
        averageHeight += height/(m*n);
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
        for (int t =0; t < 100000; t++) {
            changeConfiguration();
        }
        for (int k = 0; k < iterations; k++)
        {
            for (int t =0; t < 10000; t++) {
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
    }

    public void changeTemperature(double T) {
        this.T = T;
    }

    private void finalizeSample(int iterations) {
        averageHeight = averageHeight/iterations;
        volumeSquared = volumeSquared/iterations;
        dispersion = volumeSquared - averageHeight*averageHeight;
    }
}
