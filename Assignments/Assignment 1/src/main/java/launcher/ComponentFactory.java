package launcher;

import controller.ActuallyController.AdminActuallyController;
import controller.ActuallyController.LoginActuallyController;
import controller.ActuallyController.UserAcctuallyController;
import controller.AdminFXView;
import controller.LoginFXView;
import controller.UserFXView;
import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryImplementation;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryImplementation;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryImplementation;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceImplementation;
import service.activity.ActivityService;
import service.activity.ActivityServiceImplementation;
import service.client.ClientService;
import service.client.ClientServiceImplementation;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceImplementation;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {


    //views
    private final LoginFXView loginFXView;
    private final AdminFXView adminFXView;
    private final UserFXView userFXView;

    //controller
    private final LoginActuallyController loginActuallyController;
    private final UserAcctuallyController userActuallyController;
    private final AdminActuallyController adminActuallyController;

    //services
    private final AuthenticationService authenticationService;
    private final ActivityService activityService;
    private final AccountService accountService;
    private final ClientService clientService;
    private final UserService userService;

    //repo
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final ClientRepository clientRepository;


    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    //constructor
    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        //repo
        this.userRepository = new UserRepositoryMySQL(connection);
        this.accountRepository= new AccountRepositoryImplementation(connection);
        this.activityRepository= new ActivityRepositoryImplementation(connection);
        this.clientRepository = new ClientRepositoryImplementation(connection);

        //service
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository);
        this.accountService = new AccountServiceImplementation(this.accountRepository);
        this.activityService = new ActivityServiceImplementation(this.activityRepository);
        this.clientService = new ClientServiceImplementation(this.clientRepository);
        this.userService = new UserServiceImplementation(this.userRepository);

        //view
        this.loginFXView = new LoginFXView();
        this.adminFXView = new AdminFXView();
        this.userFXView = new UserFXView();

        //controller
        this.loginActuallyController= new LoginActuallyController(this.loginFXView,this.authenticationService);
        this.adminActuallyController = new AdminActuallyController(this.adminFXView, this.userService, this.activityService);
        this.userActuallyController = new UserAcctuallyController(this.userFXView, this.userService,  this.accountService, this.clientService, this.activityService);

    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
    public ClientRepository getClientRepository() {return clientRepository;}
    public AccountRepository getAccountRepository() {return accountRepository;}
    public ActivityRepository getActivityRepository(){return activityRepository;}

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
    public UserService getUserService(){return userService;}
    public ClientService getClientService(){return  clientService;}
    public AccountService getAccountService() {return  accountService;}
    public ActivityService getActivityService(){return activityService;}

    public LoginFXView getLoginFXView() {
        return loginFXView;
    }
    public AdminFXView getAdminFXView() {return adminFXView;}
    public UserFXView getUserFXView(){ return userFXView;}

    public AdminActuallyController getAdminActuallyController() { return adminActuallyController;}

    public UserAcctuallyController getUserActuallyController() { return  userActuallyController;}

    public LoginActuallyController getLoginActuallyController(){ return loginActuallyController;}
}
