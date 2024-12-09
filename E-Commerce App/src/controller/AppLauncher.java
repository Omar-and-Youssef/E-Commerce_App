package controller;

public class AppLauncher{
    private static Engine app;
    public static void main(String[]args){
        new AppLauncher().launch();
    }
    void launch(){
        app=new Engine();
        app.start();
        //
    }
}
