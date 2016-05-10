package components;

import com.simsilica.es.EntityComponent;

public class Model implements EntityComponent {
    
    private String name;
    
    public static final String I_Gauze = "I_Gauze";
    public static final String I_HealthKit = "I_HealthKit";
    public static final String I_Pills = "I_Pills";
    
    public static final String P_Tree1 = "P_Tree1";
    public static final String P_TallGrass1 = "P_TallGrass1";
    
    public Model(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
