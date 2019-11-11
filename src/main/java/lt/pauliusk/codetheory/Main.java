package lt.pauliusk.codetheory;

import javafx.application.Application;
import javafx.stage.Stage;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Main extends Application {
    @Autowired
    private IWindowLoader windowLoader;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }

    @PostConstruct
    private void initPrimaryWindow() {
        windowLoader
                .getWindow("MainMenu")
                .render();
    }
}
