
/**
 * Escreva uma descrição da classe items aqui.
 * 
 * @author Caio Sanchez
 * @version 04/03/2023
 */
public class Items
{
    //Capital I
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int weightItem;
    private String descriptionItem;

    /**
     * Construtor para objetos da classe items
     */
    public Items(int weight, String descriptionItem)
    {
        // inicializa variáveis de instância
        this.weightItem = weight;
        this.descriptionItem = descriptionItem;
    }

    public String getDescription(){
        
        return descriptionItem;
    }
     
    public int getWeight(){
        
        return weightItem;
    }
}
