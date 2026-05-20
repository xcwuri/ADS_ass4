import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V data) {
        if (hasVertex(data))
            return;

        map.put(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return; // reject parallels & self-loops

        Vertex<V> vSource = map.get(source);
        Vertex<V> vDest = map.get(dest);

        // Add to adjacency map
        vSource.addAdjacentVertex(vDest, weight);

        if (undirected) {
            vDest.addAdjacentVertex(vSource, weight);
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> v : map.values()) {
            count += v.getAdjacentVertices().size();
        }

        if (undirected)
            count /= 2;

        return count;
    }

    public boolean hasVertex(V data) {
        return map.containsKey(data);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source)) return false;
        Vertex<V> vSource = map.get(source);
        Vertex<V> vDest = map.get(dest);

        return vSource.getAdjacentVertices().containsKey(vDest);
    }

    // Unwrapped list of adjacent data to make Searches easier
    public List<V> adjacencyList(V data) {
        if (!hasVertex(data)) return null;

        List<V> vertices = new LinkedList<>();
        for (Vertex<V> neighbor : map.get(data).getAdjacentVertices().keySet()) {
            vertices.add(neighbor.getData());
        }

        return vertices;
    }

    // Helper method for Dijkstra to access the actual Vertex object
    public Vertex<V> getVertex(V data) {
        return map.get(data);
    }
}