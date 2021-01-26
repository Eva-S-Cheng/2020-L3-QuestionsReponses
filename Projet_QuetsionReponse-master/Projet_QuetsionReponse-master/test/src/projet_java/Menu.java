package projet_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.mysql.jdbc.Statement;

import java.awt.Font;
import javax.swing.JComboBox;


public class Menu implements ActionListener {

	public static String databaseName = "bdd";
	public static String url= "jdbc:mysql://localhost:3306/" + databaseName;
	public static Connection myConn;
	public JFrame menuWindow;
	public JLayeredPane interface_menu;
	public JPanel menuAccueil;
	public JButton buttonStart;
	public JButton buttonRules;
	public JButton buttonQuit;
	public JButton buttonThemes;
	public JPanel menuThemes;
	public JButton buttonRThemes;
	public JButton buttonQuestionsQCM;
	public JButton buttonRetour;
	public JTable dataAll;
	public JButton buttonQuestionsVF;
	public JButton btnRepcourtes;
	public JButton buttonAjouter;
	public JButton buttonModSuppr;
	public JPanel menuRègles;
	public JTextPane rulesText;
	public JButton buttonRulesRetour;
	public JScrollPane scrollData ;
	public JPanel menuModDel;
	public JScrollPane scrollModDel;
	public JTable dataAllbis;
	public JComboBox<Integer> comboBoxTypeQ;
	public JButton btnSupprDB;
	public JButton btnretour;
	public JButton btnqcm;
	public JButton btnrc;
	public JButton btnvf;
	public JComboBox<Integer> comboBoxIDQ;
	public JComboBox<Integer> comboBoxDifficulté;
	public JComboBox<String> comboBoxTheme;
	public ArrayList<String> themes = new ArrayList<>();
	public ListeQuestions listeQ = new ListeQuestions();
	
