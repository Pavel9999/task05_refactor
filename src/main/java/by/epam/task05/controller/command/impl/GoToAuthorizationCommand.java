package by.epam.task05.controller.command.impl;



import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.util.CreatorFullURL;
import by.epam.task05.dao.DaoException;
import by.epam.task05.entity.Car;
import by.epam.task05.entity.ContractData;
import by.epam.task05.entity.User;
import by.epam.task05.entity.UserRole;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.CarService;
import by.epam.task05.service.ClientService;
import by.epam.task05.service.ContractService;
import by.epam.task05.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToAuthorizationCommand implements Command {

    private static final String AUTHORIZATION_PAGE = "/WEB-INF/views/authorization.jsp";
    private static final String CLIENT_CARS_PAGE = "/WEB-INF/views/client_cars.jsp";
    private static final String MANAGER_CARS_PAGE = "/WEB-INF/views/manager_contracts.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        String page = AUTHORIZATION_PAGE;


        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
    }
}


        /*
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);
        String page = AUTHORIZATION_PAGE;
        HttpSession session = request.getSession(true);
        UserRole role = UserRole.GUEST;

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService clientService = provider.getClientService();
        CarService serviceCar = provider.getCarService();
        ContractService contractService = provider.getContractService();

        try{
            role = UserRole.createRole((String)session.getAttribute("role")) ;
        }catch (Exception e)
        {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }

        if (role == UserRole.GUEST)
        {
            serviceCar = provider.getCarService();
            List<Car> cars = serviceCar.selectAllCars();
            page = CLIENT_CARS_PAGE;
        }

        if (role == UserRole.MANAGER)
        {
            contractService = provider.getContractService();
            User user = null;
            try{
                clientService.getUser((String)session.getAttribute("email"));
            }catch (Exception e)
            {
                System.out.println(e);
                MyLogger.getInstance().error(e);
            }

            try{
                List<ContractData> contracts = contractService.selectManagerContracts(user.getId());
                request.setAttribute("contracts", contracts);
            }
            catch (DaoException e)
            {}

            page = MANAGER_CARS_PAGE;
        }




        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        try {
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
    }

}
*/