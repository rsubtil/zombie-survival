package models;

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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelLoader {
    
    private final AssetManager assetManager;
    private Logger logger;
    
    public static enum Models {
        I_Gauze("items.Gauze"),
        I_HealthKit("items.HealthKit"),
        I_Pills("items.Pills"),
        
        A_Bird1("animals.Bird1"),
        
        P_Tree1("props.Tree1"),
        P_TallGrass("props.TallGrass"),
        P_Chair1("props.Chair1");
        
        public final String name;
        
        private Models(String name) {
            this.name = name;
        }
    }
    
    public ModelLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.logger = LoggerFactory.getLogger(ModelLoader.class);
    }
    
    public Spatial load(String name) {
        Node model;
        logger.debug("Created model node");
        
        try {
            logger.debug("Try \"models." + name);
            Class<?> modelClass = Class.forName("models." + name + "Factory");
            logger.debug("Try to get create method");
            Method createMethod = modelClass.getDeclaredMethod("create", AssetManager.class);
            logger.debug("Try to call create method");
            model = (Node)createMethod.invoke(modelClass, assetManager);
        } catch(ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.debug("Model \"" + name + "\" doesn't have custom factory. Using default loading");
            model = (Node)assetManager.loadModel("Models/" + name.replace(".", "/") + ".j3o");
        }
        
        model.setName(name);
        
        return model;
    }
}