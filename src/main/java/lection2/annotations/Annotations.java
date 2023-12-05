package lection2.annotations;

import java.lang.reflect.Field;

public class Annotations {
    public static void main(String[] args) throws NoSuchFieldException {
        MyClass myClass = new MyClass();

        System.out.println(myClass.getNegative());
        System.out.println(myClass.getPositive());

        RandomIntegerProcessor.processObject(myClass);

        System.out.println(myClass.getNegative());
        System.out.println(myClass.getPositive());

//        Field negativeField = MyClass.class.getDeclaredField("negative");
//        RandomInteger annotation = negativeField.getAnnotation(RandomInteger.class);
//        System.out.println(annotation.annotationType().getSimpleName());
//
//        System.out.println(annotation.minValue());
//        System.out.println(annotation.maxValue());
    }

    static class MyClass {
        @RandomInteger(minValue =  -15, maxValue = -1)
        private int negative;

        @RandomInteger(minValue =  1, maxValue = 20)
        private int positive;

        public int getNegative() {
            return negative;
        }

        public int getPositive() {
            return positive;
        }
    }
}
