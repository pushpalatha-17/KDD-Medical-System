/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medical;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 *
 * @author seabirds
 */
public class DifferentialDiagnosis 
{
    MainFrame mf;
    String symptoms[][];
    String disease[][][][];
    String Probs[][][];
    String Result[][];
    String output[][];
    String Final[][];
    DifferentialDiagnosis(MainFrame me)
    {
        mf=me;
        ArrayList sym=mf.sympt;
        ArrayList dis1=mf.disList1;
        Probs=new String[dis1.size()][6][2];
        Result=new String[dis1.size()][3];
        output=new String[dis1.size()][2];
        Final=new String[dis1.size()][2];
    }
    
    public void getSymProb()
    {
        try
        {
            
            ArrayList sym=mf.sympt;
            ArrayList dis1=mf.disList1;
            
            symptoms=new String[sym.size()][2];
                        
            
            for(int i=0;i<sym.size();i++)
            {
                int k=0;
                for(int j=0;j<dis1.size();j++)
                {
                    if(dis1.get(j).toString().contains(sym.get(i).toString()))
                        k++;
                        
                }
                double p1=(double)k/(double)dis1.size();
                System.out.println(sym.get(i).toString()+" : "+k+ " : "+((double)k/(double)dis1.size()));
                symptoms[i][0]=sym.get(i).toString();
                symptoms[i][1]=String.valueOf(p1);
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void getDisease()
    {
        try
        {
            ArrayList sym=mf.sympt;
            ArrayList dis1=mf.disList1;
            disease = new String[dis1.size()][2][6][2];
            //Probs= new String[dis1.size()][6][2];
             
            for(int i=0;i<dis1.size();i++)
            {
                String g1[]=dis1.get(i).toString().split("@");
                disease[i][0][0][0]=g1[0];
                String g2[]=g1[1].split("#");
                for(int j=0;j<g2.length;j++)
                {
                    disease[i][1][j][0]=g2[j];    
                }
            }
            
            /*for(int m=0;m<disease.length;m++)
            {	
                System.out.println("The disease"+m+" stored is "+disease[m][0][0][0]);
                for(int k=0;k<6;k++)
		{
                    if(disease[m][1][k][0]!=null)
                    {	
                        System.out.println("The input values are "+disease[m][1][k][0]);
                    }
		}
            }*/
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void calcProbs()
    {
        ArrayList sym=mf.sympt;
        ArrayList dis1=mf.disList1;
	int i,j,k,s=0;
	int f=1;
	int flag=0;
	try
	{
            /*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String mycon2="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=details.mdb";
            Connection	con1=DriverManager.getConnection(mycon2);
            con1.setAutoCommit(true);
            Statement stmt1 = con1.createStatement();*/
            DBConnection db=new DBConnection();
            Statement stmt1=db.stt;
            stmt1.executeUpdate("delete * from CPT");
           // con1.close();
	}
	catch(Exception els)
	{
		//////System.out.println(els);
	}

	for(j=0;j<dis1.size();j++)
	{
            Probs[j][0][0]=disease[j][0][0][0];
            for(int m=0;m<6;m++)
            {	
		flag=0;
		if(disease[j][1][m][0]!=null)
		{				
                    String symp = disease[j][1][m][0];
                    for(i=0;i<sym.size();i++)
                    {
                        if(symptoms[i][0].equals(symp))
                        {
                            Probs[j][m][1]=symptoms[i][1];
                            Probs[j][m][0] = symptoms[i][0];
                            flag=1;
                            break;	
			}																		
                    }											
		}
		else
		{
                    Probs[j][m][1]="null";
		}
            }//end of for m
            try
            {
		/*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String mycon1="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=details.mdb";
		Connection	con=DriverManager.getConnection(mycon1);
		con.setAutoCommit(true);
		Statement stmt = con.createStatement();*/
                DBConnection db=new DBConnection();
                Statement stmt=db.stt;
                stmt.executeUpdate("insert into CPT values('"+disease[j][0][0][0]+"','"+Probs[j][0][0]+"','"+Probs[j][1][0]+"','"+Probs[j][2][0]+"','"+Probs[j][3][0]+"','"+Probs[j][4][0]+"','"+Probs[j][5][0]+"','CP')");
			
		//con.close();	
            }
            catch(Exception es)
            {
		//System.out.println("Exception in Prior Prob"+es);
            }
            caltimes(disease[j][0][0][0],Probs[j]);
	}//end of for	

    }
    
    void caltimes(String disease,String [][] input)
    {
	String in[][] = new String[6][2];
	for(int i=0;i<6;i++)
	{	
            if(input[i][0]==null)
            {
		in[i][0]=null;
		in[i][1]=null;
            }
            else
            {
		in[i][0]=input[i][0];
		in[i][1]=input[i][1];
            }
	}
	String dis = disease;
	int l=3;
	char combs[] = new char[7];
	String temp;
	int f=0;
	int x=0;
	for( x=0;x<6;x++)
	{
            if(in[x][0]==null)
		break;
            else
		f++;
	}
	//System.out.println("The value of f is "+f);
	double  k = Math.pow(2.0,Double.valueOf(Integer.toString(f)));
	String Prob[]= new String[6];
	Double res=0.0;
	Double res1=0.0;
	Double res2=0.0;
	for(int i=0;i<(int)k;i++)
	{
            combs = calComb(i);
            //System.out.println("The value returned is"+new String(combs));
            char ch;
            int s=6;
            for(int j=f;j>0;j--)
            {
		ch = combs[s];
		if(ch=='0')
		{
                    double doub =(1.0- Double.parseDouble(in[j-1][1]));
                    res = res+doub;
                    Prob[j-1] = String.valueOf(doub);
		}
		else
		{
                    res1 = res1+ Double.parseDouble(in[j-1][1]);
                    Prob[j-1]=in[j-1][1];
		}
		s--;		
            }
            res2 = res1/res;
            //System.out.println("The Probable cp value is "+res2);
            try
            {
		/*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String mycon1="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=details.mdb";
		Connection	con=DriverManager.getConnection(mycon1);
		con.setAutoCommit(true);
		Statement stmt = con.createStatement();*/
                 DBConnection db=new DBConnection();
                Statement stmt=db.stt;
		String str=String.valueOf(res2);
                /*stmt.executeUpdate("insert into CPT values('"+dis+"','"+in[0][0]+"','"+in[1][0]+"','"+in[2][0]+"','"+in[3][0]+"','"+in[4][0]+"','"+in[5][0]+"','"+res2+"')");*/
					
		stmt.executeUpdate("insert into CPT values('"+dis+"','"+Prob[0]+"','"+Prob[1]+"','"+Prob[2]+"','"+Prob[3]+"','"+Prob[4]+"','"+Prob[5]+"','"+res2+"')");
		//con.close();	
            }
            catch(Exception e)
            {
		//////System.out.println(e);
            }	

	}

    } 
    char []  calComb(int  p)
    {
	String s =Integer.toBinaryString((int)p);
        char coun[] = new char[7];
	char coun1[] = new char[7];
	int i;
	if(s.length()<7)
	{
            int l = 7-s.length();
            for( i=0;i<l;i++)
		coun[i]='0';

            coun1 = s.toCharArray();
            for(i=0;i<s.length();i++)
            {
		coun[l] = coun1[i];
		l++;
            }
	}
	return  coun;
		
    } //calComb
    
    public void PriorProb(String in_remote[])
    {
	int i=0,j=0;
	String input1,input2;

	String input[] =in_remote;
	double total1 = 0.0;
	int k;
	double  total=0.0;
        ArrayList dis1=mf.disList1;
	for(i=0;i<dis1.size();i++)
	{
            //System.out.println("COmes ");
            total=0.0;
            Result[i][0]=disease[i][0][0][0];
			
            for(j=0;j<6;j++)
            {
                if(Probs[i][j][1].equals("null"))
		{
		}
		else
		{
                    total = total+Double.parseDouble(Probs[i][j][1]);
                    ////////System.out.println(total);					
		}
		////System.out.println("Coe");
            }
            ////System.out.println("The total vaues ios"+total);	
            Result[i][1]=String.valueOf(total);
	}

	for(i=0;i<dis1.size();i++)
	{
            total1 = 0.0;	
            for(j=0;j<6;j++)
            {
                for(int k_new=0;k_new<input.length;k_new++)		
		{
                    if(input[k_new].equals(Probs[i][j][0]))
                    {
                        total1 = total1+Double.parseDouble(Probs[i][j][1]); 				
			break;
                    }
		}
		//if(input[0].equals(Probs[i][j][0]) || input[1].equals(Probs[i][j][0]) || input[2].equals(Probs[i][j][0]))
		//	total1 = total1+Double.parseDouble(Probs[i][j][1]); 				
            }
            Result[i][2]=String.valueOf(total1);
            //System.out.println("Total --> "+total1);
	}
    }

	
    public String[][] Inference()
    {
        ArrayList dis1=mf.disList1;
	Float f,f1;
	for(int i=0;i<dis1.size();i++)
	{
            Final[i][0]=Result[i][0];
            output[i][0] = Result[i][0];
            Final[i][1]=String.valueOf(Float.parseFloat(Result[i][2])/Float.parseFloat(Result[i][1]));
            f = Float.valueOf(Final[i][1]);
            DecimalFormat df=new DecimalFormat("#.####");
            
            output[i][1]=df.format(f);
            //output[i][1]=f.toString();
	}	
            //output_remote="";
        for(int i=0;i<dis1.size();i++)
        {
            System.out.println(i+" output -->  "+output[i][0]+"\t"+output[i][1]);
		//output_remote=output_remote+output[i][0]+"/"+output[i][1]+"#";
        }
        return output;
    }
        
}
