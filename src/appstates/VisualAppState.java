package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Spatial;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import components.Model;
import components.Transform;
import java.util.HashMap;

public class VisualAppState extends AbstractAppState {
    
    private SimpleApplication app;
    private EntityData ed;
    private EntitySet entities;
    private final HashMap<EntityId, Spatial> models;
    private ModelLoader modelLoader;
    
    public VisualAppState() {
        this.models = new HashMap<EntityId, Spatial>();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        this.app = (SimpleApplication)app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();
        this.entities = this.ed.getEntities(Transform.class, Model.class);
        
        
    }
    
    @Override
    public void update(float tpf) {
        
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
    }
}
