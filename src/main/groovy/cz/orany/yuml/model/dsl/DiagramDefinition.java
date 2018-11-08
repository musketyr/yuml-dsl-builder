package cz.orany.yuml.model.dsl;

import cz.orany.yuml.model.Note;
import cz.orany.yuml.model.RelationshipType;

import java.util.function.Consumer;

public interface DiagramDefinition {

    default Note note(String text) {
        return note(text, null);
    }

    Note note(String text, String color);

    default TypeDefinition type(String name) {
        return type(name, (type) -> {});
    }

    TypeDefinition type(
        String name,
        Consumer<TypeDefinition> builder
    );

    default RelationshipDefinition aggregation(String source, String destination) {
        return aggregation(source, destination, (r) -> {});
    }

    default RelationshipDefinition aggregation(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.AGGREGATION, destination, additionalProperties);
    }

    default RelationshipDefinition composition(String source, String destination) {
        return composition(source, destination,  (r) -> {});
    }

    default RelationshipDefinition composition(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.COMPOSITION, destination, additionalProperties);
    }

    default RelationshipDefinition inheritance(
        String source,
        String destination
    ) {
        return inheritance(source, destination,  (r) -> {});
    }

    default RelationshipDefinition inheritance(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.INHERITANCE, destination, additionalProperties);
    }

    default RelationshipDefinition association(String source, String destination) {
        return association(source, destination,  (r) -> {});
    }

    default RelationshipDefinition association(
        String source,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    ) {
        return relationship(source, RelationshipType.ASSOCIATION, destination, additionalProperties);
    }

    default RelationshipDefinition relationship(String source, RelationshipType relationshipType, String destination) {
        return relationship(source, relationshipType, destination,  (r) -> {});
    }

    RelationshipDefinition relationship(
        String source,
        RelationshipType relationshipType,
        String destination,
        Consumer<RelationshipDefinition> additionalProperties
    );

    default <H extends DiagramHelper> H add(Class<H> helper) {
        return configure(helper,  (h) -> {});
    }

    <H extends DiagramHelper> H configure(Class<H> helper, Consumer<H> additionalProperties);

    void postprocess();

}
