package by.epam.task05.controller.command.impl;



import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.util.CreatorFullURL;
import by.epam.task05.dao.DaoException;
import by.epam.task05.entity.Car;
import by.epam.task05.entity.Contract;
import by.epam.task05.entity.ContractData;
import by.epam.task05.entity.User;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthorizationCommand implements Command {

	private static final String PARAMETER_LOGIN = "email";
	private static final String PARAMETER_PASSWORD = "password";

	private static final String GUEST_CARS_PAGE = "/WEB-INF/views/guest_cars.jsp";
	private static final String CLIENT_CARS_PAGE = "/WEB-INF/views/client_cars.jsp";
	private static final String MANAGER_CARS_PAGE = "/WEB-INF/views/manager_contracts.jsp";
	private static final String ADMIN_CARS_PAGE = "/WEB-INF/views/admin_cars.jsp";
	private static final String AUTHORIZATION_PAGE = "/WEB-INF/views/authorization.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login;
		String password;

		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);

		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();

		User user = null;
		String page;
		try {
			user = service.authorization(login, password);

			if (user == null) {
				request.setAttribute("message_text", "login or password error");
				page = AUTHORIZATION_PAGE;
			} else {
				request.setAttribute("user", user);

				switch (user.getRole())
				{
					case CLIENT: {
						CarService serviceCar = provider.getCarService();
						List<Car> cars = serviceCar.selectAllCars();
						request.setAttribute("cars", cars);
						page = CLIENT_CARS_PAGE;
					} break;
					case MANAGER: {
						ContractService contractService = provider.getContractService();

						try{
							List<ContractData> contracts = contractService.selectManagerContracts(user.getId());
							request.setAttribute("contracts", contracts);
						}
						catch (DaoException e)
						{}

						page = MANAGER_CARS_PAGE;
					} break;
					case ADMIN: {
						page = ADMIN_CARS_PAGE;
					} break;
					default: {
						page = GUEST_CARS_PAGE;
					} break;
				}

				String role = user.getRole().toString();

				HttpSession session = request.getSession(true);
				session.setAttribute("email", user.getEmail());
				session.setAttribute("role", role);
				session.setAttribute("full_name", user.getFull_name());
			}
		} catch (ServiceException e) {
			request.setAttribute("message_text", "Login or Password Error");
			// log
			page = AUTHORIZATION_PAGE;
		}

		String url = CreatorFullURL.create(request);
		request.getSession( true).setAttribute("prev_request", url);




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
