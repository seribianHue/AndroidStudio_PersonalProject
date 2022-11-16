package com.example.project_personal;

import android.widget.Toast;

import java.util.ArrayList;

public class IDList {
    static ArrayList<String> IDList = new ArrayList<>();

    static boolean checkIDduplicants(String id) {
        for (String ID: IDList) {
            if (ID.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
