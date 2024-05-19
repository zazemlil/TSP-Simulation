package Model.interfaces;

public interface IModel {
    void addVertex(int vertex);
    void deleteVertex(int vertex);
    void setRay(int vertex1, int vertex2, int weight);
    void deleteRay(int vertex1, int vertex2);
    void setStartPoint(int vertex);
    void setEndPoint(int vertex);
    int tsp();
    void clear();
}
