package lt.pauliusk.codetheory;

import javafx.application.Application;
import javafx.stage.Stage;
import lt.pauliusk.codetheory.communicator.ICommunicator;
import lt.pauliusk.codetheory.util.file.IBMPLoader;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class Main extends Application {
    @Autowired
    private IWindowLoader windowLoader;
    @Autowired
    private ICommunicator communicator;
    @Autowired
    private IBMPLoader ibmpLoader;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }

    @PostConstruct
    private void initPrimaryWindow() throws IOException {
        windowLoader
                .getWindow("MainMenu")
                .render();
    }
}
