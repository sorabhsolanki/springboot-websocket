package poc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.cache.TextCache;
import poc.util.PropertyFileReader;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 */
@Service
public class LoadOnStartup {

    private static final Logger LOG = LoggerFactory.getLogger(LoadOnStartup.class);

    private final PropertyFileReader propertyFileReader;
    private final TextCache textCache;

    @Autowired
    public LoadOnStartup(PropertyFileReader propertyFileReader, TextCache textCache) {
        this.propertyFileReader = propertyFileReader;
        this.textCache = textCache;
    }

    @PostConstruct
    public void init() {
        reloadCache();
    }

    private void reloadCache() {
        LOG.info("Initializing in-memory cache for speech.");
        try {
            Properties prop = PropertyFileReader.readPropertyFile();
            StringTokenizer stringTokenizerText = new StringTokenizer(prop.getProperty("text"), "|");
            int count = -1;

            while (stringTokenizerText.hasMoreTokens()){
                textCache.insert( ++count, stringTokenizerText.nextToken().trim());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in reading property file : " + e.getMessage());
        }

    }
}
