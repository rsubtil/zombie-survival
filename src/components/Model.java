package components;

import com.simsilica.es.EntityComponent;

public class Model implements EntityComponent {
    
    private final String model;
    
    public Model(String model) {
        this.model = model;
    }
    
    public String getModel() {
        return model;
    }
}
