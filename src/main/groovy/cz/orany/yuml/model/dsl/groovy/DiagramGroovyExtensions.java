package cz.orany.yuml.model.dsl.groovy;

import cz.orany.yuml.model.RelationshipType;
import cz.orany.yuml.model.dsl.*;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.transform.NamedParam;
import groovy.transform.NamedParams;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FirstParam;
import groovy.transform.stc.SimpleType;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

import java.util.Map;

public class DiagramGroovyExtensions {

    private static final String CARDINALITY = "cardinality";
    private static final String TITLE = "title";

    public static TypeDefinition type(
        DiagramDefinition self,
        String name,
        @DelegatesTo(value = TypeDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.TypeDefinition")
        Closure<? extends DiagramContentDefinition> builder
    ) {
        return self.type(name, ConsumerWithDelegate.create(builder));
    }

    public static RelationshipDefinition aggregation(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.AGGREGATION, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition composition(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.COMPOSITION, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition inheritance(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.INHERITANCE, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition association(
        DiagramDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.ASSOCIATION, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition relationship(
        DiagramDefinition self,
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return self.relationship(source, relationshipType, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition source(
        RelationshipDefinition self,
        @NamedParams({
            @NamedParam(value = CARDINALITY, type = String.class),
            @NamedParam(value = TITLE, type = String.class)
        })
        Map<String, String> cardinalityAndTitle
    ) {
        return self.source(cardinalityAndTitle.get(CARDINALITY), cardinalityAndTitle.get(TITLE));
    }

    public static RelationshipDefinition destination(
        RelationshipDefinition self,
        @NamedParams({
            @NamedParam(value = CARDINALITY, type = String.class),
            @NamedParam(value = TITLE, type = String.class)
        })
            Map<String, String> cardinalityAndTitle
    ) {
        return self.destination(cardinalityAndTitle.get(CARDINALITY), cardinalityAndTitle.get(TITLE));
    }

    public static <H extends DiagramHelper, R> H configure(
        DiagramDefinition self,
        @DelegatesTo.Target("helper") Class<H> helper,
        @DelegatesTo(value = DelegatesTo.Target.class, target = "helper", strategy = Closure.DELEGATE_FIRST, genericTypeIndex = 0)
        @ClosureParams(FirstParam.FirstGenericType.class)
        Closure<R> configuration
    ) {
        return self.configure(helper, ConsumerWithDelegate.create(configuration));
    }

}
