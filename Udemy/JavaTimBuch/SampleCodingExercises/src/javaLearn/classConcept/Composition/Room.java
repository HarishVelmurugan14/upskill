package javaLearn.classConcept.Composition;


//Composition : Instead of using base inheritance ; many base classes can be assigned to a primary class

public class Room {
    public static void main(String []args){
        Windows window =new Windows(2,new Dimension(23,34,6));
            Electricals electricals = new Electricals(2,2);
            Chairs chairs = new Chairs(new Dimension(32,34,3));
            Furniture furniture = new Furniture(chairs,5);
    }
}
