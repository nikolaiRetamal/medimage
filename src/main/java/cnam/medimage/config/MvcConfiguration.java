package cnam.medimage.config;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import org.easycassandra.persistence.cassandra.CassandraFactory;
import org.easycassandra.persistence.cassandra.ClusterInformation;
import org.easycassandra.persistence.cassandra.EasyCassandraManager;
import org.easycassandra.persistence.cassandra.spring.CassandraFactoryAnnotation;
import org.easycassandra.persistence.cassandra.spring.SimpleCassandraTemplateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cnam.medimage.bean.Dicom;

@Configuration
@ComponentScan(basePackages="cnam.medimage")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(838860800);
        return multipartResolver;
    }
	
    
    /*public ClusterInformation clusterInformation(){
    	//System.out.println("Je crée ClusterInformation");
    	ClusterInformation clusterInformation = new ClusterInformation();
    	clusterInformation.setKeySpace("medimage");
    	clusterInformation.setPort(9042);
    	ArrayList<String> hosts = new ArrayList<>();
    	hosts.add("localhost");
    	clusterInformation.setHosts(hosts);
    	//System.out.println("Je retourne ClusterInformation");
    	return clusterInformation;
    }
    
     
    public CassandraFactoryAnnotation cassandraFactory(){
    	CassandraFactoryAnnotation cassandraFactory = new CassandraFactoryAnnotation(clusterInformation());
    	ArrayList<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
    	annotatedClasses.add(cnam.medimage.bean.Dicom.class);
    	cassandraFactory.setAnnotatedClasses(annotatedClasses);
    	//System.out.println("keySpace: " + cassandraFactory.getKeySpace());
    	//System.out.println("annotated class: " + cassandraFactory.getPersistence().count(Dicom.class));
    	return cassandraFactory;
    }
    
    @Bean
    public SimpleCassandraTemplateImpl cassandraTemplate(){
    	//System.out.println("Je vais créer cassandraTemplate");
    	CassandraFactoryAnnotation cassandraFactory = cassandraFactory();
    	SimpleCassandraTemplateImpl cassandraTemplate = new SimpleCassandraTemplateImpl(cassandraFactory);
    	//System.out.println("Je retourne cassandraTemplate");
    	return cassandraTemplate;
    }*/
}
