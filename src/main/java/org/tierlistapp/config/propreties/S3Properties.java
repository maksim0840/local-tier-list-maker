package org.tierlistapp.config.propreties;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Автоматическое распределение значений из application.yaml в поля java класса
// Требует включить конфигурацию на класс в Main.java (@EnableConfigurationProperties)
@ConfigurationProperties(prefix = "s3")
public record S3Properties(
        String accessKey,
        String secretKey,
        String endpointUrl,
        String bucketName,
        Boolean pathStyleAccess,
        String regionName
) {}
