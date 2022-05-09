package bstorm.akimts.gateway.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties("app")
public class ServiceProperties implements InitializingBean {

    private Map<String, Service> services;

    public Map<String, Service> getServices() {
        return services;
    }

    public void setServices(Map<String, Service> services) {
        this.services = services;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "ServiceProperties{ services=" + services + '}';
    }

    public static class Service {

        private String url;
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Service() {
        }

        public Service(String url, String name) {
            this.url = url;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Service{" + "url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}
