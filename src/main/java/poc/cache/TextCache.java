package poc.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 */
@Component
public class TextCache {

    private final Map<Integer, String> textCacheMap;
    private int textCount = 0;

    public TextCache(){
        textCacheMap = new HashMap<>();
    }

    public void insert(int key, String val){
        textCacheMap.put(key, val);
        ++ textCount;
    }

    public String get(int key){
        return textCacheMap.get(key);
    }

    public int getTextCount() {
        return textCount;
    }
}
