package by.smirnov.giftcertapi.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Import({DatabaseProperties.class})
@RequiredArgsConstructor
public class ConnectionPoolConfig {

    private final DatabaseProperties databaseConfig;
    private final Environment env;

    @Bean
    public DataSource hikariDatasource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(databaseConfig.getUrl());
        hikariDataSource.setUsername(databaseConfig.getLogin());
        hikariDataSource.setPassword(databaseConfig.getPassword());
        hikariDataSource.setDriverClassName(databaseConfig.getDriverName());
        hikariDataSource.setMaximumPoolSize(10);

        return hikariDataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setPackagesToScan("by.smirnov.giftcertapi.domain");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(getAdditionalProperties());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.default_schema", "sensorsdb");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }
}
