package main;

import appstates.EntityDataState;
import appstates.GameplayAppState;
import appstates.VisualAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
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
                               /*new BillboardAppState(),*/
                               new EntityDataState());
       
        viewPort.setBackgroundColor(ColorRGBA.Cyan);
        
        inputManager.addMapping("+", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("-", new KeyTrigger(KeyInput.KEY_O));
        inputManager.addListener(analogListener, "+", "-");
    }
    
    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float intensity, float tpf) {
            if(name.equals("+")) {
                cam.setFrustumFar(cam.getFrustumFar() + 100*tpf);
            } else if(name.equals("-")) {
                cam.setFrustumFar(cam.getFrustumFar() - 100*tpf);
            }
        }
    };
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
