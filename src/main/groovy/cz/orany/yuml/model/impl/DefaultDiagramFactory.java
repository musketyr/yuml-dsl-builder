package cz.orany.yuml.model.impl;

import cz.orany.yuml.model.Diagram;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

public class DefaultDiagramFactory {

    public static Diagram build(@DelegatesTo(value = cz.orany.yuml.model.dsl.DiagramDefinition.class, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        DefaultDiagram diagram = new DefaultDiagram();
        DefaultGroovyMethods.with(diagram, definition);
        return diagram;
    }

}