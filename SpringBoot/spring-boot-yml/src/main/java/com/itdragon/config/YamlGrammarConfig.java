package com.itdragon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * yaml 语法配置类
 */
@Configuration
@PropertySource(value={"classpath:yamlgrammar.yml"})
public class YamlGrammarConfig {
}
