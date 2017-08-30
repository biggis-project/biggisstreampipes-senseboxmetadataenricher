package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import org.streampipes.model.impl.graph.SepaInvocation;
import org.streampipes.wrapper.BindingParameters;

/**
 * Created by Jochen Lutz on 2017-08-02.
 */
public class SenseboxLocationParameters extends BindingParameters {
    //TODO: brauch ich hier was? Wenn ja muss das auch in SenseboxLocationController.declareModel() eingepflegt werden
    public SenseboxLocationParameters(SepaInvocation graph) {
        super(graph);
    }
}
