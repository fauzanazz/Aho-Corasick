package fauzannazz.ahocorasick;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphShow {
    public AnchorPane root;
    protected static String styleSheet =
            "graph {" +
                    "    padding: 60px;" +
                    "}" +
                    "node {" +
                    "    text-size: 30px;" +
                    "    text-alignment: under;" +  // Align text under the node
                    "    text-offset: 0, 15px;" +  // Offset text by 15 pixels down
                    "}";

    public void start(Ahocorasick ahocorasick) {
        Graph graph = new MultiGraph("mg");

        FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        addNodesAndEdges(graph, ahocorasick);

        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.stylesheet", styleSheet);

        FxViewPanel v = (FxViewPanel) viewer.addDefaultView(false);

        root.getChildren().add(v);
    }

    private void addNodesAndEdges(Graph graph, Ahocorasick ac) {
        Map<Ahocorasick.Node, String> nodeIds = new HashMap<>();
        Queue<Ahocorasick.Node> queue = new LinkedList<>();
        Map<Ahocorasick.Node, Integer> levels = new HashMap<>();
        queue.add(ac.getRoot());
        nodeIds.put(ac.getRoot(), "root");
        levels.put(ac.getRoot(), 0);
        graph.addNode("root").setAttribute("ui.label", "root");

        while (!queue.isEmpty()) {
            Ahocorasick.Node current = queue.poll();
            String currentId = nodeIds.get(current);
            int level = levels.get(current);
            int position = 0;

            for (int i = 0; i < Ahocorasick.ALPHABET_SIZE; i++) {
                Ahocorasick.Node child = current.children[i];
                if (child != null) {
                    String childId = currentId + "-" + (char) i;
                    nodeIds.put(child, childId);
                    levels.put(child, level + 1);
                    StringBuilder sentence = new StringBuilder();
                    if (!currentId.equals("root")) {
                        sentence.append(currentId.replace("root-", "")).append((char) i);
                    } else {
                        sentence.append((char) i);
                    }
                    graph.addNode(childId).setAttribute("ui.label", sentence.toString());
                    graph.addEdge(currentId + "-" + childId, currentId, childId, true).setAttribute("ui.style", "fill-color: black;");
                    queue.add(child);
                }
            }

            // Set the position of the current node
            double x = position * 2 - Math.pow(2, level);
            double y = -level * 2;
            graph.getNode(currentId).setAttribute("xyz", x, y, 0);
            position++;
        }

        for (Ahocorasick.Node node : nodeIds.keySet()) {
            if (node.fail != null) {
                String currentId = nodeIds.get(node);
                String failId = nodeIds.get(node.fail);
                graph.addEdge(currentId + "-fail-" + failId, currentId, failId, true).setAttribute("ui.style", "fill-color: red;");
            }
        }
    }
}
