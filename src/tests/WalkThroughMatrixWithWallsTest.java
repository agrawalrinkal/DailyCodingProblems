package tests;

import impl.WalkThroughMatrixWithWalls;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalkThroughMatrixWithWallsTest {

    private final WalkThroughMatrixWithWalls walkThruWalls = new WalkThroughMatrixWithWalls();

    @Test
    void testGetSourceToDestMinPath() {
        boolean[][] matrix = new boolean[4][4];
//        {false,false,false,false},
//        {true,true,false,true},
//        {false,false,false,false},
//        {false,false,false,false}
        matrix[1][0] = true;
        matrix[1][1] = true;
        matrix[1][3] = true;

        int[] source = new int[]{3, 0};
        int[] dest = new int[]{0, 0};
        assertEquals(7, walkThruWalls.getSourceToDestMinPath(matrix, source, dest));
    }
}