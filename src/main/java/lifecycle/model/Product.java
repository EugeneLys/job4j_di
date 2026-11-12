package lifecycle.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Product implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;
    private double price;
    private String beanName;
    private BeanFactory beanFactory;

    public Product(String name, double price) {
        System.out.printf("1. Called constructor %s%n", getClass().getSimpleName());
        this.name = name;
        this.price = price;
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
        System.out.printf("2. This bean was registered in cont"
                + ""
                + "ext with name '%s'%n", name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        boolean result = this.beanFactory.containsBean(beanName);
        System.out.printf("2. Installing BeanFactory to bean '%s', result of installation: '%s'%n", beanName, result);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.printf("2. Executing actions setApplicationContext of bean '%s'%n", beanName);
    }

    @PostConstruct
    public void annotInitMethod() {
        System.out.printf("4. @PostConstruct executing actions while initialisation of bean '%s'%n", beanName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("4. InitializingBean: executing actions while initialisation of bean '%s'%n", beanName);
    }

    public void initMethod() {
        System.out.printf("4. @Bean, свойство initMethod: executing actions while initialisation of bean '%s'%n", beanName);
    }

    @PreDestroy
    public void oneMethodDestroy() {
        System.out.printf("6. @PreDestroy: executing actions while elimination of bean '%s'%n", beanName);
    }

    @Override
    public void destroy() throws Exception {
        System.out.printf("6. DisposableBean: executing actions while elimination of bean '%s'%n", beanName);
    }

    public void destroyMethod() {
        System.out.printf("6. @Bean, свойство destroyMethod: - executing actions while elimination of bean '%s'%n", beanName);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return String.format("%s: name=%s, price=$%.2f", getClass().getSimpleName(), name, price);
    }
}