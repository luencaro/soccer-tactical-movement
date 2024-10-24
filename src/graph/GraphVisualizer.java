/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

import java.util.Random;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

public class GraphVisualizer {

    public static Graph visualizeGraph(MyGraph customGraph) {
        // Crear un nuevo GraphStream graph
        Graph graphStream = new SingleGraph("Football Field");

        // Añadir nodos al grafo de GraphStream desde el grafo personalizado
        for (Vertex vertex : customGraph.getVertices()) {
            Node node = graphStream.addNode(vertex.getData().getName());
            // Configurar estilos para el nodo
            node.setAttribute("ui.label", vertex.getData().getName()); // Mostrar el nombre del jugador
        }

        // Definir los colores para las aristas
        String[] colors = {"#FFB74D, #FF9800, #F57C00"};
        Random random = new Random();

        // Añadir aristas basadas en la lista de adyacencia
        for (Vertex vertex : customGraph.getVertices()) {
            String vertexName = vertex.getData().getName();
            for (Vertex adj : customGraph.getAdjVertices(vertex.getData())) {
                String adjName = adj.getData().getName();

                // Solo añadir la arista si no existe ya (evitar duplicados en grafos no dirigidos)
                String edgeId = vertexName + "-" + adjName;
                if (graphStream.getEdge(edgeId) == null && graphStream.getEdge(adjName + "-" + vertexName) == null) {
                    Edge edge = graphStream.addEdge(edgeId, vertexName, adjName, true);
                    
                    // Asignar un color aleatorio de la lista
                    String color = colors[random.nextInt(colors.length)];
                    edge.setAttribute("ui.style", "fill-color: " + color + ";");
                }
            }
        }

        // Aplicar estilo avanzado al grafo para mejor visualización
String css = """
    graph {
        padding: 40px;
        fill-mode: gradient-vertical;
        fill-color: gray, white, white, white, white, gray; // Gradiente de naranja para el fondo
    }
    
    node {
        size: 15px;
        fill-mode: gradient-vertical;
        fill-color: #FFB74D, #FF9800, #F57C00; // Gradiente de naranja para nodos
        stroke-mode: plain;
        stroke-color: black;
        shadow-mode: plain;
        shadow-width: 0px;
        shadow-color: #999;
        shadow-offset: 3px, -3px;
        text-color: white;
        text-background-mode: rounded-box;
        text-background-color: black;
        text-padding: 3px, 2px;
        text-alignment: at-right;
    }
    
    edge {
        shape: cubic-curve;
        arrow-shape: diamond;
        size: 1px;
        fill-mode: gradient-vertical;
        fill-color: #FFB74D, #FF9800, #F57C00; // Gradiente de grises para aristas
    }
""";

// Aplicar el CSS al grafo
graphStream.setAttribute("ui.stylesheet", css);

        graphStream.setAttribute("ui.quality");
        graphStream.setAttribute("ui.antialias");

        // Mostrar el grafo
        graphStream.display();

        return graphStream;
    }
}

