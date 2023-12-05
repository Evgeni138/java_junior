package lection2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchFieldException {
        Person person = new Person("Eugene");

        Class<Person> personClass = Person.class;

        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);

        constructor.setAccessible(true);

        Person personViaReflection = constructor.newInstance("Eugene");
        System.out.println(personViaReflection);

        Class<? super Head> superclass = Head.class.getSuperclass();
        Constructor<? super Head> declaredConstructor = superclass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("abc");
        System.out.println(o);

        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(o, 42);
        System.out.println(o);

        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(o, "Eugene");
        System.out.println(o);

        Method toStringMethod = personClass.getMethod("toString");
        Object teStringResult = toStringMethod.invoke(o);
        System.out.println(teStringResult);

        Person person1 = Person.randomPerson();
        System.out.println(person1);

        Method randomPersonMethod = personClass.getMethod("randomPerson");
        Object randomPerson = randomPersonMethod.invoke(null);
        System.out.println(randomPerson);
        System.out.println(randomPerson.getClass().getSimpleName());

    }


}
