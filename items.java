
/**
 * Escreva uma descrição da classe items aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Items
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int weight;
    private String descriptionItem;

    /**
     * Construtor para objetos da classe items
     */
    public Items(int weight, String descriptionItem)
    {
        // inicializa variáveis de instância
        this.weight = weight;
        this.descriptionItem = descriptionItem;
    }

    public String getDescription(){
        
        return descriptionItem;
    }
    
}
