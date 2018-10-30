package cz.orany.yuml

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Property {

    String name
    Type type
    Modifier modifiers = Modifier.PUBLIC

}
