
package com.jeremp.service;

import io.indico.Indico;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.results.IndicoResult;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author jeremp
 */
@Service
public class IndicoClient {
    
    private static final Logger LOG = LoggerFactory.getLogger(IndicoClient.class);
    
    @Value("${jpn.api.key}")
    private String apiKey ;
    
    private Indico indico  ;
    
    @PostConstruct
    private void init(){
        LOG.info("Initialisation d'un client Indico, apiKey={}", apiKey);
        indico = new Indico(apiKey);        
    }
    
    public List<Double> analyseSentiment(List<String> text) throws Exception{
        LOG.info("predict {}", ToStringBuilder.reflectionToString(text, ToStringStyle.JSON_STYLE));
        BatchIndicoResult result = indico.sentimentHQ.predict(text.toArray(new String[text.size()]));
        LOG.info("res={}", ToStringBuilder.reflectionToString(result, ToStringStyle.JSON_STYLE));
        return result.getSentimentHQ();
        
    }
    
    
    
    
}
