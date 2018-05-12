package serializaradb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static serializaradb.Conexion.getConnection;

public class serializa extends javax.swing.JFrame {

    private Conexion con = null;
    private Persona p; 
    
    /*
    p es un objeto de la clase Persona, la cual es serializable y contiene objetos serializables
    Si un objeto a serializar contiene otros objetos u array d
    e objetos, estos tambien deben ser serializables
    */
    
 

    //query para los inserts
    static final String WRITE_OBJECT_SQL = "INSERT INTO ejem(nombre, valor_objeto) VALUES (?, ?)";

    //query para los select
    static final String READ_OBJECT_SQL = "SELECT valor_objeto FROM ejem WHERE id = ?";

    public serializa() {

        try {
            Connection conn = null;
            try {
                initComponents();
                conn = getConnection();

                ArrayList<Gusto> gustos = new ArrayList();

                Gusto uno = new Gusto("programar");
                Gusto dos = new Gusto("videjuegos");
                gustos.add(uno);
                gustos.add(dos);

                Persona p = new Persona("Marcelo", gustos);

                long objectID = writeJavaObject(conn, p);

                Persona objFromDatabase = (Persona) readJavaObject(conn, objectID);

               
                System.out.println("A "+objFromDatabase.getNombre()+" le gusta ");
                for (Gusto g : objFromDatabase.getGustos()) {
                    System.out.println(g.getNombreDeGusto());
                    
                }

            } catch (Exception ex) {
                Logger.getLogger(serializa.class.getName()).log(Level.SEVERE, null, ex);
            }

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(serializa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    //para escribir el objeto
    public static long writeJavaObject(Connection conn, Object object) throws Exception {
        String className = object.getClass().getName();
        PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);

        // fijar parametros de ingreso
        pstmt.setString(1, className);
        pstmt.setObject(2, object);
        pstmt.executeUpdate();

        // obtener la clave generada para el id
        ResultSet rs = pstmt.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();
        pstmt.close();
        
        return id;
    }

    //para leer el objeto
    public static Object readJavaObject(Connection conn, long id) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Object object = rs.getObject(1);
        String className = object.getClass().getName();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }

        Object deSerializedObject = objectIn.readObject();

        rs.close();
        pstmt.close();
        
        return deSerializedObject;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(serializa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(serializa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(serializa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(serializa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new serializa().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
