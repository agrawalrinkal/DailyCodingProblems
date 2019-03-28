/**
 * This problem was asked by Google.
 *
 * You are given an M by N matrix consisting of booleans that represents a board. Each True boolean represents a wall.
 * Each False boolean represents a tile you can walk on.
 *
 * Given this matrix, a start coordinate, and an end coordinate, return the minimum number of steps required to reach
 * the end coordinate from the start. If there is no possible path, then return null. You can move up, left, down, and right.
 * You cannot move through walls. You cannot wrap around the edges of the board.
 *
 * For example, given the following board:
 *
 * [[f, f, f, f],
 * [t, t, f, t],
 * [f, f, f, f],
 * [f, f, f, f]]
 * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps required to reach the end is 7,
 * since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
 */





package impl;

import java.util.Deque;
import java.util.LinkedList;

public class WalkThroughMatrixWithWalls {

    public int getSourceToDestMinPath(boolean[][] matrix, int[] source, int[] dest) {
        if(matrix == null || matrix.length == 0 || source == null || dest == null || matrix[source[0]][source[1]]) return 0;
        int m = matrix.length, n = matrix[0].length;

        int[][] directions = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(source);
        matrix[source[0]][source[1]] = true;
        int jumps = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] cur = queue.poll();
                for(int[] dir : directions) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if(x < 0 || y < 0 || x >= m || y >= n || matrix[x][y]) continue;
//                    System.out.println(cur[0] + ", " + cur[1]);
                    if(x == dest[0] && y == dest[1]) {
                        return jumps + 1;
                    }
                    queue.offer(new int[]{x, y});
                    matrix[x][y] = true;
                }
            }
            jumps++;
        }
        return -1;
    }
}
