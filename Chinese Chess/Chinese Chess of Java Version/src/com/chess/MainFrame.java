package com.chess;

//import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Point;
import java.awt.Rectangle;
//import java.awt.GridBagLayout;
//import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

//import com.chess.Chess.Blink;
//import com.chess.Chess.Moniter;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 565;
	public static final int FRAME_HEIGHT = 700;
	Image offScreenImage = null;
	
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	//private JLabel image = null; // For background image
	private JPanel jPanel1 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	//private JLabel jLabel2 = null;
	//private JLabel play[] = new JLabel[9];
	
	private ChessGroup red = null;  //  @jve:decl-index=0:
	private ChessGroup black = null;  //  @jve:decl-index=0:
	private ChessBoard cb = null;  //  @jve:decl-index=0:
	private boolean RedPlay = false;
	private boolean isOver = false;
	public boolean isClick = false;
	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
		// for double buffering
		new Thread(new PaintThread()).start();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(565, 700);
		this.setContentPane(getJContentPane());
		this.setTitle("[Chinese Chess] Designed by Dr.Louis TANG/ FALL 2011");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		/*Point p3 = new Point(25,25);
		for(int i=0; i<9; i++){
			p3.setLocation( 25+ i*57, 25);
			play[i] = new JLabel();
			play[i].setIcon(new ImageIcon("images/red-ju.gif"));
			play[i].setSize(55,55);
			play[i].setLocation(p3);
			play[i].setVisible(true);
			jPanel.add(play[i],null);
			
		}*/
	}
	
	// for double buffering
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	// for double buffering
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("This PaintThread is triggered!");
			}
		}
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
		}
		
		/*// red chess
		// JIANG
		jLabel1 = new JLabel();
		jLabel1.setIcon(new ImageIcon("images/red-jiang.gif"));
		jLabel1.setSize(Chess.SIZE, Chess.SIZE);
		jLabel1.setLocation(new Point(Chess.START_X + 4*Chess.UNIT,Chess.START_Y));
		jLabel1.setVisible(true);
		jPanel.add(jLabel1,null);
		
		// JU
		Point p2 = new Point(25,80);
		jLabel2 = new JLabel();
		jLabel2.setIcon(new ImageIcon("images/red-ju.gif"));
		jLabel2.setSize(55, 55);
		//jLabel1.setBounds(80, 80, 55, 55);
		jLabel2.setLocation(p2);
		jLabel2.setVisible(true);
		jPanel.add(jLabel2,null);
		*/
		
        //jContentPane.add(jLabel1,null);
		
		jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon("images/main.gif"));
		jLabel.setBounds(0, 0, 582, 620);
		jLabel.setVisible(true);
		jPanel.add(jLabel,null);
		
		
		
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(0, 0, 558, 620));
			//jPanel.add(image = new JLabel(new ImageIcon("images\\main.gif")));
			//image.setBounds(0,0,558,620);
			jPanel.addMouseListener(new Moniter());
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(384, 2, 171, 36));
			jLabel1.setText("Black side walks first.");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(0, 622, 558, 41));
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton1(), null);
			jPanel1.add(getJButton2(), null);
			jPanel1.add(jLabel1, null);
		}
		return jPanel1;
	}
	
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(3, 2, 123, 37));
			jButton.setText("New Game");
			//jButton.setToolTipText("Start a new game!");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					jLabel.setVisible(false);
					jPanel.remove(jLabel);
					newGame();
					jLabel.setVisible(true);
					jPanel.add(jLabel,null);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(128, 2, 123, 37));
			jButton1.setText("Play Back");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); 
					// TODO Auto-generated Event stub actionPerformed()
					/*jLabel.setVisible(false);
					jPanel.remove(jLabel);
					Chess c = new Chess("black-ma.gif",5,5);
					
					jPanel.add(c,null);
					jLabel.setVisible(true);
					jPanel.add(jLabel,null);*/
				}
			});
		}
		return jButton1;
	}
	
	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(254, 2, 123, 37));
			jButton2.setText("Draw");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); 
					// TODO Auto-generated Event stub actionPerformed()
					//disableChosen(red);
				}
			});
		}
		return jButton2;
	}
	
	public void newGame(){
		if(red != null){
			ListIterator<Chess> lit = red.getChess().listIterator();
			while(lit.hasNext()){
				Chess c = lit.next();
				jPanel.remove(c);
			}
			red = null;
		}
		if(black != null){
			ListIterator<Chess> lit = black.getChess().listIterator();
			while(lit.hasNext()){
				Chess c = lit.next();
				jPanel.remove(c);
			}
			black = null;
		}
		if(cb != null){
			cb = null;
		}
		String[] str = readImageFolder("images/");
		for(int i=0; i<str.length; i++){
			System.out.println(str[i]);
		}
		red = new ChessGroup(str, "red");
		drawChess(red);
		black = new ChessGroup(str, "black");
		drawChess(black);
		cb = new ChessBoard(9,10);
		cb.SetPositionTaken(red);
		cb.SetPositionTaken(black);
		new ChessDestroy(red,black);
	}
	
	public void disableChosen(ChessGroup cg){
		Iterator<Chess> it = cg.getChess().iterator();
		while(it.hasNext()){
			Chess c = it.next();
			if(c.isChosen()){
				c.setChosen(false);
			}
		}
	}
	public boolean findChosen(ChessGroup cg){
		Iterator<Chess> it = cg.getChess().iterator();
		boolean flag = false;
		while(it.hasNext()){
			Chess c = it.next();
			if(c.isChosen()){
				cg.setIndex(c.getId());
				flag = true;
			}
		}
		return flag;
	}
	public static String[] readImageFolder(String url){
		String tmp = new String();
		String[] str = null;
		//System.out.println("enter to here!");
		//Pattern p = Pattern.compile("[a-z]{3,5}-\\d{1,2}\\.gif");
		Pattern p = Pattern.compile("[a-z]{3,5}-[a-z]{2,5}.gif");
		HashSet<String> hs = new HashSet<String>();
		File f = new File(url);
		if(!f.exists()|| !f.isDirectory()){
			System.out.println("please input an valid directory name!");
			return null;
		}
		File[] fArray = f.listFiles();
		for(int i=0; i<fArray.length; i++){
			if(fArray[i].isFile()){
				tmp = fArray[i].getName();
				//System.out.println(tmp);
				Matcher m = p.matcher(tmp);
				if(m.matches()){
					//hs.add(tmp.substring(0, tmp.length()-4));
					hs.add(tmp);
				}
			}
		}
		if(str == null){
			str = new String[hs.size()];
			Iterator<String> it = hs.iterator();
			int j = 0;
			while(it.hasNext()){
				tmp = it.next();
				str[j] = new String(tmp);
				j++;
				//System.out.println(j);
			}
		}
		return str;
	}
	
	public void drawChess(ChessGroup cg){
		
		Iterator<Chess> it = cg.getChess().iterator();
		while(it.hasNext()){
			Chess c = it.next();
			jPanel.add(c,null);
		}
	}
	

	
	public int[] convertIndex(int mx, int my){
		int[] back= new int[2];
		int range = 15;
		int index_x = -1;
		int index_y = -1;
		int start_x = 51;
		int start_y = 53;
		int reminder_x = (mx - start_x)%Chess.UNIT;
		int reminder_y = (my - start_y)%Chess.UNIT;
		//System.out.println("reminder x is :"+ reminder_x + "y is :"+ reminder_y);
		// cross the border case
		if(mx < (start_x-range) || mx > (start_x+Chess.UNIT*8+range) || my < (start_y-range) || my > (start_y+Chess.UNIT*9+range)){
			back[0] = -1;
			back[1] = -1;
			return back;
		}
		// only response in 10 X 10 local area
		if((reminder_x>range && reminder_x<(Chess.UNIT-range)) || (reminder_y>range && reminder_y<(Chess.UNIT-range))){
			// don't response
			;
		}else{
			if(reminder_x <= range){
				index_x = (mx - start_x)/Chess.UNIT;
			}else if(reminder_x >= (Chess.UNIT-range)){
				index_x = (mx - start_x +range)/Chess.UNIT;
			}
			if(reminder_y <=range){
				index_y = (my - start_y)/Chess.UNIT;
			}else if(reminder_y >=(Chess.UNIT-range)){
				index_y = (my - start_y +range)/Chess.UNIT;
			}
		}
		back[0] = index_x;
		back[1] = index_y;
		//System.out.println("x is :"+ index_x + "y is :"+ index_y);
		return back;
	}
	
	/**
	 * Inner class. Used to respond mouse click on the Panle
	 * @author Louis
	 *
	 */
	class Moniter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getX());
			System.out.println(e.getY());
			//Chess cf = new Chess();
			int[] tmp = new int[2];
			tmp = convertIndex(e.getX(),e.getY());
			if(tmp[0]!=-1 && tmp[0]!=-1){
				if(RedPlay == true){
					System.out.println("red side walks");

					red.CalIndex();
					black.CalIndex();
					if(red.getIndex()!=-1){
						Chess c = red.getChess().get(red.getIndex());
						int old_x = c.getIndX();
						int old_y = c.getIndY();
						ChessRule.AllRules(cb, c, tmp[0], tmp[1]);
						//red.setIndex(-1);
						red.disableChosen();
						if(old_x != c.getIndX() || old_y != c.getIndY()){
							RedPlay = false;
							jLabel1.setText("Now is Black side's turn!");
						}
					}
					if(black.getIndex()!=-1){
						//Chess c = black.getChess().get(black.getIndex());
						//c.setChosen(false);
						black.disableChosen();
						//black.setIndex(-1);
					}

				}
				else{
					System.out.println("black side walks!");

					red.CalIndex();
					black.CalIndex();
					if(red.getIndex()!=-1){
						//Chess c = red.getChess().get(red.getIndex());
						//c.setChosen(false);
						red.disableChosen();
						//red.setIndex(-1);
					}
					if(black.getIndex()!=-1){
						Chess c = black.getChess().get(black.getIndex());
						int old_x = c.getIndX();
						int old_y = c.getIndY();
						ChessRule.AllRules(cb, c, tmp[0], tmp[1]);
						//black.setIndex(-1);
						black.disableChosen();
						if(old_x != c.getIndX() || old_y != c.getIndY()){
							RedPlay = true;
							jLabel1.setText("Now is Red side's turn!");
						}

					}
				}

			}

		}

		
	}
	
	/**
	 * Inner class to handle chess destroy
	 */
	class ChessDestroy implements Runnable{
		private ChessGroup cg1 = null;
		private ChessGroup cg2 = null;
		ChessDestroy(ChessGroup cg1,ChessGroup cg2 ){
			this.cg1 = cg1;
			this.cg2 = cg2;
			(new Thread(this)).start();
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!isOver){
			//if(!isOver){
				System.out.println("subthread running!");
			// red chess been clicked first, and another chess been clicked
				cg1.CalIndex();
				cg2.CalIndex();
				if(cg1.getNumTaken()>1){
					cg1.disableChosen();
				}
				if(cg2.getNumTaken()>1){
					cg2.disableChosen();
				}
				if(cg1.getNumTaken()==1 && cg2.getNumTaken()==1){
					//Can not stop click black chess first. it will cause problem. need to be fixed later
					if(RedPlay == true){ // red chess eat black
						Chess r = cg1.getChess().get(cg1.getIndex());
						Chess b = cg2.getChess().get(cg2.getIndex());
						int x = b.getIndX();
						int y = b.getIndY();
						int old_x = r.getIndX();
						int old_y = r.getIndY();
						if(r.getRank().equals("pao")){
							ChessRule.cannonRule(cb, r, b);
						}else{
							ChessRule.AllRules(cb, r, x, y);
						}
						if(old_x != r.getIndX() || old_y != r.getIndY()){
							RedPlay = false;
							jLabel1.setText("Now is Black side's turn!");
							b.dead();
							jPanel.remove(b);
						}
						
					}else{ // black chess eat red
						Chess r = cg1.getChess().get(cg1.getIndex());
						Chess b = cg2.getChess().get(cg2.getIndex());
						int x = r.getIndX();
						int y = r.getIndY();
						int old_x = b.getIndX();
						int old_y = b.getIndY();
						if(b.getRank().equals("pao")){
							ChessRule.cannonRule(cb, b, r);
						}else{
							ChessRule.AllRules(cb, b, x, y);
						}
						if(old_x != b.getIndX() || old_y != b.getIndY()){
							RedPlay = true;
							jLabel1.setText("Now is Red side's turn!");
							r.dead();
							jPanel.remove(r);
						}
						
					}
				}
				if(!cg1.isAlive() || !cg2.isAlive()){
					isOver = true;
					System.out.println("Game over!");
					if(!cg1.isAlive()){
						JOptionPane.showMessageDialog(null,
							"Red side LOSE", "Game Over!",
							JOptionPane.PLAIN_MESSAGE);
						System.exit(NORMAL);
					}else{
						JOptionPane.showMessageDialog(null,
								"Black side LOSE", "Game Over!",
								JOptionPane.PLAIN_MESSAGE);
						System.exit(NORMAL);
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"


