package by.epam.task05.controller.command.impl;

import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.util.CreatorFullURL;
import by.epam.task05.entity.Car;
import by.epam.task05.entity.UserRole;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.CarService;
import by.epam.task05.service.ClientService;
import by.epam.task05.service.ServiceProvider;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FreeCarsCommand implements Command
{
    private static final String DEFAULT_PAGE = "/WEB-INF/views/default.jsp";
    private static final String GUEST_PAGE = "/WEB-INF/views/guest_cars.jsp";
    private static final String CLIENT_PAGE = "/WEB-INF/views/client_cars.jsp";
    private static final String MANAGER_PAGE = "/WEB-INF/views/manager_cars.jsp";
    private static final String ADMIN_PAGE = "/WEB-INF/views/admin_cars.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;
        String email;
        UserRole role;
        String url;
        String page;
        List<Car> cars;


        session = request.getSession(true);
        email = (String)session.getAttribute("email");
        role = UserRole.createRole((String)session.getAttribute("role")) ;
        url = CreatorFullURL.create(request);
        session.setAttribute("prev_request", url);

        ServiceProvider provider = ServiceProvider.getInstance();
        CarService service = provider.getCarService();
        cars = service.selectFreeCars();
        request.setAttribute("cars", cars);

        switch (role)
        {
            case GUEST:{
                page = GUEST_PAGE;
            }break;
            case CLIENT:{
                page = CLIENT_PAGE;
                request.setAttribute("full_name", session.getAttribute("full_name"));
            }break;
            case MANAGER:{
                page = MANAGER_PAGE;
                request.setAttribute("full_name", session.getAttribute("full_name"));
            }break;
            case ADMIN:{
                page = ADMIN_PAGE;
                request.setAttribute("full_name", session.getAttribute("full_name"));
            }break;
            default: {
                page = DEFAULT_PAGE;
            }break;
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
