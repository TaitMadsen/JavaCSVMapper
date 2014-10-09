import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Mapper<T> {
    private Class<T> clazz;
    private Map<Class<?>, Class<?>> wrapperMap;

    public Mapper(Class<T> clazz) {
        this.clazz = clazz;

        wrapperMap = new HashMap<Class<?>, Class<?>>();
        wrapperMap.put(boolean.class, Boolean.class);
        wrapperMap.put(byte.class, Byte.class);
        wrapperMap.put(short.class, Short.class);
        wrapperMap.put(char.class, Character.class);
        wrapperMap.put(int.class, Integer.class);
        wrapperMap.put(long.class, Long.class);
        wrapperMap.put(float.class, Float.class);
        wrapperMap.put(double.class, Double.class);

    }

    public List<T> map(BufferedReader br, String delimiter) throws IOException {
        ArrayList<T> result = new ArrayList<T>();
        String line = br.readLine();
        if (line != null) {
            List<String> headerList = createHeaderListFromLine(line, delimiter);

            while ((line = br.readLine()) != null) {
                result.add(createObjectFromLine(line, delimiter, headerList));
            }
        }
        return result;
    }

    private List<String> createHeaderListFromLine(String line, String delimiter) {
        return Arrays.asList(line.split(delimiter));
    }

    private T createObjectFromLine(String line, String delimiter, List<String> headerList) {
        List<String> fieldList = Arrays.asList(line.split(delimiter));
        if (headerList.size() != fieldList.size()) {
            throw new RuntimeException("The number of elements in line: "+fieldList.toString()+
                                               " does not match the number of elements in the header: "
                                               + headerList.toString());
        }
        HashMap<String, String> headerToFieldMap = new HashMap<String, String>();
        for (int i = 0; i < headerList.size(); i++) {
            headerToFieldMap.put(headerList.get(i).trim(), fieldList.get(i).trim());
        }

        T instance;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        callAllAnnotatedSetters(headerToFieldMap, instance);
        return instance;
    }

    private void callAllAnnotatedSetters(HashMap<String, String> headerToFieldMap, T instance) {
        for (Class<?> iteratedClass = clazz; iteratedClass != Object.class; iteratedClass = iteratedClass
                .getSuperclass()) {
            List<Method> allMethods = Arrays.asList(iteratedClass.getDeclaredMethods());
            for (Method method : allMethods) {
                if (method.isAnnotationPresent(CSVMapperSetterMethod.class)) {

                    CSVMapperSetterMethod annotation = method.getAnnotation(CSVMapperSetterMethod.class);
                    if (headerToFieldMap.keySet().contains(annotation.fieldName())) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {

                            Class<?> parameterType = parameterTypes[0];
                            callSetter(headerToFieldMap, instance, method, annotation, parameterType);
                        } else {
                            throw new RuntimeException(
                                    "The annotation @CSVMapperSetterMethod can only be applied to methods with " +
                                            "exactly one argument");
                        }
                    }
                }
            }
        }
    }

    private void callSetter(HashMap<String, String> headerToFieldMap, T instance, Method method,
                            CSVMapperSetterMethod annotation, Class<?> parameterType) {
        Class<?> wrapperType;

        if (parameterType.isPrimitive()) {
            wrapperType = wrapperMap.get(parameterType);
        } else {
            wrapperType = parameterType;
        }

        try {
            Object fieldValue = headerToFieldMap.get(annotation.fieldName());
            Object fieldValueAsCorrectType;

            fieldValueAsCorrectType = getFieldValueAsCorrectType(wrapperType, fieldValue);
            method.invoke(instance, fieldValueAsCorrectType);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getFieldValueAsCorrectType(Class<?> wrapperType,
                                              Object fieldValue) throws IllegalAccessException,
            InvocationTargetException {
        Object fieldValueAsCorrectType;
        if (wrapperType == String.class) {
            fieldValueAsCorrectType = fieldValue;
        } else {
            Method conversionMethod;
            try {
                conversionMethod = wrapperType.getDeclaredMethod("valueOf", String.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(
                        "Methods annotated with @CSVMapperSetterMethod must take as argument " +
                                "a String or an object with 'valueOf(String s)' method", e);
            }

            fieldValueAsCorrectType = conversionMethod.invoke(null, fieldValue);

        }
        return fieldValueAsCorrectType;
    }

}
