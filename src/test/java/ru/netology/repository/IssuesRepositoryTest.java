package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssuesRepositoryTest {
    private IssuesRepository repository = new IssuesRepository();

    private HashSet<String> assignee1 = new HashSet<>((Arrays.asList("assignee1")));
    private HashSet<String> assignee2 = new HashSet<>((Arrays.asList("assignee2")));
    private HashSet<String> assignee3 = new HashSet<>((Arrays.asList("assignee3")));
    private HashSet<String> assignee4 = new HashSet<>((Arrays.asList("assignee4")));

    private HashSet<String> label1 = new HashSet<>((Arrays.asList("label1")));
    private HashSet<String> label2 = new HashSet<>((Arrays.asList("label2")));
    private HashSet<String> label3 = new HashSet<>((Arrays.asList("label3")));
    private HashSet<String> label4 = new HashSet<>((Arrays.asList("label4")));

    private Issue issue1 = new Issue(1, "author1", "03.01.2020", true, label1, assignee1, 5);
    private Issue issue2 = new Issue(2, "author2", "03.02.2020", true, label2, assignee2, 2);
    private Issue issue3 = new Issue(3, "author3", "03.03.2020", true, label3, assignee3, 3);
    private Issue issue4 = new Issue(4, "author4", "03.04.2020", false, label4, assignee2, 0);

    @BeforeEach
    public void setUp() {
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
    }

    @Test
    void shouldOpenById() {
        repository.openById(1);
        assertTrue(issue1.isOpen());
    }

    @Test
    void shouldCloseById() {
        repository.closeById(4);
        assertFalse(issue4.isOpen());
    }

    @Test
    void shouldFindAll() {
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(issue1,issue2,issue3,issue4);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = repository.findById(2);
        Issue expected = issue2;
        assertEquals(expected,actual);
    }

    @Test
    public void shouldRemoveById() {
        int idToRemove = 3;
        repository.removeById(idToRemove);
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(issue1,issue2,issue4);
        assertEquals(expected,actual);
    }

    @Test
    void shouldRemoveAll() {
        repository.removeAll();
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

}
