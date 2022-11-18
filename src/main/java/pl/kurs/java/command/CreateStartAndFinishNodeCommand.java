package pl.kurs.java.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class CreateStartAndFinishNodeCommand {
    private List<Integer> start;
    private List<Integer> finish;

}
