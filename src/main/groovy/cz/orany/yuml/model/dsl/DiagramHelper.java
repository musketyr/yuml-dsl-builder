package cz.orany.yuml.model.dsl;

import java.util.Collections;
import java.util.Map;

public interface DiagramHelper extends DiagramContentDefinition {

    default Map<String, Object> getMetadata() {
        return Collections.emptyMap();
    }

}
