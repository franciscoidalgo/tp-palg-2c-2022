package pablosz.app;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import pablosz.app.domain.Session;
import pablosz.app.persistance.PersistentObject;
import pablosz.app.persistance.Persistidor;


// +---------------------------------------------------------+
// | NOTA: Si queremos organizar los componentes y entidades | 
// | dentro de diferentes paquetes tendremos que especificar |
// | explicitamente cada uno de estos descomenando las       |
// | siguientes lineas: @ComponentScan y @EntityScan         |
// +---------------------------------------------------------+
@ComponentScan
@EntityScan
@SpringBootApplication
public class Application implements CommandLineRunner
{
    private static Logger LOG = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class,args);
	}
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private Persistidor p;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception
	{
		LOG.info("Todo funciona correctamente? "+(em!=null));
		//p.createSession(2, 1);
	}
}
