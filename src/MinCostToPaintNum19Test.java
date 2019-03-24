import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinCostToPaintNum19Test {

    private final MinCostToPaintNum19 minCost19 = new MinCostToPaintNum19();

    @Test
    void minCostToPaintNum19Test() {
        int[][] costMatrix = {
                {2, 1, 5, 7},
                {3, 1, 8, 9},
                {6, 2, 8, 7},
                {5, 4, 3, 2},
                {9, 10, 5, 7},
                {8, 5, 4, 9}
        };

        int[][] costMatrix2 = {
                {2, 1, 5, 7},
                {3, 1, 8, 9},
                {6, 2, 8, 7},
                {5, 4, 3, 2},
                {9, 10, 8, 7},
                {8, 5, 4, 9}
        };

        assertEquals(18, minCost19.getMinCostToPaint(costMatrix));
        assertEquals(20, minCost19.getMinCostToPaint(costMatrix2));
    }
}
