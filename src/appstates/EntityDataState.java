package appstates;

import com.jme3.app.state.AbstractAppState;
import com.simsilica.es.EntityData;
import com.simsilica.es.base.DefaultEntityData;

public class EntityDataState extends AbstractAppState {
    
    public EntityData ed;
    
    public EntityDataState() {
        this(new DefaultEntityData());
    }
    
    public EntityDataState(EntityData ed) {
        this.ed = ed;
    }
    
    public EntityData getEntityData() {
        return ed;
    }
    
    @Override
    public void cleanup() {
        ed.close();
        ed = null;
        super.cleanup();
    }
}
