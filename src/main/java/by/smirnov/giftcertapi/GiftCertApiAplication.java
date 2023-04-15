package by.smirnov.giftcertapi;


import by.smirnov.giftcertapi.configuration.ConnectionPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "by.smirnov.giftcertapi")
@Import({ConnectionPoolConfig.class})
public class GiftCertApiAplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftCertApiAplication.class, args);
    }


}
