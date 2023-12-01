package pl.patrykv220.grupowetpcore.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ReflectionUtil
{
    public static String getVersion() {
        final String name = Bukkit.getServer().getClass().getPackage().getName();
        final String version = String.valueOf(name.substring(name.lastIndexOf(46) + 1)) + ".";
        return version;
    }
    
    public static Class<?> getClass(final String name) {
        Class<?> c = null;
        try {
            c = Class.forName("net.minecraft.server." + getVersion() + name);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static int getVersion(final Player p) {
        final Class<?> c = getBukkitEntityClass("CraftPlayer");
        final Object cp = c.cast(p);
        try {
            final Object handle = c.getMethod("getHandle", (Class<?>[])new Class[0]).invoke(cp, new Object[0]);
            final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            final Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
            final Method m = networkManager.getClass().getMethod("getVersion", (Class<?>[])new Class[0]);
            final int version = (int)m.invoke(networkManager, new Object[0]);
            return version;
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException | NoSuchMethodException ex2) {
            final Exception ex = null;
            final Exception e = ex;
            e.printStackTrace();
            return 0;
        }
    }
    
    public static Object getHandle(final Entity entity) {
        try {
            return getMethod(entity.getClass(), "getHandle").invoke(entity, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object getHandle(final World world) {
        try {
            return getMethod(world.getClass(), "getHandle").invoke(world, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Method getMethod(final Class<?> cl, final String method, final Class<?>... args) {
        Method[] methods;
        for (int length = (methods = cl.getMethods()).length, i = 0; i < length; ++i) {
            final Method m = methods[i];
            if (m.getName().equals(method) && classListEqual(args, m.getParameterTypes())) {
                return m;
            }
        }
        return null;
    }
    
    public static Method getMethod(final Class<?> cl, final String method) {
        Method[] methods;
        for (int length = (methods = cl.getMethods()).length, i = 0; i < length; ++i) {
            final Method m = methods[i];
            if (m.getName().equals(method)) {
                return m;
            }
        }
        return null;
    }
    
    public static boolean classListEqual(final Class<?>[] l1, final Class<?>[] l2) {
        boolean equal = true;
        if (l1.length != l2.length) {
            return false;
        }
        for (int i = 0; i < l1.length; ++i) {
            if (l1[i] != l2[i]) {
                equal = false;
                break;
            }
        }
        return equal;
    }
    
    public static void sendPacket(final Object packet, final Player p) {
        try {
            final Method getHandle = p.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            final Object entityPlayer = getHandle.invoke(p, new Object[0]);
            final Field f = entityPlayer.getClass().getField("playerConnection");
            final Object playerConnection = f.get(entityPlayer);
            final Method sendPacket = playerConnection.getClass().getMethod("sendPacket", getClass("Packet"));
            sendPacket.invoke(playerConnection, packet);
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex2) {
            final Exception ex = null;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    public static void sendPackets(final Object[] packets, final Player p) {
        try {
            final Method getHandle = p.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            final Object entityPlayer = getHandle.invoke(p, new Object[0]);
            final Field f = entityPlayer.getClass().getField("playerConnection");
            final Object playerConnection = f.get(entityPlayer);
            final Method sendPacket = playerConnection.getClass().getMethod("sendPacket", getClass("Packet"));
            for (final Object packet : packets) {
                if (packet != null) {
                    sendPacket.invoke(playerConnection, packet);
                }
            }
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex2) {
            final Exception ex = null;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    public static Class<?> getBukkitEntityClass(final String name) {
        Class<?> c = null;
        try {
            c = Class.forName("org.bukkit.craftbukkit." + getVersion() + "entity." + name);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static <T> FieldAccessor<T> getSimpleField(final Class<?> target, final String name) {
        return (FieldAccessor<T>)getField(target, name);
    }
    
    public static <T> FieldAccessor<T> getField(final Class<?> target, final String name, final Class<T> fieldType) {
        return getField(target, name, fieldType, 0);
    }
    
    public static <T> FieldAccessor<T> getField(final String className, final String name, final Class<T> fieldType) {
        return getField(getClass(className), name, fieldType, 0);
    }
    
    public static <T> FieldAccessor<T> getField(final Class<?> target, final Class<T> fieldType, final int index) {
        return getField(target, null, fieldType, index);
    }
    
    public static <T> FieldAccessor<T> getField(final String className, final Class<T> fieldType, final int index) {
        return getField(getClass(className), fieldType, index);
    }
    public static <T> FieldAccessor<T> getFielD(Class<?> target, Class<T> fieldType, int index) {
        return ReflectionUtil.getField(target, null, fieldType, index);
    }
    
    private static <T> FieldAccessor<T> getField(final Class<?> target, final String name, final Class<T> fieldType, int index) {
        Field[] declaredFields;
        for (int length = (declaredFields = target.getDeclaredFields()).length, i = 0; i < length; ++i) {
            final Field field = declaredFields[i];
            if ((name == null || field.getName().equals(name)) && fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
                field.setAccessible(true);
                return new FieldAccessor<T>() {
                    @Override
                    public T get(final Object target) {
                        try {
                            return (T)field.get(target);
                        }
                        catch (IllegalAccessException e) {
                            throw new RuntimeException("Cannot access reflection.", e);
                        }
                    }
                    
                    @Override
                    public void set(final Object target, final Object value) {
                        try {
                            field.set(target, value);
                        }
                        catch (IllegalAccessException e) {
                            throw new RuntimeException("Cannot access reflection.", e);
                        }
                    }
                    
                    @Override
                    public boolean hasField(final Object target) {
                        return field.getDeclaringClass().isAssignableFrom(target.getClass());
                    }
                };
            }
        }
        if (target.getSuperclass() != null) {
            return (FieldAccessor<T>)getField(target.getSuperclass(), name, (Class<Object>)fieldType, index);
        }
        throw new IllegalArgumentException("Cannot find field with type " + fieldType);
    }
    
    public static <T> FieldAccessor<T> getField(final Class<?> target, final String name) {
        Field[] declaredFields;
        for (int length = (declaredFields = target.getDeclaredFields()).length, i = 0; i < length; ++i) {
            final Field field = declaredFields[i];
            if (name == null || field.getName().equals(name)) {
                field.setAccessible(true);
                return new FieldAccessor<T>() {
                    @Override
                    public T get(final Object target) {
                        try {
                            return (T)field.get(target);
                        }
                        catch (IllegalAccessException e) {
                            throw new RuntimeException("Cannot access reflection.", e);
                        }
                    }
                    
                    @Override
                    public void set(final Object target, final Object value) {
                        try {
                            field.set(target, value);
                        }
                        catch (IllegalAccessException e) {
                            throw new RuntimeException("Cannot access reflection.", e);
                        }
                    }
                    
                    @Override
                    public boolean hasField(final Object target) {
                        return field.getDeclaringClass().isAssignableFrom(target.getClass());
                    }
                };
            }
        }
        if (target.getSuperclass() != null) {
            return (FieldAccessor<T>)getField(target.getSuperclass(), name);
        }
        throw new IllegalArgumentException("Cannot find field with type");
    }
    
    public interface FieldAccessor<T>
    {
        T get(final Object p0);
        
        void set(final Object p0, final Object p1);
        
        boolean hasField(final Object p0);
    }
}
