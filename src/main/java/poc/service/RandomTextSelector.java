package poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.cache.TextCache;

import java.util.Random;

/**
 */
@Service
public class RandomTextSelector {

    private final TextCache textCache;
    private final Random random;

    @Autowired
    public RandomTextSelector(TextCache textCache) {
        this.textCache = textCache;
         random = new Random();
    }

    public String getRandomText(){
        return textCache.get(random.nextInt(textCache.getTextCount()));
    }
}
