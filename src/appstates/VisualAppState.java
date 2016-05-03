package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import components.Model;
import components.Transform;
import java.util.HashMap;
import java.util.Set;
import tools.ModelLoader;

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
        
        this.modelLoader = new ModelLoader(this.app.getAssetManager());
    }
    
    @Override
    public void update(float tpf) {
        if(entities.applyChanges()) {
            
        }
    }
    
    private void removeModels(Set<Entity> entities) {
        for(Entity e : entities) {
            Spatial s = models.remove(e);
            s.removeFromParent();
        }
    }
    
    private void addModels(Set<Entity> entities) {
        for(Entity e : entities) {
            Spatial s = createVisual(e);
            models.put(e.getId(), s);
            updateModelSpatial(e, s);
            this.app.getRootNode().attachChild(s);
        }
    }
    
    private void updateModels(Set<Entity> entities) {
        for(Entity e : entities) {
            Spatial s = models.get(e.getId());
            updateModelSpatial(e, s);
        }
    }
    
    private void updateModelSpatial(Entity e, Spatial s) {
        Transform t = e.get(Transform.class);
        s.setLocalTranslation(t.getPosition());
        s.setLocalRotation(t.getRotation());
        s.setLocalScale(t.getScale());
    }
    
    private Spatial createVisual(Entity e) {
        Model m = e.get(Model.class);
        return modelLoader.load(m.getName());
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
    }
}
