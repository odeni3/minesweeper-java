package zminesweeper;

import java.util.Timer;
import java.util.TimerTask;

import gui.LoadingGui;
import gui.MainFrame;

public class Main {

	public static void main(String[] args) {

		//criando uma instância de LoadingGui
        LoadingGui loadingGui = new LoadingGui();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadingGui.dispose();
                
                //inicializando a janela principal
                new MainFrame();
            }
        }, 4000);
    }
}



