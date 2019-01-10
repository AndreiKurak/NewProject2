package examples_test.chapter1_8;

import org.springframework.beans.factory.FactoryBean;

public class SourceFactory implements FactoryBean {

    private BasicDataSource dataSource = new BasicDataSource();

    public BasicDataSource createDataSourceInstance() {
        return dataSource;
    }

    @Override
    public Object getObject() {
        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return BasicDataSource.class;
    }
}
