package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.terrain.Terrain;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import components.Model;
import components.Transform;

public class GameplayAppState extends AbstractAppState{
    
    private SimpleApplication app;
    private AssetManager assetManager;
    private Node rootNode;
    private Camera cam;
    private FlyByCamera flyCam;
    
@Override
    public void initialize(AppStateManager stateManager, Application app) {
        // Sets local variables
        this.app = (SimpleApplication) app;
        this.assetManager = this.app.getAssetManager();
        this.rootNode = this.app.getRootNode();
        this.cam = this.app.getCamera();
        this.flyCam = this.app.getFlyByCamera();
        
        // Loads the terrain
        Spatial terrain = assetManager.loadModel("Scenes/Terrain/terrain.j3o");
        //rootNode.attachChild(terrain);
        
        // Sets the camera's position and velocity
        //cam.setLocation(new Vector3f(0, 100, 0));
        flyCam.setMoveSpeed(32);
        
        // Adds lights
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -10, -5));
        sun.setColor(ColorRGBA.White);
        
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.Gray);
        
        rootNode.addLight(sun);
        rootNode.addLight(ambient);
        
        // Gets the Terrain object
        Node temp = (Node)terrain;
        Terrain ground = (Terrain)temp.getChild("Terrain");
        
        // Gets the entityData
        EntityData ed = stateManager.getState(EntityDataState.class).getEntityData();
        
        for(int i = 0; i < 1000; i++) {
            int x = FastMath.nextRandomInt(-64, 64);
            int z = FastMath.nextRandomInt(-64, 64);
            //float y = ground.getHeight(new Vector2f(x, z));
            
            EntityId id = ed.createEntity();
            ed.setComponents(id,
                        new Transform(new Vector3f(x, 0, z)),
                        new Model(Model.P_TallGrass1));
        }
        
        // Continues to initialize
        super.initialize(stateManager, app);
    }

    @Override
    public void update(float tpf) {
        
    }

    @Override
    public void cleanup() {
        
        
        // Continues to cleanup
        super.cleanup();
    }
}
