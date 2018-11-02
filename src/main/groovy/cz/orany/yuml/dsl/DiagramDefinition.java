package cz.orany.yuml.dsl;

import cz.orany.yuml.*;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

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

    default Type type(String name) {
        return type(name, Closure.IDENTITY);
    }

    Type type(String name, @DelegatesTo(value = Type.class, strategy = Closure.DELEGATE_FIRST) Closure builder);

    default Relationship aggregation(String source, String destination) {
        return aggregation(source, destination, Closure.IDENTITY);
    }

    default Relationship aggregation(
        String source,
        String destination,
        @DelegatesTo(value = Relationship.class, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    ) {
        return relationship(source, RelationshipType.AGGREGATION, destination, additionalProperties);
    }

    default Relationship composition(String source, String destination) {
        return composition(source, destination, Closure.IDENTITY);
    }

    default Relationship composition(
        String source,
        String destination,
        @DelegatesTo(value = Relationship.class, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    ) {
        return relationship(source, RelationshipType.COMPOSITION, destination, additionalProperties);
    }

    default Relationship inheritance(
        String source,
        String destination
    ) {
        return inheritance(source, destination, Closure.IDENTITY);
    }

    default Relationship inheritance(
        String source,
        String destination,
        @DelegatesTo(value = Relationship.class, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    ) {
        return relationship(source, RelationshipType.INHERITANCE, destination, additionalProperties);
    }

    default Relationship association(String source, String destination) {
        return association(source, destination, Closure.IDENTITY);
    }

    default Relationship association(
        String source,
        String destination,
        @DelegatesTo(value = Relationship.class, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    ) {
        return relationship(source, RelationshipType.ASSOCIATION, destination, additionalProperties);
    }

    default Relationship relationship(String source, RelationshipType relationshipType, String destination) {
        return relationship(source, relationshipType, destination, Closure.IDENTITY);
    }

    Relationship relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = Relationship.class, strategy = Closure.DELEGATE_FIRST) Closure additionalProperties
    );

}
