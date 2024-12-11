package com.example.sms_project;

// Importation des classes nécessaires
import android.Manifest; // Pour gérer les permissions
import android.content.pm.PackageManager; // Pour vérifier l'état des permissions
import android.os.Bundle; // Pour transférer les données lors de la création d'activités
import android.telephony.SmsManager; // Pour envoyer des SMS
import android.util.Log; // Pour les journaux d'information
import android.view.View; // Pour gérer les interactions utilisateur
import android.widget.Button; // Pour représenter le bouton d'envoi
import android.widget.EditText; // Pour la saisie du numéro et du message
import androidx.activity.EdgeToEdge; // Pour gérer les bords des écrans récents
import androidx.appcompat.app.AppCompatActivity; // Classe de base pour les activités
import androidx.core.app.ActivityCompat; // Pour demander les permissions
import androidx.core.content.ContextCompat; // Pour vérifier l'état des permissions
import androidx.core.graphics.Insets; // Pour gérer les marges et bordures
import androidx.core.view.ViewCompat; // Pour appliquer des marges globales
import androidx.core.view.WindowInsetsCompat; // Pour détecter la taille des barres système

public class MainActivity extends AppCompatActivity {

    // Déclaration des propriétés de la classe
    private Button envoi; // Bouton pour déclencher l'envoi du SMS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Activation de la gestion des bords d'écran
        setContentView(R.layout.activity_main); // Définition de la vue pour l'activité
        // Vérification des permissions SEND_SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Si la permission n'est pas accordée, elle est demandée
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        initActivity(); // Initialisation des éléments de l'activité
        // Gestion des marges en fonction des barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initActivity() {
        // Liaison avec les éléments graphiques définis dans le fichier XML
        Log.i("avant", "avant"); // Log pour vérifier le processus d'initialisation
        envoi = (Button) findViewById(R.id.btnEnvoi); // Bouton d'envoi
        createOnClickEnvoiButton(); // Configuration de l'action du bouton
        Log.i("apres", "apres"); // Log pour vérifier l'état post-initialisation
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) { // Vérification du code de requête
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission", "Permission SEND_SMS accordée");
            } else {
                Log.i("Permission", "Permission SEND_SMS refusée");
            }
        }
    }

    private void createOnClickEnvoiButton() {
        // Définition de l'action lors du clic sur le bouton
        envoi.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                // Envoi d'un SMS avec un message fixe
                SmsManager.getDefault().sendTextMessage("5554", null, "abc", null, null);
            }
        });
    }}
