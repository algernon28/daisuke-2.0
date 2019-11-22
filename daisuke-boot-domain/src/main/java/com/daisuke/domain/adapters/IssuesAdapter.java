package com.daisuke.domain.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daisuke.domain.model.IssueDTO;

@Component
public interface IssuesAdapter<T> {

    List<IssueDTO> findIssues(T search) throws SearchException;

    IssueDTO findIssueByKey(String key) throws SearchException;

}
