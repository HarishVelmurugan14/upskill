package Topics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs {

    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public int mazeMinimumDistance(int[][] maze, int[] start, int[] dest) {
        int n = maze.length, m = maze[0].length;
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start[0], start[1]));

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] d : dirs) {
                int x = cur.x, y = cur.y, count = 0;
                while (isValid(n, m, x + d[0], y + d[1]) && maze[x + d[0]][y + d[1]] == 0) {
                    x += d[0];
                    y += d[1];
                    count++;
                }

                if (dist[cur.x][cur.y] + count < dist[x][y]) {
                    dist[x][y] = dist[cur.x][cur.y] + count;
                    queue.add(new Node(x, y));
                }
            }
        }

        return dist[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : dist[dest[0]][dest[1]];
    }

    public int numberOfIslandsPresent(char[][] A) {
        int n = A.length;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];
        int islands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char current = A[i][j];
                if (current == '1' && !visited[i][j]) {
                    islands++;
                    visited[i][j] = true;
                    dfs(i, j, A, n, m, visited);
                }
            }
        }
        return islands;
    }

    public void dfs(int x, int y, char[][] A, int n, int m, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int xAxis = x + directions[i][0];
            int yAxis = y + directions[i][1];
            if (isValid(n, m, xAxis, yAxis) && !visited[xAxis][yAxis]) {
                visited[xAxis][yAxis] = true;
                if (A[xAxis][yAxis] == '1') {
                    dfs(xAxis, yAxis, A, n, m, visited);
                }
            }
        }
    }

    public boolean isValid(int n, int m, int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] t : times) graph[t[0]].add(new int[]{t[1], t[2]});

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0], time = current[1];

            if (time > dist[node]) continue;

            for (int[] neighbor : graph[node]) {
                int nextNode = neighbor[0], weight = neighbor[1];
                if (dist[nextNode] > time + weight) {
                    dist[nextNode] = time + weight;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }


    public int rottenOranges(int[][] oranges) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Position> queue = new LinkedList<>();
        int m = oranges.length;
        int n = oranges[0].length;
        int freshOranges = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (oranges[i][j] == 2) {
                    queue.add(new Position(i, j));
                } else if (oranges[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        int minutes = 0;
        if (freshOranges == 0) {
            return minutes;
        }

        while (!queue.isEmpty() && freshOranges > 0) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                Position cell = queue.poll();
                for (int[] direction : directions) {
                    int row = cell.row + direction[0];
                    int col = cell.col + direction[1];
                    if (isValidCell(row, col, m, n) && oranges[row][col] == 1) {
                        oranges[row][col] = 2;
                        freshOranges--;
                        queue.add(new Position(row, col));
                    }
                }
            }
            minutes++;
        }
        return freshOranges == 0 ? minutes : -1;
    }

    public boolean isValidCell(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        int totalCost = 0;
        int nodesVisited = 0;

        // Start from point 0 with cost 0
        minHeap.add(new Edge(0, 0));

        while (!minHeap.isEmpty() && nodesVisited < n) {
            Edge current = minHeap.poll();
            int currPoint = current.point;
            int currCost = current.cost;

            if (visited[currPoint]) continue;

            visited[currPoint] = true;
            totalCost += currCost;
            nodesVisited++;

            // Add edges from current point to all unvisited points
            for (int nextPoint = 0; nextPoint < n; nextPoint++) {
                if (!visited[nextPoint]) {
                    int dist = manhattanDistance(points[currPoint], points[nextPoint]);
                    minHeap.add(new Edge(nextPoint, dist));
                }
            }
        }

        return totalCost;
    }

    private int manhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int connectingBridges(int islands, int[][] bridges) {
        HashMap<Integer, ArrayList<Bridge>> adjacencyList = buildAdj(bridges);
        PriorityQueue<Bridge> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        minHeap.add(new Bridge(1, 0));
        boolean[] visited = new boolean[islands + 1];
        int totalCost = 0;
        int islandVisitedSoFar = 0;

        while (!minHeap.isEmpty()) {
            Bridge current = minHeap.poll();
            int node = current.end;
            int cost = current.cost;
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            islandVisitedSoFar++;
            totalCost += cost;
            for (Bridge neighbour : adjacencyList.get(node)) {
                if (!visited[neighbour.end]) {
                    minHeap.add(new Bridge(neighbour.end, neighbour.cost)); // create fresh bridge
                }
            }
        }
        return islandVisitedSoFar == islands ? totalCost : -1;
    }

    public HashMap<Integer, ArrayList<Bridge>> buildAdj(int[][] bridges) {
        HashMap<Integer, ArrayList<Bridge>> map = new HashMap<>();
        for (int[] bridge : bridges) {
            int source = bridge[0];
            int dest = bridge[1];
            int cost = bridge[2];
            map.computeIfAbsent(source, k -> new ArrayList<>()).add(new Bridge(dest, cost));
            map.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Bridge(source, cost));
        }
        return map;
    }

    static class Edge {
        int point;
        int cost;

        Edge(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }
    }

    static class Bridge {
        int end, cost;

        Bridge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }


}

class Position {
    int row, col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
