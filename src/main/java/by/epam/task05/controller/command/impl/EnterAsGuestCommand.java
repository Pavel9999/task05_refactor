package by.epam.task05.controller.command.impl;

import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.util.CreatorFullURL;
import by.epam.task05.entity.Car;
import by.epam.task05.entity.UserRole;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.CarService;
import by.epam.task05.service.ServiceProvider;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EnterAsGuestCommand implements Command {

    private static final String GUEST_PAGE = "/WEB-INF/views/guest_cars.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Car> cars;

        String page = GUEST_PAGE;

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("role", UserRole.GUEST.toString());
        request.getSession(true).setAttribute("email", "");
        request.getSession(true).setAttribute("prev_request", url);

        ServiceProvider provider = ServiceProvider.getInstance();
        CarService service = provider.getCarService();
        cars = service.selectAllCars();
        request.setAttribute("cars", cars);


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
