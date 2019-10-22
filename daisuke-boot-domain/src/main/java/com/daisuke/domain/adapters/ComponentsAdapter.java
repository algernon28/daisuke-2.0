package com.daisuke.domain.adapters;

import java.util.List;

import com.daisuke.domain.model.ComponentDTO;

@FunctionalInterface
public interface  ComponentsAdapter<T> {
    List<ComponentDTO> findComponents(T search) throws SearchException;
}
