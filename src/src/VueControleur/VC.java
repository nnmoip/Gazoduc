package VueControleur;

import Modele.GrilleSimple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VC extends JFrame implements Observer {

    JLabel jMeilleur = new JLabel("Meilleur Score");
    JLabel jMeilleurS = new JLabel("0");
    JLabel jTime = new JLabel("Temps : 0 s");
    JLabel jScore = new JLabel("Score : 0");
    JLabel jLignes = new JLabel("Nombre de Lignes : 0");
    JButton jDemarre = new JButton("Nouvelle Partie");
    JButton jPause = new JButton("Pause");

    JLabel goLabel = new JLabel("Game Over");

    GrilleSimple modele;

    /* premier sous-panneau : le tétris en lui même, il faudra le supprimer lorsque le jeu est fini */
    JPanel jGrille = new JPanel();

    Observer vueGrille;
    private Executor ex =  Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modele) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modele = _modele;

        setSize(500, 560);
        setTitle("Tetris Game");
        setLocationRelativeTo(null); // s'affiche au centre de l'écran


        // panneau qui prend toute le frame pour pouvoir ordonner d'autres panneau à l'intérieur
        JPanel contentPanel = new JPanel();
        // on enlève les types de rangement par défaut
        contentPanel.setLayout(null);



        // jGrille prédéfini en tant que variable globale
        // on initialise l'emplacemetn et la taille du panneau
        jGrille.setBounds(0, 5, 280, 600);
        vueGrille = new VueGrilleV2(modele); // composant AWT dédié
        jGrille.add((JPanel)vueGrille);

        contentPanel.add(jGrille);



        // on initialise l'affichage du Game Over, superposé à la grille de jeu
        // écriture Game Over
        goLabel.setForeground(Color.RED);
        goLabel.setFont(new Font("Bell MT", Font.BOLD, 25));

        // bandeau Game Over
        JPanel jBarGO = new JPanel();
        jBarGO.setBounds(0, 220, 250, 40);
        jBarGO.setBackground(Color.WHITE);
        jBarGO.add(goLabel);
        
        // écran noir
        JPanel jGO = new JPanel();
        jGO.setBounds(15, 15, 250, 495);
        jGO.setBackground(Color.BLACK);
        jGO.setLayout(null);
        jGO.add(jBarGO);

        contentPanel.add(jGO);



        // initialisation des sous-panneaux qui concernent les changements dans le jeu
        jMeilleur.setFont(new Font("Bell MT", Font.BOLD, 20));
        jMeilleurS.setFont(new Font("Bell MT", Font.BOLD, 20));
        
        JPanel jM = new JPanel();
        jM.setBounds(280, 100, 200, 40);
        jM.add(jMeilleur);

        JPanel jMS = new JPanel();
        jMS.setBounds(310, 140, 150, 40);
        jMS.add(jMeilleurS);

        contentPanel.add(jM);
        contentPanel.add(jMS);
        
        JPanel jD = new JPanel();
        jD.setBounds(310, 200, 150, 40);
        jD.add(jDemarre);

        contentPanel.add(jD);

        JPanel jP = new JPanel();
        jP.setBounds(310, 250, 150, 40);
        jP.add(jPause);

        contentPanel.add(jP);
        
        JPanel jT = new JPanel();
        jT.setBounds(310, 350, 150, 20);
        jT.add(jTime);

        contentPanel.add(jT);

        JPanel jS = new JPanel();
        jS.setBounds(310, 400, 150, 20);
        jS.add(jScore);

        contentPanel.add(jS);

        JPanel jL = new JPanel();
        jL.setBounds(305, 450, 170, 20);
        jL.add(jLignes);

        contentPanel.add(jL);



        setContentPane(contentPanel);


        
        jDemarre.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
            @Override
            public void actionPerformed(ActionEvent e) {
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        modele.demarrer();
                    }
                });
            }
        });



        jPause.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
            @Override
            public void actionPerformed(ActionEvent e) {
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        modele.pause();
                    }
                });
            }
        });



        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                //System.out.println("key event");
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    modele.action(e.getKeyCode());
                }
                // Renvoyer `true` pour dire que l'event est consommé
                // (=> il ne sera traité par personne d'autre)
                // ou `false` pour propager l'event au prochain Listener
                return false;
            }
        });

    }

    static long lastTime = System.currentTimeMillis();

    @Override
    public void update(Observable o, Object arg) { // rafraichissement de la vue

        SwingUtilities.invokeLater(new Runnable() {
            //@Override
            public void run() {
                vueGrille.update(o, arg);
                if(modele.enCours){
                    jGrille.setVisible(true);
                    repaint();
                }

                modele.getPieceCourante();
                jMeilleurS.setText("" + modele.meilleurScore);
                jTime.setText("Temps : " + (System.currentTimeMillis() - lastTime)/1000 + " s");
                jScore.setText("Score : " + modele.score);
                jLignes.setText("Nombre de Lignes : " + modele.nbLignes);
                if(modele.jeuFini){ // si le jeu est fini on supprime la grille et révèle le Game Over
                    jGrille.setVisible(false);
                    repaint();
                }
            }
        });

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    GrilleSimple m = new GrilleSimple();
                    VC vc = new VC(m);
                    vc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    m.addObserver(vc);
                    vc.setVisible(true);
                }
            }
        );
    }







}
