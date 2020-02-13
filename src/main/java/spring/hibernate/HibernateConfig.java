package spring.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import spring.hibernate.car.Cars;
import spring.hibernate.employee.Employees;
import spring.hibernate.printer.Printers;

import java.net.URI;
import java.util.Properties;

//@Configuration
public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                URI dbUri = new URI("postgres://iwadpmkavjwttt:adeacfa29e243705d30ca548bb7454a05c955929a986eb0eef5dda6f4613898f@ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/d2co2812u94h5d");
                String username = dbUri.getUserInfo().split("iwadpmkavjwttt")[0];
                String password = dbUri.getUserInfo().split("adeacfa29e243705d30ca548bb7454a05c955929a986eb0eef5dda6f4613898f")[1];
                String dbUrl = "jdbc:postgresql://ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/d2co2812u94h5d?sslmode=require";
                System.out.println(dbUrl);
                settings.put(Environment.URL, dbUrl);
                settings.put(Environment.USER, username);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
//                settings.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Employees.class);
//                configuration.addAnnotatedClass(Phones.class);
                configuration.addAnnotatedClass(Cars.class);
                configuration.addAnnotatedClass(Printers.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

//    @Bean
//    public BasicDataSource dataSource() throws URISyntaxException {
////        URI dbUri = new URI(System.getenv("DATABASE_URL"));
////        postgres://<username>:<password>@<host>/<dbname>
//        URI dbUri = new URI(System.getenv(" postgres://iwadpmkavjwttt:adeacfa29e243705d30ca548bb7454a05c955929a986eb0eef5dda6f4613898f@ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/d2co2812u94h5d\n"));
//        String username = dbUri.getUserInfo().split("iwadpmkavjwttt")[0];
//        String password = dbUri.getUserInfo().split("adeacfa29e243705d30ca548bb7454a05c955929a986eb0eef5dda6f4613898f")[1];
////        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
//        String dbUrl = "jdbc:postgresql://ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/dbbrr04ptqlt9v?sslmode=require";
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//
//        return basicDataSource;
//    }
//
//    private static SessionFactory sessionFactory;
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                settings.put(Environment.URL, "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=CONNECTIS2;integratedSecurity=true;");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
//                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
////                settings.put(Environment.HBM2DDL_AUTO, "update");
//                settings.put(Environment.HBM2DDL_AUTO, "create");
//
//                configuration.setProperties(settings);
//                configuration.addAnnotatedClass(Employees.class);
//                configuration.addAnnotatedClass(Cars.class);
//                configuration.addAnnotatedClass(Printers.class);
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
}