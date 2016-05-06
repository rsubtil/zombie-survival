package main;

import appstates.EntityDataState;
import appstates.GameplayAppState;
import appstates.VisualAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import components.Model;
import components.Transform;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Initializes the appstates
        stateManager.attachAll(new GameplayAppState(), 
                               new EntityDataState(),
                               new VisualAppState());
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
