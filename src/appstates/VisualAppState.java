package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.BatchNode;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import components.Model;
import components.Transform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import models.ModelLoader;

public class VisualAppState extends AbstractAppState {
    
    private SimpleApplication app;
    private EntityData ed;
    private EntitySet entities;
    public final HashMap<EntityId, Spatial> models;
    public final HashMap<String, BatchNode> batchedNodes;
    private ModelLoader modelLoader;
    
    public VisualAppState() {
        this.models = new HashMap<EntityId, Spatial>();
        this.batchedNodes = new HashMap<String, BatchNode>();
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
            removeModels(entities.getRemovedEntities());
            addModels(entities.getAddedEntities());
            updateModels(entities.getChangedEntities());
        }
        for(BatchNode batchNode : batchedNodes.values()) {
            if(batchNode.getUserData("batched").equals(false)) {
                batchNode.setUserData("batched", true);
                batchNode.batch();
            }
        }
    }
    
    private void removeModels(Set<Entity> entities) {
        for(Entity e : entities) {
            Spatial s = models.remove(e.getId());
            BatchNode batchNode = batchedNodes.get(s.getName());
            s.removeFromParent();
            if(batchNode.getChildren().isEmpty()) {
                batchNode.removeFromParent();
                batchedNodes.remove(s.getName());
            }
        }
    }

    private void addModels(Set<Entity> entities) {
        for(Entity e : entities) {
            Spatial s = createVisual(e);
            
            models.put(e.getId(), s);
            updateModelSpatial(e, s);
            //this.app.getRootNode().attachChild(s);
            BatchNode batchNode = batchedNodes.get(s.getName());
            if(batchNode == null) {
                batchNode = new BatchNode();
                batchedNodes.put(s.getName(), batchNode);
            }
            batchNode.setUserData("batched", false);
            batchNode.attachChild(s);
            this.app.getRootNode().attachChild(batchNode);
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
        return modelLoader.load(m.getModel());
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
    }
}
