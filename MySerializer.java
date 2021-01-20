package com;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;

public class MySerializer {
    public static String serialize(Object o) throws IllegalAccessException {
        StringBuilder serializedString = new StringBuilder();
        Class<?> cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Save.class)){
                if (Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                serializedString.append(field.getName()).append("=").append(field.get(o)).append("\n");
            }
        }
        try(FileWriter writer = new FileWriter("file.txt", false)){
            writer.write(serializedString.toString());
            System.out.println("Fields with annotation \"@Save\" are successfully saved to \"file.txt\" with their values");
        }catch (IOException e){
            e.printStackTrace();
        }
        return serializedString.toString();
    }
    public static <T> T deserialize(String string, Class<?> cls) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T result;
        result = (T)cls.newInstance();
        String[] namesAndValues = string.split("\n");
        for (String s : namesAndValues){
            String[] nameAndValueItem = s.split("=");
            if(nameAndValueItem.length != 2){
                throw new InvalidParameterException(string);
            }
            Field currentField = cls.getDeclaredField(nameAndValueItem[0]);
            if (Modifier.isPrivate(currentField.getModifiers())){
                currentField.setAccessible(true);
            }
            //if(currentField.isAnnotationPresent(Save.class)){
                if (currentField.getType() == int.class){
                    currentField.set(result, Integer.parseInt(nameAndValueItem[1]));
                } else if (currentField.getType() == String.class){
                    currentField.set(result, nameAndValueItem[1]);
                } else if (currentField.getType() == boolean.class){
                    currentField.set(result, Boolean.parseBoolean(nameAndValueItem[1]));
                } else if (currentField.getType() == long.class){
                    currentField.set(result, Long.parseLong(nameAndValueItem[1]));
                } else if (currentField.getType() == double.class){
                    currentField.set(result, Double.parseDouble(nameAndValueItem[1]));
                } else {
                    throw new IllegalArgumentException("Undefined type : " + currentField.getType().toString());
                }
            //}
        }
        return result;
    }
}
