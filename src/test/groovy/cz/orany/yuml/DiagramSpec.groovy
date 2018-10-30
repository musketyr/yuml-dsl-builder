package cz.orany.yuml

import spock.lang.PendingFeature
import spock.lang.Specification

class DiagramSpec extends Specification {


//    [note: You can stick notes on diagrams too!{bg:skyblue}]
//    [Customer]<>1-orders 0..*>[Order]
//    [Order]++*-*>[LineItem]
//    [Order]-1>[DeliveryMethod]
//    [Order]*-*>[Product]
//    [Category]<->[Product]
//    [DeliveryMethod]^[National]
//    [DeliveryMethod]^[International]

    @PendingFeature
    void 'create orders digaram'() {
        given:
            Diagram diagram =  new Diagram()

            diagram.notes.add(new Note(
                text: 'You can stick notes on diagrams too!',
                color: 'skyblue'
            ))

            Type customer = new Type(name: 'Customer')
            Type order = new Type(name: 'Order')
            Type lineItem = new Type(name: 'LineItem')
            Type deliveryMethod = new Type(name: 'DeliveryMethod')
            Type product = new Type(name: 'Product')
            Type category = new Type(name: 'Category')
            Type nationalDeliveryMethod = new Type(name: 'National')
            Type internationalDeliveryMethod = new Type(name: 'International')

            diagram.types.add(customer)
            diagram.types.add(order)
            diagram.types.add(lineItem)
            diagram.types.add(deliveryMethod)
            diagram.types.add(product)
            diagram.types.add(category)
            diagram.types.add(nationalDeliveryMethod)
            diagram.types.add(internationalDeliveryMethod)

            diagram.relationships.add(new Relationship(

            ))
        expect:
            true == false
    }


}
