package pl.kurs.java;

import org.springframework.boot.SpringApplication;
import pl.kurs.java.model.Board;
import pl.kurs.java.service.Algorithms;

public class Main {
    public static void main(String[] args) {
        //SpringApplication.run(Main.class, args);

       /*Board board = new Board();
        board.setStartNode(1,1);
        board.setFinishNode(7,7);
        board.setCostsOnNodes();
        board.search();
        System.out.println(board.trackThePath());*/

        Board board = new Board();
        board.setStartNode(1,1);
        board.setFinishNode(7,7);
        board.setCostsOnNodes();
        Algorithms algorithms = new Algorithms(board);
        algorithms.search();
        System.out.println(algorithms.trackThePath());

    }
}