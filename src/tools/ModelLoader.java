package tools;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class ModelLoader {
    
    private final AssetManager assetManager;
    
    public ModelLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    public Spatial load(String name) {
        Node visual = new Node("Visual");
        Spatial model = assetManager.loadModel("Models/" + name + ".j3o");
        visual.attachChild(model);
        return visual;
    }
}
