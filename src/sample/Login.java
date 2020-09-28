package sample;





import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




//import com.sun.prism.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

//import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

       // login lable for useremail and password
      @FXML private TextField userMail ,userPassword;
       private String userEmailCheck ,userPasswordCheck;
       Alert a = new Alert(Alert.AlertType.NONE) ;
      //if the data right
      private void iftheDataRight(javafx.event.ActionEvent event) throws IOException
      {
          Parent tableview = FXMLLoader.load(getClass().getResource("secondPage.fxml"));
          Scene tablescene = new Scene(tableview);
          Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
          windows.setScene(tablescene);
          windows.show();

      }
// button action
 public  void loginButton(javafx.event.ActionEvent event) throws IOException
     {
         userEmailCheck =  userMail.getText().toString();
         userPasswordCheck =  userPassword.getText().toString();

         //  CALL Your FUNCTION
   /*  if ()
           {
              iftheDataRight(event);
           }
      else
           {
              Errorinlogin(event);
           }*/

     }
 //if you email or password had some thing wrong
    private void Errorinlogin(javafx.event.ActionEvent event) throws IOException
    {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Invalid Email or password ");
        a.show()  ;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
