///*
//# Given a grid, entry at the top left corner, exit at the bottom right corner.
//Find if there is a path from entry to exit or not. Return true or false.
//
//# Return true
//# 0 0 0 0 0
//# 1 1 1 1 0
//# 0 0 0 0 0
//# 0 1 1 1 1
//# 0 0 0 0 0
//
//# False
//# 0 0 0 0 0
//# 1 1 1 1 0
//# 0 0 0 0 0
//# 1 1 1 1 1
//# 0 0 0 0 0
//
//0 1 1 1 1
//1 1 1 1 1
//1 1 1 1 1
//1 1 1 1 1
//1 1 1 1 1
//
//    1 1 1
//    1 1 1
//*/
//
//Time: n*m
//        Space: n*m
//
//
//public boolean findWay(int[][] matrix) {
//
//        boolean isWay = false;
//
//        // logic
//
//        int i = 0, j = 0;
//        int[][] dir = new int[][]{
//        //{-1,0}  // N
//        {1,0}   // S
//        //{0,-1}  // W
//        {0,1}  // E
//        }
//
//        isWay = traverse(matrix,i,j, dir);
//
//        return isWay;
//        }
//
//        boolean traverse(int[][] matrix int i, int j, int[][] dir) {
//
//        // base condition
//        int n = matrix.length;
//        int m = matrix[0].length;
//
//        if(i==n-1 && j==m-1) {
//        if(matrix[i][j] == 0) {
//        return true;
//        }
//        else {
//        return false;
//        }
//        }
//
//        if(withInBoundry(i,j,n,m)) {
//
//      /*
//      0 0 1 1 1  n=5, m=5
//      0 0 1 1 1    i=0, j=0
//      1 1 1 1 1
//      1 1 1 1 1
//      1 1 1 1 1
//      */
//
//        if(matrix[i][j] == 0) {
//        for(int k=0; k<dir.length; k++) {
//
//        boolean found = traverse(matrix, i+dir[k][0], j+dir[k][1], dir);
//
//        if(found)  return true;
//        }
//        }
//        }
//
//        return false;
//        }
//
//
///*
//4 directions  -- N, W, S, E
//    n * m
//
//    go through first element (0,0)
//
//    base case
//    i == n
//    j == m
//
//    TRUE
//
//    if coordinate ended - out of range
//
//    false
//
//    --
//    check if it is 0
//
//    check all directions   N W S E
//*/
//
//
//
//
//
//
//
///*
//Given a collection of intervals, merge all overlapping intervals.
//Input:  [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//*/
//
//        Time: O(n)
//        space: O(n)
//
//        3 > 2
//
//        [1, 3] [-3,-1]
//
//        3 > -3
//
//        1 > -1
//
//        3> 2
//        1< 6
//
//        10> 15
//
//        [a,b] [c,d]    -> [a,d]
//        b>c
//        a<d
//
//  [3,7] [6,9]
//          [3,7] [-3,-1]
//
//
//          List<Integer[]> mergeIntervals(int[][] arr) {
//
//        List<Integer[]> output = new ArrayList<>(Integer[]);
//
//        // logix
//        for(int i=1; i<arr.length-1; i++) {
//        int a = arr[i-1][0];
//        int b = arr[i-1][1];
//        int c = arr[i][0];
//        int d = arr[i][1];
//
//        if(b > c && a < d) {
//        output.add(new Integer[]{a,d});
//        }
//        else {
//        output.add(new Integer[]{});
//        }
//        }
//
//        return output;
//        }
//
//
