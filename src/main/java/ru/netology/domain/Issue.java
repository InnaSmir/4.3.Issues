package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    private int id;
    private String author;
    private String date;
    private boolean open;
    private HashSet<String> label;
    private HashSet<String> assignees;
    private int countComments;
}
