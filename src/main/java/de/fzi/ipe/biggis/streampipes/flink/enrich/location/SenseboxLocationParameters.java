package de.fzi.ipe.biggis.streampipes.flink.enrich.location;

import de.fzi.cep.sepa.model.impl.graph.SepaInvocation;
import de.fzi.cep.sepa.runtime.param.BindingParameters;

/**
 * Created by Jochen Lutz on 2017-08-02.
 */
public class SenseboxLocationParameters extends BindingParameters {
    //TODO: brauch ich hier was? Wenn ja muss das auch in SenseboxLocationController.declareModel() eingepflegt werden
    public SenseboxLocationParameters(SepaInvocation graph) {
        super(graph);
    }
}
