package blcr_project_test;

import com.PauWare.PauWare_view.AbstractElement;
import com.PauWare.PauWare_view.JungLayoutTest;
import com.PauWare.PauWare_view.StateChart;
import com.PauWare.PauWare_view.Transition;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PauWare_component_test {

    public static void main(String[] args)
    {
        //testPauWareStateChart();
        testMyStateChart();
        
//        JungLayoutTest jungLayoutTest = new JungLayoutTest();
//        jungLayoutTest.runTest();
    }

    public static void testPauWareStateChart()
    {
        try {
            PauWare_component pc = new PauWare_component();
            pc.start();
            while (true) {
                pc.go();
                Thread.sleep(500);
                pc.request_b();
                Thread.sleep(500);
                pc.request_c();
                Thread.sleep(500);
                pc.request_d();
                Thread.sleep(500);
                pc.request_e();
                Thread.sleep(500);
                pc.request_f();
                Thread.sleep(500);
                pc.request_g();
                Thread.sleep(500);
                pc.request_h();
                Thread.sleep(500);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }        
    }
    
    public static void testMyStateChart()
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