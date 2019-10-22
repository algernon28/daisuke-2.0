package com.daisuke.domain.adapters;

import java.util.List;

import com.daisuke.domain.model.IssueDTO;

@FunctionalInterface
public interface IssuesAdapter<T> {
    List<IssueDTO> findIssues(T search) throws SearchException;
}
