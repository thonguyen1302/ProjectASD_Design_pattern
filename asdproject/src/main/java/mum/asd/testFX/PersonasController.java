package mum.asd.testFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mum.asd.domain.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PersonasController implements Initializable {

    @Autowired
    private PersonaService personaService;

//    @FXML
//    private JFXButton btn_submit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Handles all events on mouse clicks and actions
        eventHandler();

    }

    //Handles all events on mouse clicks and actions
    private void eventHandler() {

//        btn_submit.setOnMouseClicked((MouseEvent event) -> {
//            Skill persona = new Skill("some data");
//            personaService.add(persona);
//
//        }
    }

}
