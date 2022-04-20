package Practice.MultiThreading;

public class SingletonThreadSafe {
    private static volatile SingletonThreadSafe instance;
    private static Object mutex = new Object();

    private SingletonThreadSafe() {
    }

    public static SingletonThreadSafe getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new SingletonThreadSafe();
                }
            }
        }

        return instance;
    }
}
