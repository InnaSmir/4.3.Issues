package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssuesRepository {
    private final List<Issue> issues = new ArrayList<>();

    public void openById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpen(true);
                break;
            }
        }
    }

    public void closeById(int id){
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpen(false);
                break;
            }
        }
    }

    public List<Issue> findAll() {
        return issues;
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void removeById(long id) {
        issues.removeIf((item) -> item.getId() == id);
    }

    public void removeAll() {
        issues.clear();
    }
}
