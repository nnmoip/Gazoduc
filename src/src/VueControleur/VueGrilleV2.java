package VueControleur;

import Modele.GrilleSimple;
import Modele.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

class VueGrilleV2 extends JPanel implements Observer {

    private final static int TAILLE = 16;
    private GrilleSimple modele;
    Canvas c;

    public VueGrilleV2(GrilleSimple _modele) {

        modele = _modele;
        setLayout(new BorderLayout());
        Dimension dim = new Dimension(TAILLE*modele.TAILLEX,TAILLE*modele.TAILLEY);
        //this.setPreferredSize(dim);



        //setBackground(Color.black);

        c = new Canvas() {


            public void paint(Graphics g) {


                for (int i = 0; i < modele.TAILLEX; i++) {
                    for (int j = 0; j < modele.TAILLEY; j++) {
                        //if (!(i == modele.getPieceCourante().getx() && j == modele.getPieceCourante().gety())) {
                        g.setColor(Color.WHITE);
                        g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
                        g.setColor(Color.BLACK);
                        g.drawRoundRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE, 1, 1);
                    }

                }

                Color getCouleur = modele.getPieceCourante().couleur;
                g.setColor(getCouleur);

                for (int i = 0; i < modele.getPieceCourante().tailleMatrices; i++) {
                    for (int j = 0; j < modele.getPieceCourante().tailleMatrices; j++) {

                        if (modele.getPieceCourante().motif[i][j]) {
                            int xabs = modele.getPieceCourante().getx() + i;
                            int yabs = modele.getPieceCourante().gety() + j;

                            g.fillRect(xabs * TAILLE, yabs * TAILLE, TAILLE, TAILLE);

                        }
                    }
                }

            }
        };

        c.setPreferredSize(dim);
        add(c, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {

        BufferStrategy bs = c.getBufferStrategy(); // bs + dispose + show : double buffering pour éviter les scintillements
        if(bs == null) {
            c.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        c.paint(g); // appel de la fonction pour dessiner
        g.dispose();
        //Toolkit.getDefaultToolkit().sync(); // forcer la synchronisation
        bs.show();
    }
}
