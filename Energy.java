
/**
 * Escreva uma descrição da classe Energy aqui.
 * 
  * @author Caio Sanchez
 * @version 04/03/2023
 */
public class Energy
{
    private String energyStatus;
    private int energyPoints;

    /**
     * Construtor para objetos da classe Energy
     */
    public Energy(int energyPoints, String energyStatus){
        
        this.energyPoints = energyPoints;
        this.energyStatus = energyStatus;
    }
    
    public String getEnergyStatus(){
        
        return energyStatus;
    }
     
    public int getEnergyPoints(){
        
        return energyPoints;
    }
}
