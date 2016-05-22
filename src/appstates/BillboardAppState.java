package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;
import components.Billboard;
import components.Transform;

public class BillboardAppState extends AbstractAppState {
    
    private SimpleApplication app;
    private EntityData ed;
    private EntitySet entities;
    private InputManager inputManager;
    
@Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        this.app = (SimpleApplication)app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();
        this.entities = this.ed.getEntities(Transform.class, Billboard.class);
        
        this.inputManager = this.app.getInputManager();
        this.inputManager.addMapping("Update", new KeyTrigger(KeyInput.KEY_W),
                                               new KeyTrigger(KeyInput.KEY_S),
                                               new KeyTrigger(KeyInput.KEY_A),
                                               new KeyTrigger(KeyInput.KEY_D),
                                               new KeyTrigger(KeyInput.KEY_Q),
                                               new KeyTrigger(KeyInput.KEY_Z));
        this.inputManager.addListener(analogListener, "Update");
    }

    @Override
    public void update(float tpf) {
        entities.applyChanges();
        for(Entity e : entities) {
            if(e.get(Billboard.class).needsUpdate()) {
                e.get(Billboard.class).updated();
                Transform t = e.get(Transform.class);
                t.getRotation().lookAt(app.getCamera().getLocation(), Vector3f.UNIT_Y);
                Transform newT = new Transform(t.getPosition(), t.getRotation(), t.getScale());
                e.set(newT);
            }
        }
    }
    
    private AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float intensity, float tpf) {
            if(name.equals("Update")) {
                for(Entity e : entities) {
                    e.get(Billboard.class).update();                   
                }
            }
        }
    };

    @Override
    public void cleanup() {
        super.cleanup();
    }
}
