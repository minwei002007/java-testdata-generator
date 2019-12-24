package cn.binarywang.tools.generator;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import static com.google.inject.name.Names.named;

/**
 * 类说明
 * <p>
 *
 * @author ： minwei
 * @version ：1.0.0
 * @date ：2019-12-24
 */
public class TestConfigExtendModule extends AbstractModule {

    @Override
    protected void configure() {
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(ClassLoader
                .getSystemResourceAsStream("test-config-template.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
        Enumeration<Object> e = p.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = (String) p.get(key);
            bindConstant().annotatedWith(named("config." + key))
                .to(value);
        }
    }


}
