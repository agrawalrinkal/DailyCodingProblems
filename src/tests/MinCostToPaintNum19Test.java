package tests;

import impl.MinCostToPaintNum19;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinCostToPaintNum19Test {

    private final MinCostToPaintNum19 minCost19 = new MinCostToPaintNum19();

    @Test
    void getMinCostToPaintTest() {
        int[][] costMatrix = {
                {2, 1, 5, 7},
                {3, 1, 8, 9},
                {6, 2, 8, 7},
                {5, 4, 3, 2},
                {9, 10, 5, 7},
                {8, 5, 4, 9}
        };
        assertEquals(18, minCost19.getMinCostToPaint(costMatrix));
    }

    @Test
    void getMinCostToPaint2Test() {
        int[][] costMatrix = {
                {2, 1, 5, 7},
                {3, 1, 8, 9},
                {6, 2, 8, 7},
                {5, 4, 3, 2},
                {9, 10, 8, 7},
                {8, 5, 4, 9}
        };
        assertEquals(20, minCost19.getMinCostToPaint(costMatrix));
    }

    @Test
    void getMinCostToPaint3Test() {
        int[][] costMatrix = {
                {2, 1, 5, 7},
        };
        assertEquals(1, minCost19.getMinCostToPaint(costMatrix));
    }

    @Test
    void getMinCostToPaint4Test() {
        int[][] costMatrix = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        assertEquals(3, minCost19.getMinCostToPaint(costMatrix));
    }

    @Test
    void getMinCostToPaint5Test() {
        int[][] costMatrix = {
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}
        };
        assertEquals(0, minCost19.getMinCostToPaint(costMatrix));
    }


}
