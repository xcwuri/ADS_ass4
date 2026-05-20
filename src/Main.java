public class Main {

    public static void main(String[] args) {
        WeightedGraph<String> spaceNetwork = new WeightedGraph<>(true);

        spaceNetwork.addEdge("Earth", "Saturn", 100.0);

        spaceNetwork.addEdge("Earth", "Mars", 10.0);
        spaceNetwork.addEdge("Mars", "Jupiter", 10.0);
        spaceNetwork.addEdge("Jupiter", "Saturn", 10.0);

        spaceNetwork.addEdge("Earth", "Venus", 60.0);
        spaceNetwork.addEdge("Venus", "Saturn", 50.0);

        System.out.println("Dijkstra (Fastest Route by Time):");
        Search<String> djk = new DijkstraSearch<>(spaceNetwork, "Earth");
        outputPath(djk, "Saturn");

        System.out.println();

        System.out.println("BFS (Route with Fewest Stops):");
        Search<String> bfs = new BreadthFirstSearch<>(spaceNetwork, "Earth");
        outputPath(bfs, "Saturn");
    }

    public static void outputPath(Search<String> search, String key) {
        Iterable<String> path = search.pathTo(key);
        if (path == null) {
            System.out.println("No path found.");
            return;
        }

        for (String v : path) {
            System.out.print(v + " -> ");
        }

        System.out.println();
    }
}
