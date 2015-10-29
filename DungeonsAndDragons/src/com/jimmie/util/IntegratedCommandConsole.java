package com.jimmie.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.jimmie.gui.ConsolePanel;

public class IntegratedCommandConsole {

	   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	   ConsolePanel consolePanel = null;

	   public void addPropertyChangeListener(PropertyChangeListener listener)
	   {
	      pcs.addPropertyChangeListener(listener);
	   }

	   public void removePropertyChangeListener(PropertyChangeListener listener)
	   {
	      pcs.removePropertyChangeListener(listener);
	   }

	   public void print(String s)
	   {
	      pcs.firePropertyChange("newText","old",s);   
	   }
	   
	   public ConsolePanel getConsolePanel()
	   {
	      return consolePanel;
	   }
	   public void setConsolePanel(ConsolePanel consolePanel)
	   {
	      this.consolePanel = consolePanel;
	   }
	   
	   public String getYesOrNoInput()
	   {
	      String result = null;
	      String text = null;
	      
	      while (result == null)
	      {
	         text = consolePanel.getTextFieldValue();
	         
	         if (text != null)
	         {
	            if("Y".compareToIgnoreCase(text) == 0)
	            {
	               return "Y";
	            }
	            if("N".compareToIgnoreCase(text) == 0)
	            {
	               return "N";
	            }
	         }
	         try
	         {
	            Thread.sleep(1000);
	         }
	         catch (Exception e)
	         {
	            print("In Here");
	         }
	      }
	      return "N";
	      
	   }
	   
	   public int getValidIntInputInRange(int start, int end)
	   {
	      String result = null;
	      String text = null;
	      int temp = 0;
	      
	      while (result == null)
	      {
	         text = consolePanel.getTextFieldValue();
	         
	         if (text != null)
	         {
	            try
	            {
	               temp = Integer.parseInt(text);
	            }
	            catch(NumberFormatException e)
	            {
	               print("Not a number!  Try again.");
	               continue;
	            }

	            if((temp >= start) && (temp <= end))
	            {
	               return (temp);
	            }
	            else
	            {
	               print("Out of range.  Please try again.");
	            }
	            
	         }
	         try
	         {
	            Thread.sleep(1000);
	         }
	         catch (Exception e)
	         {
	            print("In Here");
	         }
	      }
	      return 1;      
	   }

	public String getInput() {
	    String result = null;
	    String text = null;
	    
	    while (result == null)
	    {
	       text = consolePanel.getTextFieldValue();
	       
	       if (text != null)
	       {
	    	   return text;
	       }
	       try
	       {
	          Thread.sleep(1000);
	       }
	       catch (Exception e)
	       {
	          print("In Here");
	       }
	    }
	    return null;
	}
}
