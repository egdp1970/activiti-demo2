package es.ine.sgtic.activiti.servicios;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ine.sgtic.negocio.SolicitudBeca;

public class ServicioSolicitudBecaRechazada implements JavaDelegate {

	private final Logger logger = LoggerFactory.getLogger(ServicioSolicitudBecaRechazada.class);
	
	public void execute(DelegateExecution arg0) throws Exception {
		
		logger.info("Procesando una solicitud de beca rechazada");
		final SolicitudBeca solicitud = (SolicitudBeca)arg0.getVariables().get("solicitud");
		arg0.setVariable("contestacion", String.format("Estimado %s.\n\n Su solicitud de beca por %s ha sido rechazada", solicitud.getNombre(), solicitud.getImporte()));
		arg0.setVariable("mailto",solicitud.getEmail());
	}
}
