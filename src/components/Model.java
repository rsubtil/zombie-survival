package components;

import com.simsilica.es.EntityComponent;
import tools.ModelLoader;

public class Model implements EntityComponent {
    
    private ModelLoader.Models model;
    
    public Model(ModelLoader.Models model) {
        this.model = model;
    }
    
    public ModelLoader.Models getModel() {
        return model;
    }
}
