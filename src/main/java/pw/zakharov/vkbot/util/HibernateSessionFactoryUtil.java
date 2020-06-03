package pw.zakharov.vkbot.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.persistance.Secret;
import pw.zakharov.vkbot.persistance.User;

/**
 * @author Alexey Zakharov
 * @date 03.06.2020
 */
public final class HibernateSessionFactoryUtil {

    private static final Logger log = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Secret.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                log.error("Error when getting session factory. StackTrace: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

}
