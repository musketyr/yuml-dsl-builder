package cz.orany.yuml.model;

import cz.orany.yuml.model.dsl.DiagramDefinition;
import cz.orany.yuml.model.impl.DefaultDiagramFactory;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

import java.util.Collection;
import java.util.Map;

public interface Diagram {

    static Diagram build(@DelegatesTo(value = DiagramDefinition.class, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        return DefaultDiagramFactory.build(definition);
    }

    Collection<? extends Note> getNotes();
    Collection<? extends Type> getTypes();
    Collection<? extends Relationship> getRelationships();

    Map<String, Object> getMetadata();

}
