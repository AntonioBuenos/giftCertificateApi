package by.smirnov.giftcertapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "by.smirnov.giftcertapi")
public class GiftCertApiAplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftCertApiAplication.class, args);
    }


}
