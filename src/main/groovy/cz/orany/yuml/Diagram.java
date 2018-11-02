package cz.orany.yuml;

import cz.orany.yuml.dsl.DiagramDefinition;
import cz.orany.yuml.impl.DefaultDiagram;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.util.Collection;

public interface Diagram {

    static Diagram build(@DelegatesTo(value = DiagramDefinition.class, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        Diagram diagram = new DefaultDiagram();
        DefaultGroovyMethods.with(diagram, definition);
        return diagram;
    }

    Collection<? extends Note> getNotes();
    Collection<? extends Type> getTypes();
    Collection<? extends Relationship> getRelationships();

    String toYuml();

}
