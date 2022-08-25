package connector

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream
import java.io.InputStream

class Connection{
    fun getConnection(): Firestore {
            val serviceAccount:InputStream= FileInputStream("/home/savera/Downloads/firebasesetup-30b7209597af.json")
    val credentials:GoogleCredentials = GoogleCredentials.fromStream(serviceAccount);
    val options:FirebaseOptions=FirebaseOptions.builder()
        .setCredentials(credentials)
        .build()
    FirebaseApp.initializeApp(options)
    val db:Firestore = FirestoreClient.getFirestore()
        return  db
    }
}