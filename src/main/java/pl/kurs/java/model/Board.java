package pl.kurs.java.model;

import lombok.Getter;
import lombok.Setter;
import pl.kurs.java.interfaces.BoardParameters;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Board implements BoardParameters {

    private Node[][] arraysWithNodes = new Node[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];

    private Node startNode, finishNode, currentNode;

    private List<Node> openedList = new ArrayList<>();


    public Board() {
        setAllNodes();
        setObstructionNode();
    }

    public void setAllNodes() {

        int column = 0;
        int row = 0;

        while (column < NUMBER_OF_COLUMNS && row < NUMBER_OF_ROWS) {

            arraysWithNodes[column][row] = new Node(column, row);

            column++;
            if (column == NUMBER_OF_COLUMNS) {
                column = 0;
                row++;
            }

        }
    }

    public void setStartNode(int column, int row) {
        arraysWithNodes[column][row].setAsStart();
        startNode = arraysWithNodes[column][row];
        currentNode = startNode;
    }

    public void setFinishNode(int column, int row) {
        arraysWithNodes[column][row].setAsFinish();
        finishNode = arraysWithNodes[column][row];
    }

    public void setObstructionNode() {
        for (int i = 2; i < 7; i++) {
            arraysWithNodes[7][i].setAsObstruction();
        }
        for (int i = 1; i < 7; i++) {
            arraysWithNodes[i][7].setAsObstruction();
        }
    }

    public void setGCostOfNode(Node node) {
        int xDistance = Math.abs(node.getColumn() - startNode.getColumn());
        int yDistance = Math.abs(node.getRow() - startNode.getRow());
        node.setGCost(xDistance + yDistance);
    }

    public void setHCostOfNode(Node node) {
        int xDistance = Math.abs(node.getColumn() - finishNode.getColumn());
        int yDistance = Math.abs(node.getRow() - finishNode.getRow());
        node.setHCost(xDistance + yDistance);
    }

    public void setFCostOfNode(Node node) {
        node.setFCost(node.getGCost() + node.getHCost());
    }

    public void setAllCostsTogether(Node node) {
        setGCostOfNode(node);
        setHCostOfNode(node);
        setFCostOfNode(node);
    }

    public void setCostsOnNodes() {
        int column = 0;
        int row = 0;

        while (column < NUMBER_OF_COLUMNS && row < NUMBER_OF_ROWS) {
            setAllCostsTogether(arraysWithNodes[column][row]);
            column++;
            if (column == NUMBER_OF_COLUMNS) {
                column = 0;
                row++;
            }
        }
    }

   /* public void search() {

        while (currentNode != finishNode) {

            int column = currentNode.getColumn();
            int row = currentNode.getRow();

            currentNode.setAsChecked();
            openedList.remove(currentNode);

            //open upper node
            if (row - 1 >= 0) {
                openNode(arraysWithNodes[column][row - 1]);
            }
            //open lower node
            if (row + 1 >= 0) {
                openNode(arraysWithNodes[column][row + 1]);
            }
            //open left node
            if (column - 1 >= 0) {
                openNode(arraysWithNodes[column - 1][row]);
            }
            //open right node
            if (column + 1 >= 0) {
                openNode(arraysWithNodes[column + 1][row]);
            }

            //znajdowanie najlepszego noda
            int bestNodeIndex = 0;
            int bestNodeFcost = Integer.MAX_VALUE;


            for (int i = 0; i < openedList.size(); i++) {
                //sprawdzenie ktory node ma najnizszy fCost
                if (openedList.get(i).getFCost() < bestNodeFcost) {
                    bestNodeIndex = i;
                    bestNodeFcost = openedList.get(i).getFCost();
                }
                // jesli nody mają ten sam fCost sprawdzamy G cost
                else if (openedList.get(i).getFCost() == bestNodeFcost) {
                    if (openedList.get(i).getGCost() < openedList.get(bestNodeIndex).getGCost()) {
                        bestNodeIndex = i;
                    }
                }
            }

            // po sprawdzeniu ktory node będzie najlepszy, bedzie on następnym krokiem
            currentNode = openedList.get(bestNodeIndex);
            if (currentNode == finishNode) {
                trackThePath();
            }


        }
    }*/

   /* public void openNode(Node node) {
        if (!node.isOpen() && !node.isChecked() && !node.isObstruction()) {
            node.setAsOpen();
            node.parent = currentNode;
            openedList.add(node);
        }
    }*/

   /* public List<Node> trackThePath() {
        List<Node> listOfNodes = new ArrayList<>();
        Node current = finishNode;
        listOfNodes.add(finishNode);

        while (current != startNode) {
            current = current.parent;
            listOfNodes.add(current);
            current.setAsPath();
        }
        Collections.reverse(listOfNodes);
        return listOfNodes;
    }
*/
}
