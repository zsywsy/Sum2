package mzs.libtools.utils;

import java.util.LinkedHashMap;

public class TempRepo {
    private static TempRepo instance;

    private LinkedHashMap<String, Object> mMap = new LinkedHashMap<>();

    private TempRepo() {
    }

    public static TempRepo getInstance() {
        if (instance == null) {
            synchronized (TempRepo.class) {
                if (instance == null) {
                    instance = new TempRepo();
                }
            }
        }
        return instance;
    }

    public Object put(String key, Object o) {
        return mMap.put(key, o);
    }

    public Object get(String key) {
        Object value = null;
        if (mMap.containsKey(key)) {
            value = mMap.get(key);
        }
        mMap.remove(key);
        return value;
    }

    public void clear() {
        mMap.clear();
    }

    public void log() {
        System.out.println("size:" + mMap.size());
    }

    @Override
    public String toString() {
        return mMap.toString();
    }
}
