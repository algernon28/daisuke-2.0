package com.daisuke.domain.adapters;

import java.util.List;

import com.daisuke.domain.model.IssueDTO;

public interface IssuesAdapter<T> {

    List<IssueDTO> findIssues(T search) throws SearchException;

    IssueDTO findIssueByKey(String key) throws SearchException;

}
