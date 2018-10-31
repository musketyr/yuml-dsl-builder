package cz.orany.yuml

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope

@CompileStatic
@EqualsAndHashCode
class Note {

    final String text
    final String color

    @PackageScope Note(String text, String color = null) {
        this.text = text
        this.color = color
    }

    @Override
    String toString() {
        assert text

        if (color) {
            return "[note: ${text}{bg:$color}]"
        }

        return "[note: $text]"
    }
}
