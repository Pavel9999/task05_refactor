package by.epam.task05.controller.command.impl;



import by.epam.task05.controller.command.Command;
import by.epam.task05.dao.DaoException;
import by.epam.task05.entity.UserData;
import by.epam.task05.entity.UserRole;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.ClientService;
import by.epam.task05.service.ServiceException;
import by.epam.task05.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {

	private static final String REGISTRATION_PAGE = "/WEB-INF/views/registration.jsp";
	private static final String INDEX_PAGE = "/WEB-INF/views/default.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String page = REGISTRATION_PAGE;

		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();

		int role = 2;
		String first_name = request.getParameter("first_name");
		String second_name = request.getParameter("second_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserData userData = new UserData(role, first_name, second_name, last_name, email, password);

		try {
			if (service.registration(userData))
			{
				page = INDEX_PAGE;
				request.setAttribute("message_text", "регистрация завершена успешно!");
			}
			else
			{
				page = REGISTRATION_PAGE;
				request.setAttribute("message_text", "регистрация провалилась");
			}


		} catch (ServiceException e) {
			request.setAttribute("error", "Login or Password Error");
			// log
			page = INDEX_PAGE;
		} catch (DaoException e) {
			e.printStackTrace();
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
