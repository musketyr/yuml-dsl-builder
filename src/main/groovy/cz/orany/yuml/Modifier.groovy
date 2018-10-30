package cz.orany.yuml

enum Modifier {
    PUBLIC('+'),
    PACKAGE('~'),
    PROTECTED('#'),
    PRIVATE('-')

    final String notation

    Modifier(String notation) {
        this.notation = notation
    }
}
