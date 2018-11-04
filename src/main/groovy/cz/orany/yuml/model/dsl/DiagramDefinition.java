package cz.orany.yuml.model.dsl;

import cz.orany.yuml.model.Note;
import cz.orany.yuml.model.RelationshipType;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FirstParam;
import groovy.transform.stc.SimpleType;

public interface DiagramDefinition {

    static From getFrom() {
        return From.FROM;
    }

    static Integer getZero() {
        return 0;
    }

    static Integer getOne() {
        return 1;
    }

    static String getMany() {
        return "*";
    }

    default Note note(String text) {
        return note(text, null);
    }

    Note note(String text, String color);

    default TypeDefinition type(String name) {
        return type(name, Closure.IDENTITY);
    }

    TypeDefinition type(
        String name,
        @DelegatesTo(value = TypeDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.TypeDefinition")
        Closure<? extends DiagramContentDefinition> builder
    );

    default RelationshipDefinition aggregation(String source, String destination) {
        return aggregation(source, destination, Closure.IDENTITY);
    }

    default RelationshipDefinition aggregation(
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.AGGREGATION, destination, additionalProperties);
    }

    default RelationshipDefinition composition(String source, String destination) {
        return composition(source, destination, Closure.IDENTITY);
    }

    default RelationshipDefinition composition(
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.COMPOSITION, destination, additionalProperties);
    }

    default RelationshipDefinition inheritance(
        String source,
        String destination
    ) {
        return inheritance(source, destination, Closure.IDENTITY);
    }

    default RelationshipDefinition inheritance(
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.INHERITANCE, destination, additionalProperties);
    }

    default RelationshipDefinition association(String source, String destination) {
        return association(source, destination, Closure.IDENTITY);
    }

    default RelationshipDefinition association(
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.ASSOCIATION, destination, additionalProperties);
    }

    default RelationshipDefinition relationship(String source, RelationshipType relationshipType, String destination) {
        return relationship(source, relationshipType, destination, Closure.IDENTITY);
    }

    RelationshipDefinition relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "cz.orany.yuml.model.dsl.RelationshipDefinition")
        Closure<? extends DiagramContentDefinition> additionalProperties
    );

    default <H extends DiagramHelper> H add(Class<H> helper) {
        return (H) configure(helper, Closure.IDENTITY);
    }

    <H extends DiagramHelper, R> H configure(
        @DelegatesTo.Target("helper") Class<H> helper,
        @DelegatesTo(value = DelegatesTo.Target.class, target = "helper", strategy = Closure.DELEGATE_FIRST, genericTypeIndex = 0)
        @ClosureParams(FirstParam.FirstGenericType.class)
        Closure<R> configuration
    );

    void postprocess();

}
