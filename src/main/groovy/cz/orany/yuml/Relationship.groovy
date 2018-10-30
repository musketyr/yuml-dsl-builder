package cz.orany.yuml

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Relationship {

    String title
    RelationshipType type

    Type source
    String sourceCardinality

    Type destination
    String destinationCardinality

}
