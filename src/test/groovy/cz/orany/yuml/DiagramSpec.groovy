package cz.orany.yuml

import spock.lang.Specification

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

    void 'create orders diagram'() {
        given:
            Diagram diagram =  new Diagram().with {
                note('You can stick notes on diagrams too!','skyblue')

                relationship(type('Customer'), RelationshipType.AGGREGATION, type('Order')).with {
                    sourceCardinality = '1'
                    destinationTitle = 'orders'
                    destinationCardinality = '0..*'
                }

                relationship(type('Order'), RelationshipType.COMPOSITION, type('LineItem')).with {
                    sourceCardinality = '*'
                    destinationCardinality = '*'
                }

                relationship(type('Order'), type('DeliveryMethod')).with {
                    destinationCardinality = '1'
                }

                relationship(type('Order'), type('Product')).with {
                    sourceCardinality = '*'
                    destinationCardinality ='*'
                }

                relationship(type('Category'), type('Product')).with {
                    bidirectional = true
                }

                relationship(type('National'), RelationshipType.INHERITANCE, type('DeliveryMethod'))
                relationship(type('International'), RelationshipType.INHERITANCE, type('DeliveryMethod'))

                it
            }

        expect:
            diagram.toString().trim() == EXPECTED_DIAGRAM

            diagram.relationships*.source.every { it in diagram.types }
            diagram.relationships*.destination.every { it in diagram.types }
    }


}
