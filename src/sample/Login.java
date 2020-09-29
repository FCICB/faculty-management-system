package sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;


public class Login implements Initializable {


       @FXML private TextField userMail ,userPassword;
       private String userEmailCheck ,userPasswordCheck;
       @FXML private CheckBox admin;
       Alert a = new Alert(Alert.AlertType.NONE) ;
       @FXML Label error_label=new Label("Valid");
       private String pattern ="^[A-Za-z0-9+_.-]+@(.+)$";

      private void iftheDataRight(javafx.event.ActionEvent event) throws IOException
      {
          Parent tableview = FXMLLoader.load(getClass().getResource("secondPage.fxml"));
          Scene tablescene = new Scene(tableview);
          Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
          windows.setScene(tablescene);
          windows.show();

      }

      private  boolean adminOrNot()
      {
          if (admin.isSelected())
              return true;
         else
             return false;
      }



// button action
 public  void loginButton(javafx.event.ActionEvent event) throws IOException
         {
             userEmailCheck =  userMail.getText();
             userPasswordCheck =  userPassword.getText();


         //  CALL Your FUNCTION
  /*    if   (adminOrNot()&&(userMail.getText()).matches(pattern))
           {
              iftheDataRight(event);
           }
      else if ()
           {
             iftheDataRight(event);
           }
     else
          {
              Errorinlogin();
          }
*/
     }


    @FXML
    private boolean isValidName(){


       if(!(userMail.getText()).matches(pattern) )
        {
            error_label.setText("Invalid");
            error_label.setStyle("-fx-text-fill:red");
            return true;
        }

       else
        {
            this.error_label.setText(" ");
            return false;
        }
    }







    
  //  private void Errorinlogin(javafx.event.ActionEvent event) throws IOException
    private void Errorinlogin()
    {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Invalid Email or password ");
        a.show()  ;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.error_label.setText(" ");

    }
}
