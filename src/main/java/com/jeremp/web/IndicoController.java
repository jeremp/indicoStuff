/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremp.web;

import com.jeremp.service.IndicoClient;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jeremp
 */
@RestController
@RequestMapping("/indico")
public class IndicoController {
    
     
    @Autowired
    private IndicoClient indicoClient ;
    
    
    @RequestMapping("/sentiments")
    public List<Double> sentiments(@RequestParam(value="text") String texts) throws Exception {        
        List<Double> analyseSentiment = indicoClient.analyseSentiment(Arrays.asList(texts.split("\\|")));
        return analyseSentiment;
    }
    
}
