package cz.orany.yuml

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Diagram {

    Collection<Note> notes = new LinkedHashSet<>()
    Collection<Type> types = new LinkedHashSet<>()
    Collection<Relationship> relationships = new LinkedHashSet<>()

}
