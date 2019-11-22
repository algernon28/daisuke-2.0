package com.daisuke.domain.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daisuke.domain.model.ComponentDTO;

@Component
public interface ComponentsAdapter<T> {
    List<ComponentDTO> findComponents(T search) throws SearchException;

    ComponentDTO findComponentByKey(String key) throws SearchException;
}
