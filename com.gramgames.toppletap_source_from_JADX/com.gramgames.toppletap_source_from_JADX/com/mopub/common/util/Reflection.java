package com.mopub.common.util;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection {

    public static class MethodBuilder {
        private Class<?> mClass;
        private final Object mInstance;
        private boolean mIsAccessible;
        private boolean mIsStatic;
        private final String mMethodName;
        private List<Class<?>> mParameterClasses;
        private List<Object> mParameters;

        public MethodBuilder(Object instance, String methodName) {
            this.mInstance = instance;
            this.mMethodName = methodName;
            this.mParameterClasses = new ArrayList();
            this.mParameters = new ArrayList();
            this.mClass = instance != null ? instance.getClass() : null;
        }

        public <T> MethodBuilder addParam(Class<T> clazz, T parameter) {
            this.mParameterClasses.add(clazz);
            this.mParameters.add(parameter);
            return this;
        }

        public MethodBuilder setAccessible() {
            this.mIsAccessible = true;
            return this;
        }

        public MethodBuilder setStatic(Class<?> clazz) {
            this.mIsStatic = true;
            this.mClass = clazz;
            return this;
        }

        public Object execute() throws Exception {
            Method method = Reflection.getDeclaredMethodWithTraversal(this.mClass, this.mMethodName, (Class[]) this.mParameterClasses.toArray(new Class[this.mParameterClasses.size()]));
            if (this.mIsAccessible) {
                method.setAccessible(true);
            }
            Object[] parameters = this.mParameters.toArray();
            if (this.mIsStatic) {
                return method.invoke(null, parameters);
            }
            return method.invoke(this.mInstance, parameters);
        }
    }

    public static Method getDeclaredMethodWithTraversal(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                return currentClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

    public static boolean classFound(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static <T> T instantiateClassWithEmptyConstructor(@NonNull String className, @NonNull Class<? extends T> superclass) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NullPointerException {
        Preconditions.checkNotNull(className);
        Constructor<? extends T> constructor = Class.forName(className).asSubclass(superclass).getDeclaredConstructor((Class[]) null);
        constructor.setAccessible(true);
        return constructor.newInstance(new Object[0]);
    }
}