	public Menu() {
		menuWindow = new JFrame("A JFrame");
		menuWindow.setTitle("THE QUIZZ GAME");
	    menuWindow.setSize(671, 579);
	    menuWindow.setLocation(300,200);
	    menuWindow.getContentPane().setLayout(null);
	    
	    interface_menu = new JLayeredPane();
	    interface_menu.setBounds(0, 0, 655, 540);
	    menuWindow.getContentPane().add(interface_menu);
	    interface_menu.setLayout(new CardLayout(0, 0));
	    
	    menuAccueil = new JPanel();
	    menuAccueil.setBackground(SystemColor.info);
	    interface_menu.add(menuAccueil, "name_2283982189150300");
	    menuAccueil.setLayout(null);
	    
	    buttonStart = new JButton("Jouer");
	    buttonStart.setBackground(SystemColor.controlShadow);
	    buttonStart.setBounds(63, 276, 130, 57);
	    menuAccueil.add(buttonStart);
	    
	    buttonRules = new JButton("R\u00E8gles");
	    buttonRules.setBackground(SystemColor.controlShadow);
	    buttonRules.setBounds(258, 276, 130, 57);
	    menuAccueil.add(buttonRules);
	    
	    buttonQuit = new JButton("Quitter");
	    buttonQuit.setBackground(SystemColor.controlShadow);
	    buttonQuit.setBounds(454, 276, 130, 57);
	    menuAccueil.add(buttonQuit);
	    
	    buttonThemes = new JButton("Themes/Questions");
	    buttonThemes.setBackground(SystemColor.controlShadow);
	    buttonThemes.setBounds(237, 156, 164, 57);
	    menuAccueil.add(buttonThemes);
	    
	    menuThemes = new JPanel();
	    menuThemes.setBackground(SystemColor.info);
	    interface_menu.add(menuThemes, "name_2286997093067700");
	    menuThemes.setLayout(null);
	    
	    buttonRThemes = new JButton("Th\u00E8mes");
	    buttonRThemes.setBackground(Color.LIGHT_GRAY);
	    buttonRThemes.setBounds(390, 434, 110, 42);
	    menuThemes.add(buttonRThemes);
	    
	    buttonQuestionsQCM = new JButton("QCM");
	    buttonQuestionsQCM.setBackground(Color.LIGHT_GRAY);
	    buttonQuestionsQCM.setBounds(30, 434, 110, 42);
	    menuThemes.add(buttonQuestionsQCM);
	    
	    buttonRetour = new JButton("Retour");
	    buttonRetour.setBackground(Color.LIGHT_GRAY);
	    buttonRetour.setBounds(510, 434, 110, 42);
	    menuThemes.add(buttonRetour);
	    
	    scrollData = new JScrollPane();
	    scrollData.setBounds(10, 11, 635, 412);
	    menuThemes.add(scrollData);
	    
	    dataAll = new JTable();
	    scrollData.setViewportView(dataAll);
	    
	    buttonQuestionsVF = new JButton("VF");
	    buttonQuestionsVF.setBackground(Color.LIGHT_GRAY);
	    buttonQuestionsVF.setBounds(150, 434, 110, 42);
	    menuThemes.add(buttonQuestionsVF);
	    
	    btnRepcourtes = new JButton("Rep_courtes");
	    btnRepcourtes.setBackground(Color.LIGHT_GRAY);
	    btnRepcourtes.setBounds(270, 434, 110, 42);
	    menuThemes.add(btnRepcourtes);
	    
	    buttonAjouter = new JButton("Ajouter");
	    buttonAjouter.setBackground(Color.LIGHT_GRAY);
	    buttonAjouter.setBounds(130, 487, 193, 42);
	    menuThemes.add(buttonAjouter);
	    
	    buttonModSuppr = new JButton("Modifier ou Supprimer");
	    buttonModSuppr.setBackground(Color.LIGHT_GRAY);
	    buttonModSuppr.setBounds(333, 487, 190, 42);
	    menuThemes.add(buttonModSuppr);
	    
	    menuRègles = new JPanel();
	    menuRègles.setBackground(SystemColor.info);
	    interface_menu.add(menuRègles, "name_2351453701193900");
	    menuRègles.setLayout(null);
	    
	    rulesText = new JTextPane();
	    rulesText.setBackground(new Color(255, 222, 173));
	    rulesText.setForeground(Color.DARK_GRAY);
	    rulesText.setFont(new Font("Dialog", Font.ITALIC, 12));
	    rulesText.setText("\r\n\r\n\r\n\r\n\r\nVoici les r\u00E8gles du jeu :\r\n     On a une liste de 20 joueurs \r\n     Parmi ces 20 joueurs, 4 sont s\u00E9lectionn\u00E9s al\u00E9atoirement pour participer \u00E0\u00A0 la phase 1\r\n     PHASE I : Chaque joueur r\u00E9pond \u00E0 une question facile choisie al\u00E9atoire selon un th\u00E8me g\u00E9n\u00E9r\u00E9 en s\u00E9quentiel\r\n     SELECTION : Les joueurs ayant r\u00E9pondu correctement ont 2 points en plus et sont \u00E9limin\u00E9s ceux qui ont mal r\u00E9pondu\r\n     PHASE II : 6 th\u00E8mes sont s\u00E9lectionn\u00E9s al\u00E9atoirement, chaque joueur choisi un th\u00E8me parmi les 6 et r\u00E9pond \u00E0\u00A0 une question de niveau moyenne g\u00E9n\u00E9r\u00E9e al\u00E9atoirement\r\n     SELECTION : Les joueurs ayant r\u00E9pondu correctement ont 3 points en plus par question, le joueur au score minimal est \u00E9limin\u00E9\r\n     PHASE III : Chaque joueur r\u00E9ponds \u00E0\u00A0 3 questions difficiles de 3 th\u00E8mes choisis par le cr\u00E9ateur du jeu\r\n     SELECTION : Les joueurs ayant r\u00E9pondu correctement ont 5 points en plus par question et celui au score minimal est \u00E9limin\u00E9\r\n     En cas de conflit au niveau des scores, on \u00E9limine le joueur au score minimal qui aura mis le plus de temps pour r\u00E9pondre\r\n     En cas de conflit pour les d\u00E9lais, on s\u00E9lectionne al\u00E9atoirement\r\nBON JEU A VOUS ");
	    rulesText.setBounds(10, 11, 635, 468);
	    menuRègles.add(rulesText);
	    
	    buttonRulesRetour = new JButton("Retour");
	    buttonRulesRetour.setBackground(Color.LIGHT_GRAY);
	    buttonRulesRetour.setBounds(10, 490, 635, 39);
	    menuRègles.add(buttonRulesRetour);
	    
	    menuModDel = new JPanel();
	    menuModDel.setBackground(SystemColor.info);
	    interface_menu.add(menuModDel, "name_2353051968256200");
	    menuModDel.setLayout(null);
	    
	    scrollModDel = new JScrollPane();
	    scrollModDel.setBounds(10, 11, 635, 250);
	    menuModDel.add(scrollModDel);
	    
	    dataAllbis = new JTable();
	    scrollModDel.setViewportView(dataAllbis);
	    
	    comboBoxTheme = new JComboBox<String>();
	    comboBoxTheme.setBackground(Color.LIGHT_GRAY);
	    comboBoxTheme.setEditable(true);
	    comboBoxTheme.setFont(new Font("Tahoma", Font.PLAIN, 7));
	    comboBoxTheme.setBounds(421, 272, 107, 32);
	    comboBoxTheme.addItem("qcm");
	    comboBoxTheme.addItem("vf");
	    comboBoxTheme.addItem("rep_courte");
	    comboBoxTheme.setSelectedItem("Type de la Question");
	    menuModDel.add(comboBoxTheme);
	        
	    comboBoxDifficulté = new JComboBox();
	    comboBoxDifficulté.setBackground(Color.LIGHT_GRAY);
	    comboBoxDifficulté.setFont(new Font("Tahoma", Font.PLAIN, 7));
	    comboBoxDifficulté.setEditable(true);
	    comboBoxDifficulté.setBounds(538, 272, 107, 32);
	    comboBoxDifficulté.setSelectedItem("Sélectionner la difficulté");
	    comboBoxDifficulté.addItem(1);
	    comboBoxDifficulté.addItem(2);
	    comboBoxDifficulté.addItem(3);
	    menuModDel.add(comboBoxDifficulté);
	    
	    comboBoxIDQ = new JComboBox();
	    comboBoxIDQ.setFont(new Font("Tahoma", Font.PLAIN, 7));
	    comboBoxIDQ.setBackground(Color.LIGHT_GRAY);
	    comboBoxIDQ.setEditable(true);
	    comboBoxIDQ.setBounds(302, 272, 107, 32);
	    comboBoxIDQ.setSelectedItem("Sélection l'ID de la question");
	    comboBoxIDQ.addItem(1);comboBoxIDQ.addItem(2);comboBoxIDQ.addItem(3);comboBoxIDQ.addItem(4);comboBoxIDQ.addItem(5);
	    comboBoxIDQ.addItem(6);comboBoxIDQ.addItem(7);comboBoxIDQ.addItem(8);comboBoxIDQ.addItem(9);comboBoxIDQ.addItem(10);
	    menuModDel.add(comboBoxIDQ);
	    
	    btnvf = new JButton("VF");
	    btnvf.setBackground(Color.LIGHT_GRAY);
	    btnvf.setBounds(10, 332, 120, 49);
	    menuModDel.add(btnvf);
	    
	    btnrc = new JButton("Reponses courtes");
	    btnrc.setBackground(Color.LIGHT_GRAY);
	    btnrc.setBounds(10, 392, 120, 49);
	    menuModDel.add(btnrc);
	    
	    btnqcm = new JButton("QCM");
	    btnqcm.setBackground(Color.LIGHT_GRAY);
	    btnqcm.setBounds(10, 272, 120, 49);
	    menuModDel.add(btnqcm);
	    
	    comboBoxTypeQ = new JComboBox();
	    comboBoxTypeQ.setBackground(Color.LIGHT_GRAY);
	    comboBoxTypeQ.setEditable(true);
	    comboBoxTypeQ.setFont(new Font("Tahoma", Font.PLAIN, 7));
	    comboBoxTypeQ.setBounds(185, 272, 107, 32);
	    comboBoxTypeQ.setSelectedItem("Sélectionner le theme");
	    comboBoxTypeQ.addItem(1);comboBoxTypeQ.addItem(2);comboBoxTypeQ.addItem(3);comboBoxTypeQ.addItem(4);comboBoxTypeQ.addItem(5);
	    comboBoxTypeQ.addItem(6);comboBoxTypeQ.addItem(7);comboBoxTypeQ.addItem(8);comboBoxTypeQ.addItem(9);comboBoxTypeQ.addItem(10);
	    menuModDel.add(comboBoxTypeQ);
	    
	    btnSupprDB = new JButton("Supprimer");
	    btnSupprDB.setBackground(Color.LIGHT_GRAY);
	    btnSupprDB.setBounds(185, 315, 460, 42);
	    menuModDel.add(btnSupprDB);
	    
	    btnretour = new JButton("Retour");
	    btnretour.setBackground(Color.LIGHT_GRAY);
	    btnretour.setBounds(10, 452, 120, 49);
	    menuModDel.add(btnretour);
	    
	    	    
	    buttonStart.addActionListener(this);
	    buttonQuit.addActionListener(this);
	    buttonRules.addActionListener(this);
	    buttonThemes.addActionListener(this);
	    buttonRThemes.addActionListener(this);
	    buttonQuestionsQCM.addActionListener(this);
	    buttonRetour.addActionListener(this);
	    buttonQuestionsVF.addActionListener(this);
	    btnRepcourtes.addActionListener(this);
	    buttonAjouter.addActionListener(this);
	    buttonModSuppr.addActionListener(this);
	    buttonRulesRetour.addActionListener(this);
	    btnretour.addActionListener(this);
	    btnqcm.addActionListener(this);
	    btnvf.addActionListener(this);
	    btnrc.addActionListener(this);
	    btnSupprDB.addActionListener(this);
	    
	    try {
			myConn = DriverManager.getConnection(url, "Quizz" , "Quizz94260" );
			JOptionPane.showMessageDialog(this.menuWindow, "Vous êtes maintenant connecté", "Success at connecting", JOptionPane.WARNING_MESSAGE);	
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this.menuWindow, "SQLException: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);	
		}
	    
	    fillThemes(myConn);
	    fillQuestionsQCM(myConn);
	    fillQuestionsVF(myConn);
	    fillQuestionsRC(myConn);
	    menuWindow.setVisible(true);
	    
	    
	}
	
