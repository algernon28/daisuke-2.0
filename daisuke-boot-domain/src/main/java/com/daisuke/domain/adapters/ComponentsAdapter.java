package com.daisuke.domain.adapters;

import java.util.List;

import com.daisuke.domain.model.ComponentDTO;

public interface ComponentsAdapter<T> {
    List<ComponentDTO> findComponents(T search) throws SearchException;

    ComponentDTO findComponentByKey(String key) throws SearchException;
}
