/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blcr_project_test;

import com.PauWare.PauWare_view.AbstractElement;
import com.PauWare.PauWare_view.StateChart;
import com.PauWare.PauWare_view.Transition;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.io.PrintStream;

/**
 *
 * @author josuah
 */
public class MyStateChart_test
{
    public static void main(String[] args)
    {
        try
        {
            PauWare_component pc = new PauWare_component();
            pc.start();
            
            StateChart myStateChart = new StateChart(pc.getStateMachine(), "Parse Test", 800, 600);
            
            Collection<AbstractElement> elements = myStateChart.elements();
            Collection<Transition> transitions = myStateChart.transitions();
            HashMap<Integer, HashSet<AbstractElement> > nestingLevels = myStateChart.nestingLevels();
            PrintStream out = System.err;
            
            Iterator<AbstractElement> it_elem = elements.iterator();
            AbstractElement elem;
            out.println("États");
            while(it_elem.hasNext())
            {
                elem = it_elem.next();
                
                out.println(elem.name());
            }

            Iterator<Transition> it_trans = transitions.iterator();
            Transition trans;
            out.println("\nTransitions");
            while(it_trans.hasNext())
            {
                trans = it_trans.next();
                
                out.println(trans.origin().name()+"-->"+trans.target().name());
            }

            Set<Integer> keys = nestingLevels.keySet();
            Iterator<Integer> it_keys = keys.iterator();
            HashSet<AbstractElement> elems;
            Integer level;
            out.print("\nNeting Levels");
            while(it_keys.hasNext())
            {
                level = it_keys.next();
                elems = nestingLevels.get(level);
                it_elem = elems.iterator();
                
                out.println("\n\tLevel "+String.valueOf(level)+":");
                
                while(it_elem.hasNext())
                {
                    elem = it_elem.next();
                    out.print("\t\t"+elem.name()+", ");
                }
            }
        }
        
        catch (Statechart_exception t)
        {
            t.printStackTrace();
        }
    }
}
