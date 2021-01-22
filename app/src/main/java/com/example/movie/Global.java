package com.example.movie;

public class Global {
    public static ResponseBody body;
    public static int firstVisibleItem = 0;
    public static void setBody(ResponseBody localbody) {
        body = localbody;
    }


    static String[] languages = {"ru-RU","en-US"};
    public static String language = "ru-RU";

    public static void changeLang(){
        for(int i = 0; i < languages.length; i++){
            if(languages[i].contains(language))
                if(i == languages.length - 1) {
                    language = languages[0];
                    break;
                }
                else {
                    language = languages[i + 1];
                    break;
                }
        }
    }
}
