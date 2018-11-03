package cz.orany.yuml

import cz.orany.yuml.export.DiagramPrinter
import cz.orany.yuml.export.YumlDiagramPrinter
import cz.orany.yuml.model.Diagram
import cz.orany.yuml.model.dsl.DiagramContentDefinition
import cz.orany.yuml.model.dsl.DiagramDefinition
import groovy.transform.CompileStatic
import spock.lang.Specification
import spock.lang.Unroll

class DiagramSpec extends Specification {

    private static final String EXPECTED_DIAGRAM = '''
        [note: You can stick notes on diagrams too!{bg:skyblue}]
        [Customer]<>1-orders 0..*>[Order]
        [Order]++*-*>[LineItem]
        [Order]-1>[DeliveryMethod]
        [Order]*-*>[Product]
        [Category]<->[Product]
        [DeliveryMethod]^[National]
        [DeliveryMethod]^[International]
    '''.stripIndent().trim()

    private static final String EXPECTED_DIAGRAM_DIAGRAM = '''
        [note: YUML Diagram Components]
        [Type]<>1..*->[Diagram]
        [Note]<>0..*->[Diagram]
        [Relationship]<>0..*->[Diagram]
        [Type]<>source 1->[Relationship]
        [Type]<>destination 1->[Relationship]
        [RelationshipType]++1->[Relationship]
    '''.stripIndent().trim()

    @Unroll
    void 'create #title diagram'() {
        given:
            DiagramPrinter printer = new YumlDiagramPrinter()
        expect:
            printer.print(diagram).trim() == expected

            diagram.relationships*.source.every { it in diagram.types }
            diagram.relationships*.destination.every { it in diagram.types }
        where:
            title                           | diagram                                 | expected
            'orders'                        | buildOrderDiagram()                     | EXPECTED_DIAGRAM
            'literal diagram'               | buildDiagramDiagramLiteral()            | EXPECTED_DIAGRAM_DIAGRAM
            'literal diagram'               | buildDiagramDiagramGrouped()            | EXPECTED_DIAGRAM_DIAGRAM
            'diagram with methods'          | buildDiagramDiagramUsingHelperMethods() | EXPECTED_DIAGRAM_DIAGRAM
            'diagram with internal methods' | buildDiagramWithInternalMethodCalls()   | EXPECTED_DIAGRAM_DIAGRAM
    }

    @CompileStatic
    private static Diagram buildOrderDiagram() {
        Diagram.build {
            note('You can stick notes on diagrams too!', 'skyblue')

            aggregation('Customer', 'Order') {
                source '1'
                destination '0..*', 'orders'
            }

            composition('Order', 'LineItem') {
                source '*'
                destination '*'
            }

            association('Order', 'DeliveryMethod') {
                destination '1'
            }

            association('Order', 'Product') {
                source '*'
                destination '*'
            }

            association('Category', 'Product') {
                bidirectional true
            }

            type 'National' inherits from type 'DeliveryMethod'
            type'International' inherits from type 'DeliveryMethod'
        }
    }

    @CompileStatic
    private static Diagram buildDiagramDiagramLiteral() {
        Diagram.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram' has one to many type 'Type'
            type 'Diagram' has zero to many type 'Note'
            type 'Diagram' has zero to many type 'Relationship'

            type 'Relationship' has one type 'Type' called 'source'
            type 'Relationship' has one type 'Type' called 'destination'
            type 'Relationship' owns one type 'RelationshipType'
        }
    }

    @CompileStatic
    private static Diagram buildDiagramDiagramGrouped() {
        Diagram.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram', {
                has one to many type 'Type'
                has zero to many type 'Note'
                has zero to many type 'Relationship'
            }

            type 'Relationship', {
                has one type 'Type' called 'source'
                has one type 'Type' called 'destination'
                owns one type 'RelationshipType'
            }
        }
    }

    @CompileStatic
    private static Diagram buildDiagramDiagramUsingHelperMethods() {
        Diagram.build { DiagramDefinition diagram ->
            note 'YUML Diagram Components'

            buildDiagramRelationships(diagram)
            buildRelationshipRelationship(diagram)
        }
    }

    @CompileStatic
    private static DiagramContentDefinition buildDiagramRelationships(DiagramDefinition diagram) {
        diagram.with {
            type 'Diagram' has one to many type 'Type'
            type 'Diagram' has zero to many type 'Note'
            type 'Diagram' has zero to many type 'Relationship'
        }
    }

    @CompileStatic
    private static DiagramContentDefinition buildRelationshipRelationship(DiagramDefinition diagram) {
        diagram.with {
            type 'Relationship' has one type 'Type' called 'source'
            type 'Relationship' has one type 'Type' called 'destination'
            type 'Relationship' owns one type 'RelationshipType'
        }
    }

    @CompileStatic
    private static Diagram buildDiagramWithInternalMethodCalls() {
        Diagram.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram', {
                has one to many type 'Type'
                has zero to many type notes
                has zero to many type 'Relationship'
            }

            type 'Relationship', {
                has one type 'Type' called 'source'
                has one type 'Type' called 'destination'
                owns one type 'RelationshipType'
            }
        }
    }

    private static String getNotes() {
        return 'Note'
    }
}
