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

    JLabel jScore = new JLabel("");
    JLabel jLignes = new JLabel("");
    JButton jb = new JButton("Démarrer");
    GrilleSimple modele;

    Observer vueGrille;
    private Executor ex =  Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modele) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modele = _modele;

        setSize(500, 590);
        setTitle("Tetris Game");
        setLocationRelativeTo(null); // s'affiche au centre de l'écran

        
        JPanel jp = new JPanel(new BorderLayout(5,0));

        JPanel jContinue = new JPanel(new GridLayout(2,1));
        jContinue.add(jScore);
        jContinue.add(jLignes);
        jp.add(jContinue, BorderLayout.NORTH);

        vueGrille = new VueGrilleV2(modele); // composant AWT dédié
        jp.add((JPanel)vueGrille, BorderLayout.CENTER);

        jp.add(jb, BorderLayout.SOUTH);

        setContentPane(jp);
        

        /*
        // On sépare l'écran en deux pour avoir d'un côté le tétris et de l'autre les divers affichages
        JPanel jpGlobal = new JPanel(new GridLayout(1,2));
        vueGrille = new VueGrilleV2(modele); // composant AWT dédié

        // première partie du panneau : grille du jeu
        jpGlobal.add((JPanel)vueGrille);

        // deuxième partie du panneau : boutons et score
        JPanel jpButtons = new JPanel(new BorderLayout(5,5));

        jpButtons.add(jScore, BorderLayout.NORTH);
        jpButtons.add(jb, BorderLayout.CENTER);

        jpGlobal.add(jpButtons);
        setContentPane(jpGlobal);
        */
        


        /*
        jb.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
            @Override
            public void actionPerformed(ActionEvent e) {
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        modele.action();
                    }
                });
            }
        });
        */

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
