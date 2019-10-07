package com.daisuke.domain.model;

import java.util.function.Consumer;

@FunctionalInterface
public interface CQI extends Consumer<ComponentDTO>{
     void accept(ComponentDTO bean);
}
