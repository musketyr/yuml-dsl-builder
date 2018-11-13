package cz.orany.yuml.model.dsl.groovy

import cz.orany.yuml.model.RelationshipType
import cz.orany.yuml.model.dsl.*
import groovy.lang.Closure
import groovy.lang.DelegatesTo
import groovy.transform.NamedDelegate
import groovy.transform.NamedVariant
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam
import groovy.transform.stc.SimpleType
import space.jasan.support.groovy.closure.ConsumerWithDelegate

class DiagramGroovyExtensions {

    static TypeDefinition type(
        DiagramDefinition self,
        String name,
        @DelegatesTo(value = TypeDefinition, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType, options = "cz.orany.yuml.model.dsl.TypeDefinition")
        Closure<? extends DiagramContentDefinition> builder
    ) {
        return self.type(name, ConsumerWithDelegate.create(builder))
    }

    static RelationshipDefinition aggregation(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.AGGREGATION, destination, ConsumerWithDelegate.create(additionalProperties))
    }

    static RelationshipDefinition composition(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.COMPOSITION, destination, ConsumerWithDelegate.create(additionalProperties))
    }

    static RelationshipDefinition inheritance(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.INHERITANCE, destination, ConsumerWithDelegate.create(additionalProperties))
    }

    static RelationshipDefinition association(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.ASSOCIATION, destination, ConsumerWithDelegate.create(additionalProperties))
    }

    static RelationshipDefinition relationship(
        DiagramDefinition self,
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, relationshipType, destination, ConsumerWithDelegate.create(additionalProperties))
    }

    static <H extends DiagramHelper, R> H configure(
        DiagramDefinition self,
        @DelegatesTo.Target("helper") Class<H> helper,
        @DelegatesTo(value = DelegatesTo.Target, target = "helper", strategy = Closure.DELEGATE_FIRST, genericTypeIndex = 0)
        @ClosureParams(FirstParam.FirstGenericType)
        Closure<R> configuration
    ) {
        return self.configure(helper, ConsumerWithDelegate.create(configuration))
    }

    @NamedVariant
    static RelationshipDefinition source(RelationshipDefinition self, @NamedDelegate CardinalityAndTitle cardinalityAndTitle) {
        self.source(cardinalityAndTitle.cardinality, cardinalityAndTitle.title)
    }

}
