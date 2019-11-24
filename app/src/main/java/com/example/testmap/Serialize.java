package com.example.testmap;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize {

    //Creer un fichier avec les données des l'utilisateurs
    public static <CSave extends Serializable> void Serialization(Context context, CSave save, String fileName) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            os.writeObject(save);
            //Toast.makeText(context, "Serialisé", Toast.LENGTH_LONG).show();
            os.close();
            fos.close();
        } catch (IOException e) {
            //Toast.makeText(context, "Echec", Toast.LENGTH_LONG).show();
        }
    }

    //Creer un objet CSave depuis le fichier
    public static <CSave extends Serializable> CSave Deserialization(Context context, String fileName) {
        CSave save = null;

        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);

            save = (CSave) is.readObject();
            //Toast.makeText(context, "Déserialisé", Toast.LENGTH_LONG).show();
            is.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            //Toast.makeText(context, "Echec", Toast.LENGTH_LONG).show();
        }
        return save;
    }

    //Pour faire des tests
    public static void ResetFile(Context context, String fileName) {
        context.deleteFile(fileName);
    }
}
