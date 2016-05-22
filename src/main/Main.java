package main;

import appstates.BillboardAppState;
import appstates.EntityDataState;
import appstates.GameplayAppState;
import appstates.VisualAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;

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
                               // FIXME: This feature is still not totally funcional
                               //        and is causing weird behaviour. Don't uncomment
                               /*new BillboardAppState()*/
                               new EntityDataState());
        
        // DEBUG: Helps ti find transparency problems
        viewPort.setBackgroundColor(ColorRGBA.Cyan);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        /*for(Spatial s : rootNode.getChildren()) {
            s.lookAt(cam.getLocation(), Vector3f.UNIT_Y);
            s.setLocalRotation(s.getLocalRotation());
        }*/
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
