package tools;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.control.BillboardControl.Alignment;
import com.jme3.texture.Texture;

public class ModelLoader {
    
    private final AssetManager assetManager;
    
    public static enum Models {
        I_Gauze("I_Gauze", "I_Gauze", 1, true, false, false),
        I_HealthKit("I_HealthKit", "I_HealthKit", 1, false, false, false),
        I_Pills("I_Pills", "I_Pills", 3, false, false, false),
        
        E_Bird1("E_Bird1", "E_Bird", 1, false, true, false),
        
        P_Tree1("P_Tree1", "P_Tree", 5, false, false, false),
        P_TallGrass("P_TallGrass", "P_TallGrass", 1, true, false, true),
        P_Chair1("P_Chair1", "P_Chair", 1, false, false, false);
        
        private final String modelName;
        private final String materialName;
        private final int materialVariations;
        private final boolean alpha;
        private final boolean hasAnimation;
        private final boolean billboard;
        
        private Models(String modelName, String materialName, int materialVariations, boolean alpha, boolean hasAnimation, boolean billboard) {
            this.modelName = modelName;
            this.materialName = materialName;
            this.materialVariations = materialVariations;
            this.alpha = alpha;
            this.hasAnimation = hasAnimation;
            this.billboard = billboard;
        }
    }
    
    public ModelLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    public Spatial load(Models model) {
        // Creates a Node and loads the model
        Node visual = new Node("Visual");
        Spatial spatial = assetManager.loadModel("Models/" + model.modelName + ".j3o");
        // If models is null, throw an error
        if(spatial == null) {
            throw new NullPointerException("Model is null! Did you made a typo on the enum or are you hacking the modelName?\nModel name: " + model.modelName);
        }
        // Gets the material for setting "blocky" rendering and texture variation
        Material mat = getMaterial(spatial);
        if(mat != null) {
            // Randomly create a variation based on the texture's variation
            int matVariation = FastMath.nextRandomInt(1, model.materialVariations);
            // Needs to be a TextureKey because I need to flip the texture
            TextureKey texKey = new TextureKey("Textures/" + (model.materialName + matVariation) + ".png", true);
            Texture tex = assetManager.loadTexture(texKey);
            if(tex != null) {
                // Sets the texture to be rendered in a "blocky" way
                tex.setMagFilter(Texture.MagFilter.Nearest);
            } else {
                // Texture is null, throws an error
                throw new NullPointerException("Texture is null! Did you made a typo on the enum or are you hacking the materialName/materialVariation?\nMaterial name: " + model.materialName + "\nMaterial variations: " + model.materialVariations);
            }
            // Sets the texture
            mat.setTexture("DiffuseMap", tex);
        } else {
            // If material is null, the model the user is trying to load is not visual (Node)
            throw new NullPointerException("Material couldn't be found! Are you sure the model has a visual representation?\nModel name: " + model.modelName);
        }
        // Sets alpha if enabled
        if(model.alpha) {
            spatial.setQueueBucket(Bucket.Transparent);
        } else {
            spatial.setQueueBucket(Bucket.Opaque);
        }
        
        // Adds BillboardControl for billboard objects
        if(model.billboard) {
            BillboardControl bc = new BillboardControl();
            bc.setAlignment(Alignment.AxialY);
            spatial.addControl(bc);
        }
        
        // Attaches the model
        visual.attachChild(spatial);
        
        return visual;
    }
    
    public Material getMaterial(Spatial spat) {
        if(spat instanceof Node) {
            Node n = (Node)spat;
            Material mat;
            for(Spatial s : n.getChildren()) {
                mat = getMaterial(s);
                return mat;
            }
        } else if(spat instanceof Geometry) {
            Geometry g = (Geometry)spat;
            return g.getMaterial();
        }
        return null;
    }
}