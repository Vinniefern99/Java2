package foothill;

public class Test
{
    public static void main(String[] args)
    {
        Automaton auto1 = new Automaton(30);
        
        System.out.println();
        //System.out.println(auto1.toStringCurrentGen());
       
        
        for (int i = 0 ; i < 100 ; i++)
        {
            System.out.println(auto1.toStringCurrentGen());
            auto1.propagateNewGeneration();
            
        }


    }

}