	public void fillQuestionsRC(Connection m) {
		PreparedStatement myStmt;
		ResultSet rs;
		String sql = "select * from rep_courte";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			while (rs.next()) {
				Question<QuestionRC> temp = null;
				temp = new Question(rs.getString("Thême").toUpperCase(),rs.getInt("Difficulté"),
						new QuestionRC(rs.getString("Question"),rs.getString("Réponse_correcte")));
				listeQ.ajouterQuestion(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fillQuestionsVF(Connection m) {
		PreparedStatement myStmt;
		ResultSet rs;
		String sql = "select * from vf";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			while (rs.next()) {
				Question<QuestionVF> temp = null;
				if (rs.getInt("Réponse_correcte") == 1) {
					temp = new Question(rs.getString("Thême").toUpperCase(),rs.getInt("Difficulté"),
							new QuestionVF(rs.getString("Question"),true));
				} else if (rs.getInt("Réponse_correcte") == 0) {
					temp = new Question(rs.getString("Thême").toUpperCase(),rs.getInt("Difficulté"),
							new QuestionVF(rs.getString("Question"),false));
				}
				listeQ.ajouterQuestion(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fillQuestionsQCM(Connection m) {
		PreparedStatement myStmt;
		ResultSet rs;
		String sql = "select * from qcm";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> choice = new ArrayList<>();
				choice.add(rs.getString("Réponse_1"));choice.add(rs.getString("Réponse_2"));choice.add(rs.getString("Réponse_3"));
				Question<QuestionQCM> temp = new Question(rs.getString("Thême").toUpperCase(),rs.getInt("Difficulté"),
						new QuestionQCM(rs.getString("Question"),choice,rs.getInt("Réponse_correcte")));
				listeQ.ajouterQuestion(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fillThemes(Connection m) {
		PreparedStatement myStmt;
		ResultSet rs;
		String sql = "select * from showThemes";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			while(rs.next()) {
				themes.add(rs.getString("Thême"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkThemes(Connection m,JTable tD) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		String sql = "select * from showThemes";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			tD.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void checkVF(Connection m,JTable tD) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		String sql = "select * from vf";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			tD.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void checkQCM(Connection m,JTable tD) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		String sql = "select * from qcm";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			tD.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void checkREPCOURTE(Connection m,JTable tD) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		String sql = "select * from rep_courte";
		try {
			myStmt = m.prepareStatement(sql);
			rs = myStmt.executeQuery();
			tD.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public int SupprimerQ(Connection m,String type,int id1,int id2,int id3) {
		PreparedStatement myStmt = null;
		String sql = null;
		if ( type == "vf") {
			sql = "delete from vf where ID=? and Difficulté=? and ID_question=?";
		} else if ( type == "qcm") {
			sql = "delete from qcm where ID=? and Difficulté=? and ID_question=?";
		} else if ( type == "rep_courte") {
			sql = "delete from rep_courte where ID=? and Difficulté=? and ID_question=?";
		}
		int rA = 0;
		try {
			myStmt = m.prepareStatement(sql);
			myStmt.setInt(1,id1); myStmt.setInt(2,id2); myStmt.setInt(3,id3);
			rA = myStmt.executeUpdate();
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return rA;
	}
	
	public void SwitchPanel(JPanel panel) {
		this.interface_menu.removeAll();
		this.interface_menu.add(panel);
		this.interface_menu.repaint();
		this.interface_menu.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ( e.getSource() == buttonThemes) {
			SwitchPanel(menuThemes);
		}
		else if ( e.getSource() == buttonRules) {
			SwitchPanel(menuRègles);
		}
		else if ( e.getSource() == buttonRulesRetour ) {
			SwitchPanel(menuAccueil);
		}
		else if (e.getSource() == buttonQuit) {
			try {
				myConn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.gc();
			this.menuWindow.dispose();
		}
		else if ( e.getSource() == buttonRetour ) {
			SwitchPanel(menuAccueil);
		}
		else if ( e.getSource() == buttonRThemes ) {
			try {
				checkThemes(myConn,dataAll);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == btnqcm ) {
			try {
				checkQCM(myConn,dataAllbis);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == buttonQuestionsQCM) {
			try {
				checkQCM(myConn,dataAll);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == btnvf ) {
			try {
				checkVF(myConn,dataAllbis);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == buttonQuestionsVF ) {
			try {
				checkVF(myConn,dataAll);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == btnrc ) {
			try {
				checkREPCOURTE(myConn,dataAllbis);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == btnRepcourtes) {
			try {
				checkREPCOURTE(myConn,dataAll);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getSource() == buttonModSuppr ) {
			SwitchPanel(menuModDel);
		}
		else if (e.getSource() == btnretour) {
			SwitchPanel(menuThemes);
		}
		else if (e.getSource() == btnSupprDB) {
			SupprimerQ(myConn,comboBoxTheme.getSelectedItem().toString(),(int)comboBoxTypeQ.getSelectedItem(),
					(int)comboBoxDifficulté.getSelectedItem(),(int)comboBoxIDQ.getSelectedItem());
		}
		else if (e.getSource() == buttonStart ) {
			
		}
	}
}
