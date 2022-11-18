package pl.kurs.java.service;

import pl.kurs.java.interfaces.BoardParameters;
import pl.kurs.java.model.Board;
import pl.kurs.java.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Algorithms implements IAlgorithms {

    private Board board;
    private Node[][] arraysWithNodes;

    private List<Node> openedNodes = new ArrayList<>();
    Node currentNode;
    Node finishNode;

    public Algorithms(Board board) {
        this.board = board;
        arraysWithNodes = board.getArraysWithNodes();
        currentNode = board.getCurrentNode();
        finishNode = board.getFinishNode();
        search();
    }

    @Override
    public void search() {


        while (currentNode != finishNode) {

            int column = currentNode.getColumn();
            int row = currentNode.getRow();

            currentNode.setAsChecked();
            openedNodes.remove(currentNode);

            //open upper node
            if (row - 1 >= 0 && row - 1 < BoardParameters.NUMBER_OF_ROWS) {
                addToListOfOpenNodes(arraysWithNodes[column][row - 1]);
            }
            //open lower node
            if (row + 1 >= 0 && row + 1 < BoardParameters.NUMBER_OF_ROWS) {
                addToListOfOpenNodes(arraysWithNodes[column][row + 1]);
            }
            //open left node
            if (column - 1 >= 0 && column - 1 < BoardParameters.NUMBER_OF_COLUMNS) {
                addToListOfOpenNodes(arraysWithNodes[column - 1][row]);
            }
            //open right node
            if (column + 1 >= 0 && column + 1 < BoardParameters.NUMBER_OF_COLUMNS) {
                addToListOfOpenNodes(arraysWithNodes[column + 1][row]);
            }

            //znajdowanie najlepszego noda
            int bestNodeIndex = 0;
            int bestNodeFcost = Integer.MAX_VALUE;


            for (int i = 0; i < openedNodes.size(); i++) {
                //sprawdzenie ktory node ma najnizszy fCost
                if (openedNodes.get(i).getFCost() < bestNodeFcost) {
                    bestNodeIndex = i;
                    bestNodeFcost = openedNodes.get(i).getFCost();
                }
                // jesli nody mają ten sam fCost sprawdzamy G cost
                else if (openedNodes.get(i).getFCost() == bestNodeFcost) {
                    if (openedNodes.get(i).getGCost() < openedNodes.get(bestNodeIndex).getGCost()) {
                        bestNodeIndex = i;
                    }
                }
            }

            // po sprawdzeniu ktory node będzie najlepszy, bedzie on następnym krokiem
            currentNode = openedNodes.get(bestNodeIndex);
            if (currentNode == finishNode) {
                trackThePath();
            }


        }

    }

    @Override
    public void addToListOfOpenNodes(Node node) {
        if (!node.isOpen() && !node.isChecked() && !node.isObstruction()) {
            node.setAsOpen();
            node.parent = board.getCurrentNode();
            openedNodes.add(node);
        }
    }

    @Override
    public List<Node> trackThePath() {
        List<Node> listOfNodes = new ArrayList<>();
        Node current = board.getFinishNode();
        listOfNodes.add(board.getFinishNode());

        while (current != board.getStartNode()) {
            current = current.parent;
            listOfNodes.add(current);
            current.setAsPath();
        }
        Collections.reverse(listOfNodes);
        return listOfNodes;
    }
}
