import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class MinCostToPaintNum19 {

    class ValIndex {
        int val, index;
        ValIndex(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public int getMinCostToPaint(int[][] A) {
        if(A == null || A.length == 0 || A[0].length == 0) return 0;

        int N = A.length;
        int K = A[0].length;
        int[][] dp = new int[N][K];

        for(int i = 0; i < K; i++) {
            dp[0][i] = A[0][i];
        }

        PriorityQueue<ValIndex> pq = new PriorityQueue<ValIndex>((a, b) -> (a.val - b.val));
        for(int i = 1; i < N; i++) {
            pq.clear();
            getPriorityQueue(A, i-1, pq);
            for(int j = 0; j < K; j++) {
                if(pq.peek().index != j) {
                    dp[i][j] = A[i][j] + dp[i-1][pq.peek().index];
                } else {
                    ValIndex temp = pq.poll();
                    dp[i][j] = A[i][j] + dp[i-1][pq.peek().index];
                    pq.offer(temp);
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for(int j = 0; j < K; j++) {
            minCost = Math.min(dp[N-1][j], minCost);
        }
        printDP(dp);

        return minCost;
    }

    public void getPriorityQueue(int[][] A, int i, PriorityQueue<ValIndex> pq) {
        for(int j = 0; j < A[i].length; j++) {
            pq.offer(new ValIndex(A[i][j], j));
        }
    }

    private void printDP(int[][] dp) {
        System.out.println();
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

}
