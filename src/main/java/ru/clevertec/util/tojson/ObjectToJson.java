package ru.clevertec.util.tojson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import static java.lang.System.out;

public class ObjectToJson {

    public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(indent(indentSize))
                .append("{")
                .append("\n");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }

            stringBuilder
                    .append(indent(indentSize + 1))
                    .append(formatStringValue(field.getName()))
                    .append(":");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(field.get(instance), field.getType()));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(arrayToJson(field.get(instance), indentSize + 1));
            } else {
                stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
            }
            if (i != fields.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append(indent(indentSize));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String formatArrayValue(Object arrayObject) {
        int arrayLength = Array.getLength(arrayObject);

        out.print("[");

        for (int i = 0; i < arrayLength; i++) {
            Object element = Array.get(arrayObject, i);
            if (element.getClass().isArray()) {
                formatArrayValue(element);
            } else {
                out.print(element);
            }

            if (i != arrayLength - 1) {
                out.print(",");
            }
        }
        out.print("]");
        return null;
    }

    private static String arrayToJson(Object arrayObject, int indentSize) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        int arrayLength = Array.getLength(arrayObject);

        Class<?> componentType = arrayObject.getClass().getComponentType();

        stringBuilder
                .append("[")
                .append("\n");

        for (int i = 0; i < arrayLength; i++) {
            Object element = Array.get(arrayObject, i);
            if (componentType.isPrimitive()) {
                stringBuilder
                        .append(indent(indentSize + 1))
                        .append(formatPrimitiveValue(element, componentType));
            } else if (element.getClass().isArray()) {
                formatArrayValue(element);
            } else if (componentType.equals(String.class)) {
                stringBuilder
                        .append(indent(indentSize + 1))
                        .append(formatStringValue(element.toString()));
            }

            if (i != arrayLength - 1) {
                stringBuilder.append(",");
            }

            stringBuilder.append("\n");
        }

        stringBuilder
                .append(indent(indentSize))
                .append("]");

        return stringBuilder.toString();
    }

    private static String indent(int indentSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indentSize; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }

    private static String formatPrimitiveValue(Object object, Class<?> type) throws IllegalAccessException {
        if (type.equals(boolean.class)
                || type.equals(int.class)
                || type.equals(long.class)
                || type.equals(short.class)) {
            return object.toString();
        } else if (type.equals(double.class) || type.equals(float.class))
            return String.format("%.02f", object);
        throw new RuntimeException(String.format("Type : %s is unsupported", type.getName()));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }
}
