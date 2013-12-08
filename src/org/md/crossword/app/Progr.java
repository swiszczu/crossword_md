package org.md.crossword.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.md.crossword.board.CwEntry;
import org.md.crossword.gui.CrosswordTable;
import org.md.crossword.gui.CrosswordTableModel;
import org.md.crossword.gui.GenerateBtnListener;
import org.md.crossword.io.CwBrowser;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class Progr.
 */
public class Progr{
    //GUI
    /** The main frame. */
    private JFrame mainFrame;
    
    /** The top panel. */
    private JPanel topPanel;
    
    /** The generate btn. */
    private JButton generateBtn;
    
    /** The print btn. */
    private JButton printBtn;
    
    /** The save btn. */
    private JButton saveBtn;
    
    /** The width spinner. */
    private JSpinner widthSpinner;
    
    /** The height spinner. */
    private JSpinner heightSpinner;
    
    /** The current crossword. */
    private static Crossword currentCrossword;
    
    /** The path to dictionary. */
    public static String pathToDictionary="cwdb.txt";
    
    /** The browser. */
    public static CwBrowser browser;
    
    /** The cw t. */
    private CrosswordTable cwT;
	
	/**
	 * Instantiates a new progr.
	 *
	 * @param cw the cw
	 * @throws Exception the exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Progr(Crossword cw) throws Exception,IOException{
        currentCrossword=cw;

        mainFrame=new JFrame("Krzyżówka");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 700);
        mainFrame.setPreferredSize(new Dimension(700,700));

        //Drawer krzyżówki
        cwT = new CrosswordTable(new CrosswordTableModel(currentCrossword));
        cwT.setSize(80, 80);
        //Menu górne
        topPanel=new JPanel();
        generateBtn=new JButton("Generuj");
        saveBtn=new JButton("Zapisz");
        printBtn=new JButton("Drukuj");
        topPanel.add(new JLabel("Szerokość:"));
        widthSpinner=new JSpinner(new SpinnerNumberModel(15, 8, 30, 1));
        widthSpinner.setPreferredSize(new Dimension(40, 20));
        topPanel.add(widthSpinner);
        topPanel.add(new JLabel("Wysokość:"));
        heightSpinner=new JSpinner(new SpinnerNumberModel(15, 8, 30, 1));
        heightSpinner.setPreferredSize(new Dimension(40, 20));
        topPanel.add(heightSpinner);
        topPanel.add(generateBtn);
        topPanel.add(saveBtn);
        topPanel.add(printBtn);

        mainFrame.getContentPane().add(cwT,BorderLayout.CENTER);
        mainFrame.getContentPane().add(topPanel,BorderLayout.PAGE_START);

        //handlery akcji
        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setCurrentCrossword(browser.newCrosswordFromFile((Integer)(widthSpinner.getValue()), (Integer)(heightSpinner.getValue()), pathToDictionary));
                mainFrame.revalidate();
                mainFrame.repaint();

                System.out.println("You clicked the button");
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try{
                    browser.save(currentCrossword);
                    System.out.println("Zapisano!");
                }
                catch (Exception exc)
                {

                }
            }
        });
        printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print();
            }
        });
        mainFrame.pack();
        mainFrame.setVisible(true);
	}
    
    /**
     * Prints the.
     */
    private void print() {

        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("jTable.pdf"));

            document.open();
            PdfContentByte cb = writer.getDirectContent();

            cb.saveState();
            Graphics2D g2 = cb.createGraphicsShapes(cwT.getWidth(), cwT.getHeight());
            g2.translate(48.0,48.0);
            g2.scale(0.5,0.5);
            cwT.print(g2);


            g2.dispose();
            cb.restoreState();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        document.close();
    }
    
    /**
     * Gets the current crossword.
     *
     * @return the current crossword
     */
    public static Crossword getCurrentCrossword()
    {
        return currentCrossword;
    }
    
    /**
     * Sets the current crossword.
     *
     * @param cross the new current crossword
     */
    public static void setCurrentCrossword(Crossword cross)
    {
        currentCrossword=cross;
    }
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Unhandled exc:"+e.toString());
            }
        });
		try {
            browser = new CwBrowser(".");
            pathToDictionary="cwdb.txt";


			Progr dp1 = new Progr(browser.newCrosswordFromFile(15, 15, pathToDictionary));

			//long id = (long) 1258135046871.0;
			//Crossword cw2 = cwBr.load(id);



			/*
			 * Crossword cw2 = new Crossword(5, 5, pathToFileDb);
			 * cw2.generate(new CrossStrategy());
			 */

			//Progr dp2 = new Progr(cw2);
			//dp2.setSize(700, 700);
			//dp2.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
