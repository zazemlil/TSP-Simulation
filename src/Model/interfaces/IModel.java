package Model.interfaces;

public interface IModel {
    void addVertex(int vertex);
    void setRay(int vertex1, int vertex2, int weight);
    int tsp();
    void clear();
}
