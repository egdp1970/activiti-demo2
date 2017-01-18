package es.ine.sgtic.activiti.servicios;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirmadorSolicitudes {

	private static final Logger logger = LoggerFactory.getLogger(FirmadorSolicitudes.class);

	public static void main(String[] args) {

		//Instanciamos el motor de workflow desde activity.cfg.xml
		final ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		try {

			//Cargamos las solicitudes pendientes del presidente
			final TaskService taskService = processEngine.getTaskService();
			final List<Task> tareasPresidente = taskService.createTaskQuery().taskAssignee("presidente").list();

			//Nos quedamos con las tareas del 'presidente' del tipo 'firmar-solicitud-aprobada'
			//porque aquí sólo trataríamos una de esas tareas
			final int numTareasPresidente = tareasPresidente.size();
			if (numTareasPresidente > 0) {
				final String idTarea = tareasPresidente.get(0).getId();
				final String nombreTarea = tareasPresidente.get(0).getName();
				if ("firmar-solicitud-aprobada".equals(tareasPresidente.get(0).getName())){
					logger.info(String.format("Encontrada la tarea %s de %s",idTarea,nombreTarea));
					taskService.claim(tareasPresidente.get(0).getId(), "presidente");
					logger.info(String.format("Asignada la tarea %s de %s a %s: ",idTarea,nombreTarea, "presidente"));
					Thread.sleep(3000);
					taskService.complete(tareasPresidente.get(0).getId());
					logger.info(String.format("Terminada la tarea %s de %s asignada a %s: ",idTarea,nombreTarea, "presidente"));
				} else {
					logger.info(String.format("Encontrada una tarea %s del presidente pero es de tipo %s", idTarea, nombreTarea));
				}
			} else {
				logger.info("No encontrada ninguna tarea del presidente.");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {

		}
		processEngine.close();

	}
}
