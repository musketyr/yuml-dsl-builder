package cz.orany.yuml

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Method {
    String name
    Type returnType
    Modifier modifiers = Modifier.PUBLIC
    List<Parameter> parameters = new ArrayList<>()
}
