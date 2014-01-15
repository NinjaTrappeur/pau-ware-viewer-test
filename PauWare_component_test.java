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

public class PauWare_component_test
{
    private static PrintStream _out = System.err;


    public static void main(String[] args)
    {
        //testPauWareStateChart();
        testMyStateChart();
//        try
//        {
//            testPauwareDraw();
//        }
//        catch(Statechart_exception e)
//        {
//            e.printStackTrace();
//        }
        
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
            
            Iterator<AbstractElement> it_elem = elements.iterator();
            AbstractElement elem;
            _out.println("États");
            while(it_elem.hasNext())
            {
                elem = it_elem.next();
                
                _out.println(
                        elem.name()+
                        "\n\tConteneur: "+elem.container().name()+
                        "\n\tShallowContentSize: "+ elem.shallowContentSize()+
                        "\n\tDeepContentSize: "+ elem.deepContentSize()
                );
            }

            Iterator<Transition> it_trans = transitions.iterator();
            Transition trans;
            _out.println("\nTransitions");
            while(it_trans.hasNext())
            {
                trans = it_trans.next();
                
                _out.println(trans.origin().name()+"-->"+trans.target().name());
            }

            Set<Integer> keys = nestingLevels.keySet();
            Iterator<Integer> it_keys = keys.iterator();
            HashSet<AbstractElement> elems;
            Integer level;
            _out.print("\nNeting Levels");
            while(it_keys.hasNext())
            {
                level = it_keys.next();
                elems = nestingLevels.get(level);
                it_elem = elems.iterator();
                
                _out.println("\n\tLevel "+String.valueOf(level)+":");
                
                while(it_elem.hasNext())
                {
                    elem = it_elem.next();
                    _out.print("\t\t"+elem.name()+", ");
                }
            }
        }
        
        catch (Statechart_exception t)
        {
            t.printStackTrace();
        }        
    }
    
    public static void testPauwareDraw() throws Statechart_exception
    {
        com.pauware.pauware_engine._Core.AbstractStatechart state1, state2;
        com.pauware.pauware_engine._Core.AbstractStatechart_monitor monitor;
        com.PauWare.PauWare_view.Statechart_monitor_viewer viewer;

        //Création d'un PauwareStateChart
        state1 = new com.pauware.pauware_engine._Java_EE.Statechart("premier");
        state1.inputState();
        state2 = new com.pauware.pauware_engine._Java_EE.Statechart("deuxieme");
        state2.outputState();
        
        //Création d'un moniteur et d'un viewer (utilise Processing)
        monitor = new com.pauware.pauware_engine._Java_EE.Statechart_monitor(state1.xor(state2), "PauWare component", com.pauware.pauware_engine._Core.AbstractStatechart_monitor.Don_t_show_on_system_out);
        viewer = new com.PauWare.PauWare_view.Statechart_monitor_viewer();

        //Démarrage
        monitor.add_listener(viewer);
        monitor.initialize_listener();
        monitor.start();
    }
}