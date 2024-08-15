package com.eshop.config.StripeConfig;

import com.stripe.Stripe;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StripeConfig {

    @PostConstruct
    public void init() {
        Stripe.apiKey = "sk_test_51Pn2KwP8hiwFxYIvB4q62lNyg19IQA0FVMeRKUr7LhtAOOxSoY31onOcBdyDCza9z5kwTCpfcntfNU4FHD7QjEUU00ih5CGh7w";
    }
}
