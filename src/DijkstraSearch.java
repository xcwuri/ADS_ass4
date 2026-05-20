import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<V> unsettledNodes;
    private final Map<V, Double> distances;
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;

        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            V currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            Vertex<V> currentVertex = graph.getVertex(currentNode);

            // Iterate over the internal map directly
            for (Map.Entry<Vertex<V>, Double> edge : currentVertex.getAdjacentVertices().entrySet()) {
                V neighbor = edge.getKey().getData();
                double weight = edge.getValue();

                if (!marked.contains(neighbor)) {
                    double newDistance = getShortestDistance(currentNode) + weight;

                    if (getShortestDistance(neighbor) > newDistance) {
                        distances.put(neighbor, newDistance);
                        edgeTo.put(neighbor, currentNode);
                        unsettledNodes.add(neighbor);
                    }
                }
            }
        }
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
                continue;
            }

            if (getShortestDistance(vertex) < getShortestDistance(minimum))
                minimum = vertex;
        }

        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}