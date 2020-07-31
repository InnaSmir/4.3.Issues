package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssuesRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IssuesManagerTest {

    private IssuesRepository repository = new IssuesRepository();
    private IssuesManager manager = new IssuesManager(repository);

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
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
    }

    @Test
    void shouldOpenById() {
        manager.openById(3);
        assertTrue(issue3.isOpen());
    }

    @Test
    void shouldCloseById() {
        manager.closeById(4);
        assertFalse(issue4.isOpen());
    }

    @Test
    void shouldFindAllOpen() {
        List<Issue> actual = manager.findAllOpen();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3);
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindAllClosed() {
        List<Issue> actual = manager.findAllClosed();
        List<Issue> expected = Arrays.asList(issue4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthorIfExist() {
        String author = "author1";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> actual = manager.filterByAuthor(predicate);
        List<Issue> expected = Arrays.asList(issue1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthorIfNotExist() {
        String author = "author5";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> actual = manager.filterByAuthor(predicate);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }


    @Test
    void shouldFilterByLabelIfExist() {
        Predicate<HashSet> equalLabel = t -> t.equals(label2);
        List<Issue> actual = manager.filterByLabel(equalLabel);
        List<Issue> expected = Arrays.asList(issue2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExist() {
        HashSet<String> label7 = new HashSet<>((Arrays.asList("label7")));
        Predicate<HashSet> equalLabel = t -> t.equals(label7);
        List<Issue> actual = manager.filterByLabel(equalLabel);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssigneeIfExist() {
        Predicate<HashSet> equalAssignee = t -> t.equals(assignee3);
        List<Issue> actual = manager.filterByAssignee(equalAssignee);
        List<Issue> expected = Arrays.asList(issue3);
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFilterByAssigneeIfNotExist() {
        Predicate<HashSet> equalAssignee = t -> t.equals(assignee4);
        List<Issue> actual = manager.filterByAssignee(equalAssignee);
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }
}
