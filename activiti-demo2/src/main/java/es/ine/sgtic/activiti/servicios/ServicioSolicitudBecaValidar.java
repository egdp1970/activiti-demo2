package es.ine.sgtic.activiti.servicios;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ine.sgtic.negocio.SolicitudBeca;

public class ServicioSolicitudBecaValidar implements JavaDelegate {

	private final Logger logger = LoggerFactory.getLogger(ServicioSolicitudBecaValidar.class);

	public void execute(DelegateExecution arg0) throws Exception {
		
		logger.info("Validamos la solicitud de beca");
		final SolicitudBeca solicitud = (SolicitudBeca)arg0.getVariables().get("solicitud");
		if (validaSolicitud(solicitud)){
			arg0.setVariable("validada", "1");
		} else {
			arg0.setVariable("validada", "0");
		}
	}

	private boolean validaSolicitud(final SolicitudBeca solicitud){
		if (StringUtils.isNotEmpty(solicitud.getEmail()) && solicitud.getImporte()<1000){
			return true;
		} else {
			return false;
		}
	}
}
