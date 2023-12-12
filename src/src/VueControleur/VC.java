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

    JLabel jTime = new JLabel("Temps : 0 s");
    JLabel jScore = new JLabel("Score : 0");
    JLabel jLignes = new JLabel("Nombre de Lignes : 0");
    JButton jDemarre = new JButton("Démarrer");
    JButton jPause = new JButton("Pause");
    GrilleSimple modele;

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
        // on enlève les types de rangemtent par défaut
        contentPanel.setLayout(null);



        /* premier sous-panneau : le tétris en lui même */
        JPanel jGrille = new JPanel();
        // setBounds(x,y,width,height); > reminder placement dans le panneau père
        jGrille.setBounds(0, 5, 280, 600);
        vueGrille = new VueGrilleV2(modele); // composant AWT dédié
        jGrille.add((JPanel)vueGrille);

        contentPanel.add(jGrille);



        /* panneaux qui concernent les changements dans le jeu */
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
        jL.setBounds(310, 450, 150, 20);
        jL.add(jLignes);

        contentPanel.add(jL);

        

        /*
        JPanel jp = new JPanel(new GridLayout(1,2));

        vueGrille = new VueGrilleV2(modele); // composant AWT dédié
        jp.add((JPanel)vueGrille);

        JPanel jOptions = new JPanel(new GridLayout(5,1));
        jOptions.add(jTime);
        jOptions.add(jScore);
        jOptions.add(jLignes);
        jOptions.add(jDemarre);
        jOptions.add(jPause);
        jp.add(jOptions);
        */

        setContentPane(contentPanel);
        

        /*
        // On sépare l'écran en deux pour avoir d'un côté le tétris et de l'autre les divers affichages
        JPanel jpGlobal = new JPanel(new GridLayout(1,2));
        vueGrille = new VueGrilleV2(modele); // composant AWT dédié

        // première partie du panneau : grille du jeu
        jpGlobal.add((JPanel)vueGrille);

        // deuxième partie du panneau : boutons et score
        JPanel jpButtons = new JPanel(new BorderLayout(5,5));

        jpButtons.add(jScore, BorderLayout.NORTH);
        jpButtons.add(jDemarre, BorderLayout.CENTER);

        jpGlobal.add(jpButtons);
        setContentPane(jpGlobal);
        */
        


        
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

        /*
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //évènement clavier : object contrôleur qui réceptionne
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE: modele.action();
                }
            }
        });
        */


    }

    static long lastTime = System.currentTimeMillis();

    @Override
    public void update(Observable o, Object arg) { // rafraichissement de la vue

        SwingUtilities.invokeLater(new Runnable() {
            //@Override
            public void run() {
                vueGrille.update(o, arg);

                jTime.setText("Temps : " + (System.currentTimeMillis() - lastTime)/1000 + " s");
                jScore.setText("Score : " + modele.score);
                jLignes.setText("Nombre de Lignes : " + modele.nbLignes);
                //jt.setText("Elapsed time : " + (System.currentTimeMillis() - lastTime) + "ms - x = " + modele.getPieceCourante().getx() + " y = " + modele.getPieceCourante().gety());
                //lastTime = System.currentTimeMillis();

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
