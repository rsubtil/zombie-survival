package main;

import appstates.EntityDataState;
import appstates.GameplayAppState;
import appstates.VisualAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import tools.ModelLoader;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Initializes the appstates
        stateManager.attachAll(new VisualAppState(),
                               new GameplayAppState(), 
                               new EntityDataState());
        
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        for(Spatial s : rootNode.getChildren()) {
            s.lookAt(cam.getLocation(), Vector3f.UNIT_Y);
            s.lookAt(s.getLocalRotation().mult(Vector3f.UNIT_Y), Vector3f.UNIT_Y);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
