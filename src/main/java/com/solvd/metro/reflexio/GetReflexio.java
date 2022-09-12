package com.solvd.metro.reflexio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetReflexio {

    private static final Logger LOGGER = LogManager.getLogger(GetReflexio.class);

    public static void getBadClassHuman(){
        BadClassHuman badClassHuman = new BadClassHuman();
        String name = null;
        int passport;
        LOGGER.info("name = " + name + ", numberOfPassport = " + badClassHuman.getNumberOfPassport());

        try {
            Field field = badClassHuman.getClass().getDeclaredField("firstName");
            field.setAccessible(true);
            name = (String) field.get(badClassHuman);

            Field passportField = badClassHuman.getClass().getDeclaredField("numberOfPassport");
            passportField.setAccessible(true);
            passportField.set(badClassHuman, (int) 25);
        }catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

        LOGGER.info("name = " + name + ", numberOfPassport = " + badClassHuman.getNumberOfPassport());

        try{
            Method sayHelloMethod = badClassHuman.getClass().getDeclaredMethod("sayHello");
            sayHelloMethod.setAccessible(true);
            sayHelloMethod.invoke(badClassHuman);

            Method informationMethod = badClassHuman.getClass().getDeclaredMethod("information", String.class, int.class);
            informationMethod.setAccessible(true);
            informationMethod.invoke(badClassHuman, name, badClassHuman.getNumberOfPassport());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        BadClassHuman nullConstructor = null;
        try {
            Class<?> clazz = Class.forName(BadClassHuman.class.getName());
            nullConstructor = (BadClassHuman) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        BadClassHuman fullConstructor = null;
        try {
            Class<?> clazz = Class.forName(BadClassHuman.class.getName());
            Class[] params = {String.class, int.class};
            fullConstructor = (BadClassHuman) clazz.getConstructor(params).newInstance("Yuriy", 100);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
