package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Note {

    String text
    String color

    @Override
    String toString() {
        assert text

        if (color) {
            return "[note: ${text}{bg:$color}]"
        }

        return "[note: $text]"
    }
}
