package cz.orany.yuml.model.impl

import cz.orany.yuml.model.Note
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

@ToString
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultNote implements Note {

    final String text
    final String color

    @PackageScope
    DefaultNote(String text, String color = null) {
        this.text = text
        this.color = color
    }
}
