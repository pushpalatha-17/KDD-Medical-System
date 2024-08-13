/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medical;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author seabirds
 */
public class Graph1 extends ApplicationFrame 
{
    ArrayList list1=new ArrayList();
    Graph1(String tit,ArrayList at)
    {
        super(tit);
        list1=at;
        setContentPane(createDemoPanel());
    }
    public PieDataset createDataset() 
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        for(int i=0;i<list1.size();i++)
        {
            String g1[]=list1.get(i).toString().split("#");
            dataset.setValue(g1[0], new Double(g1[1]));
        }
       
        return dataset;        
    }
    
    public JFreeChart createChart(PieDataset dataset) 
    {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Differential Diagnosis",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
    
   
    public  JPanel createDemoPanel() 
    {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}
