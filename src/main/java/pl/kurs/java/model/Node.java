package pl.kurs.java.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
@Getter
@Setter
public class Node {

    public Node parent;
    private int column;
    private int row;
    private int gCost;
    private int hCost;
    private int fCost;

    private boolean start;
    private boolean end;
    private boolean obstruction;
    private boolean open;
    private boolean checked;
    private boolean path;

    public Node(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public void setAsStart() {
        start = true;
    }

    public void setAsFinish() {
        end = true;
    }

    public void setAsObstruction() {
        obstruction = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if (!start && !end) {
            checked = true;
        }
    }

    public void setAsPath() {
        path = true;
    }

    @Override
    public String toString() {
        return "Node{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
