package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Oleg","Smirnov","some1@email.ru");
      Car car1 = new Car("Volvo", 12948);

      userService.add(user1,car1);
      User user2 = new User("Andrey","Golubev","some2@email.ru");
      Car car2 = new Car("Mersedes", 981237);

      userService.add(user2,car2);
      User user3 = new User("Anton","Petrov","some3@email.ru");
      Car car3 = new Car("Moskvich", 90892348);

      userService.add(user3,car3);

      userService.getUserById(1);
      userService.getUserById(2);
      userService.getUserById(3);

      userService.getUser(car3.getModel(), car3.getSeries());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
