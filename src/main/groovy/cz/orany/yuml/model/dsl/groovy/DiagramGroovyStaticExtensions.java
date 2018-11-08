package cz.orany.yuml.model.dsl.groovy;

import cz.orany.yuml.model.Diagram;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

public class DiagramGroovyStaticExtensions {

    public static Diagram build(Diagram self, @DelegatesTo(value = cz.orany.yuml.model.dsl.DiagramDefinition.class, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        return Diagram.create(ConsumerWithDelegate.create(definition));
    }

}
