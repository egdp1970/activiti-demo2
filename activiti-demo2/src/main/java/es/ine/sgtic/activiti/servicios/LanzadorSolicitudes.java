package es.ine.sgtic.activiti.servicios;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ine.sgtic.negocio.SolicitudBeca;

public class LanzadorSolicitudes {

	private static final Logger logger = LoggerFactory.getLogger(LanzadorSolicitudes.class);
	
	public static void main(String[] args) {

		//Instanciamos el motor de workflow desde activity.cfg.xml
		final ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		//Accedemos al repositorio de servicios
		final RepositoryService repositoryService = processEngine.getRepositoryService();

		//Añadimos nuestro flujo de gestión de becas
		repositoryService.createDeployment().addClasspathResource("solicitud-beca.bpmn").deploy();

		//Cargamos las variables de la solicitud de beca
		final SolicitudBeca solicitud = new SolicitudBeca();
		solicitud.setNif("51928571E");
		solicitud.setNombre("Eduardo");
		solicitud.setApe1("García");
		solicitud.setApe2("de Pedro");
		solicitud.setImporte(500);
		solicitud.setEmail("egdp1970@gmail.com");
		final Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("solicitud", solicitud);
		
		//Iniciamos una nueva instancia del flujo de gestión de becas con los parámetros de la solicitud
		final RuntimeService runtimeService = processEngine.getRuntimeService();
		final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("tramite-solicitud-beca", variableMap);

		//Obtenemos el id del flujo de tratamiento de la solicitud y el id del flujo de gestión de becas
		logger.info("Lanzado el flujo  id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
	}

}
