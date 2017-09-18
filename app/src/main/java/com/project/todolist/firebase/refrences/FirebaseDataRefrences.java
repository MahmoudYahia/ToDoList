package com.project.todolist.firebase.refrences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mah_y on 8/29/2017.
 */

public class FirebaseDataRefrences {

    private static FirebaseDataRefrences firebaseDataRefrencesInstance; // instance of singleton
    private static DatabaseReference databaseReference;
    private static FirebaseUser firebaseUser;

    private FirebaseDataRefrences() {
    }

    public static FirebaseDataRefrences getInstance() {

        if (firebaseDataRefrencesInstance == null) {
            firebaseDataRefrencesInstance = new FirebaseDataRefrences();
            return firebaseDataRefrencesInstance;
        } else {
            return firebaseDataRefrencesInstance;
        }

    }

    public DatabaseReference getReference() {

        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
            return databaseReference;
        } else {
            return databaseReference;

        }
    }

    public FirebaseUser getFirebaseUser() {

        if (firebaseUser == null) {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            return firebaseUser;
        } else {
            return firebaseUser;
        }

    }

    public void setFirebaseUserNull() {
        firebaseUser = null;
    }

}
