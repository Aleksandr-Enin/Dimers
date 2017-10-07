import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphicPanelTest {
    int [][] lattice = {{0,0,1},{0,1,1},{1,1,2}};
    GraphicPanel panel = new GraphicPanel(lattice);

    @Test
    public void saveImage() throws Exception {
        panel.setSize(800,600);
        panel.saveImage("test");
    }

}