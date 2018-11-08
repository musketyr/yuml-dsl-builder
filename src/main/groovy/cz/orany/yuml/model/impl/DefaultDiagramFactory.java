package cz.orany.yuml.model.impl;

import cz.orany.yuml.model.Diagram;
import cz.orany.yuml.model.dsl.DiagramDefinition;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.util.function.Consumer;

public class DefaultDiagramFactory {

    public static Diagram build(Consumer<DiagramDefinition> definition) {
        DefaultDiagram diagram = new DefaultDiagram();
        definition.accept(diagram);
        diagram.postprocess();
        return diagram;
    }

}
