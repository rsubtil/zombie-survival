package components;

import com.simsilica.es.EntityComponent;

public class Billboard implements EntityComponent {
    
    private boolean update;
    
    public Billboard() {
        update = true;
    }
    
    public boolean needsUpdate() {
        return update;
    }
    
    public void update() {
        update = true;
    }
    
    public void updated() {
        update = false;
    }
}
