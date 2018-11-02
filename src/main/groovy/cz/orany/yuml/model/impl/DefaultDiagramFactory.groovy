package cz.orany.yuml.model.impl

import cz.orany.yuml.model.Diagram
import cz.orany.yuml.model.dsl.DiagramDefinition
import groovy.transform.CompileStatic

@CompileStatic
class DefaultDiagramFactory {

    static Diagram build(@DelegatesTo(value = DiagramDefinition, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        DefaultDiagram diagram = new DefaultDiagram()
        diagram.with definition
        diagram
    }

}
