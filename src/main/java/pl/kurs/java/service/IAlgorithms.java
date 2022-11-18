package pl.kurs.java.service;

import pl.kurs.java.model.Node;

import java.util.List;

public interface IAlgorithms {

    void search();
    void addToListOfOpenNodes(Node node);
    List<Node> trackThePath();
}
